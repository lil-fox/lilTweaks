package net.lilfox.liltweaks.mixin;


import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.lilfox.liltweaks.Reference;
import net.lilfox.liltweaks.config.Configs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import top.hendrixshen.magiclib.api.malilib.annotation.Config;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends ToolItem {


    @Shadow
    protected abstract Optional<BlockState> tryStrip(World world, BlockPos pos, @Nullable PlayerEntity player, BlockState state);

    public AxeItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    //TODO clean
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        TypedActionResult<ItemStack> result = super.use(world, user, hand);

        if (Configs.axeEffect.getBooleanValue()) {

            if (this.getMaterial() == ToolMaterials.NETHERITE &&
                user.isOnGround()) {

                movePlayer((ClientPlayerEntity) user);

                ItemStack itemStack = user.getStackInHand(hand);
                result = TypedActionResult.success(itemStack);
            }
        }

        return result;
    }

    @Unique
    private void movePlayer(ClientPlayerEntity player) {

        Vec3d lookDirection = player.getRotationVec(1f);
        Vec3d eyePos = player.getEyePos();

        BlockHitResult hitResult = player.getWorld().raycast(new RaycastContext(
                eyePos,
                eyePos.add(lookDirection.multiply(40)),
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE,
                player
        ));

        Vec3d targetPos;

        if(hitResult.getType() == HitResult.Type.BLOCK){

            BlockPos hitResultPos = hitResult.getBlockPos();

            BlockPos aboveTargetBlockFoot = hitResultPos.up();
            BlockPos aboveTargetBlockHead = aboveTargetBlockFoot.up();

            BlockState aboveTargetBlockFootState = player.getWorld().getBlockState(aboveTargetBlockFoot);
            BlockState aboveTargetBlockHeadState = player.getWorld().getBlockState(aboveTargetBlockHead);

            if((player.getWorld().getBlockState(aboveTargetBlockFoot).isAir() ||
                    aboveTargetBlockFootState.getCollisionShape(player.getWorld(), aboveTargetBlockFoot).isEmpty())
            && (player.getWorld().getBlockState(aboveTargetBlockHead).isAir() ||
                    aboveTargetBlockHeadState.getCollisionShape(player.getWorld(), aboveTargetBlockHead).isEmpty())){
                targetPos = new Vec3d(hitResult.getBlockPos().getX() + 0.5,
                        hitResult.getBlockPos().getY() + 1,
                        hitResult.getBlockPos().getZ() + 0.5);
            }else {
                switch (hitResult.getSide()){
                    case NORTH:
                        targetPos = new Vec3d(hitResultPos.getX() + 0.5,
                                hitResultPos.getY() + 0.5,
                                hitResultPos.getZ() - 1);
                        break;
                    case SOUTH:
                        targetPos = new Vec3d(hitResultPos.getX() + 0.5,
                                hitResultPos.getY() + 0.5,
                                hitResultPos.getZ() + 1.0);
                        break;
                    case WEST:
                        targetPos = new Vec3d(hitResultPos.getX() - 1,
                                hitResultPos.getY() + 0.5,
                                hitResultPos.getZ() + 0.5);
                        break;
                    case EAST:
                        targetPos = new Vec3d(hitResultPos.getX() + 1.0,
                                hitResultPos.getY() + 0.5,
                                hitResultPos.getZ() + 0.5);
                        break;
                    case UP:
                        targetPos = new Vec3d(hitResultPos.getX() + 0.5,
                                hitResultPos.getY() + 1,
                                hitResultPos.getZ() + 0.5);
                        break;
                    case DOWN:
                        targetPos = new Vec3d(hitResultPos.getX() + 0.5,
                                hitResultPos.getY() - 0.5,
                                hitResultPos.getZ() + 0.5);
                        break;
                    default:
                        targetPos = new Vec3d(hitResultPos.getX() + 0.5,
                                hitResultPos.getY() + 0.5,
                                hitResultPos.getZ() + 0.5);
                        break;
                }
            }

        }else {
            targetPos = eyePos.add(lookDirection.multiply(40));
        }

        Vec3d moveDirection = targetPos.subtract(player.getPos()).normalize();

        new Thread(() -> {
            Vec3d currentPosition = player.getPos();
            Vec3d velocity = moveDirection.multiply(0.2);

            double distanceX = Math.abs(targetPos.x - currentPosition.x);
            double distanceY = Math.abs(targetPos.y - currentPosition.y);
            double distanceZ = Math.abs(targetPos.z - currentPosition.z);

            double maxDistance = Math.max(distanceX, Math.max(distanceY, distanceZ));
            double stepSizeX = 0.2 * (distanceX / maxDistance);
            double stepSizeY = 0.2 * (distanceY / maxDistance);
            double stepSizeZ = 0.2 * (distanceZ / maxDistance);

            Configs.axing = true;
            player.getAbilities().flying = true;
            while (currentPosition.distanceTo(targetPos) > 0.1){
                if(player.isRemoved() || player.isDead()){
                    break;
                }

                currentPosition = new Vec3d(
                        moveTowards(currentPosition.x,
                                targetPos.x, stepSizeX),
                        moveTowards(currentPosition.y,
                                targetPos.y, stepSizeY),
                        moveTowards(currentPosition.z,
                                targetPos.z, stepSizeZ)
                );


                player.updatePosition(currentPosition.x,
                        currentPosition.y,
                        currentPosition.z);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            Configs.axing = false;
            player.getAbilities().flying = false;

        }).start();

    }

    @Unique
    private double moveTowards(double current, double target, double step) {
        if(current < target){
            return Math.min(current + step, target);
        }
        return Math.max(current - step, target);
    }

}
