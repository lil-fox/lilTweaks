package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.config.Configs;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.concurrent.locks.LockSupport;

@Mixin(AxeItem.class)
public abstract class AxeEffect extends Item {


    public AxeEffect(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world,
                            PlayerEntity user,
                            Hand hand){
        ActionResult result = super.use(world, user, hand);
        if (Configs.axeEffect.getBooleanValue()) {
            ItemStack stack = user.getStackInHand(hand);
            if(stack.getItem() == Items.NETHERITE_AXE && user.isOnGround()) {
                this.movePlayer((ClientPlayerEntity) user);
                result = ActionResult.SUCCESS;
            }
        }

        return result;
    }

    @Unique
    private void movePlayer(ClientPlayerEntity player) {
        Vec3d lookDirection = player.getRotationVec(1.0F);
        Vec3d eyePos = player.getEyePos();
        BlockHitResult hitResult = player.getEntityWorld().raycast(new RaycastContext(eyePos, eyePos.add(lookDirection.multiply((double)40.0F)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
        Vec3d targetPos;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos hitResultPos = hitResult.getBlockPos();
            BlockPos aboveTargetBlockFoot = hitResultPos.up();
            BlockPos aboveTargetBlockHead = aboveTargetBlockFoot.up();
            BlockState aboveTargetBlockFootState = player.getEntityWorld().getBlockState(aboveTargetBlockFoot);
            BlockState aboveTargetBlockHeadState = player.getEntityWorld().getBlockState(aboveTargetBlockHead);
            if (!player.getEntityWorld().getBlockState(aboveTargetBlockFoot).isAir() && !aboveTargetBlockFootState.getCollisionShape(player.getEntityWorld(), aboveTargetBlockFoot).isEmpty() || !player.getEntityWorld().getBlockState(aboveTargetBlockHead).isAir() && !aboveTargetBlockHeadState.getCollisionShape(player.getEntityWorld(), aboveTargetBlockHead).isEmpty()) {

                targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)(hitResultPos.getY() + 1), (double)hitResultPos.getZ() + (double)0.5F);
//                switch (hitResult.getSide()) {
//                    case NORTH -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)hitResultPos.getY() + (double)0.5F, (double)(hitResultPos.getZ() - 1));
//                    case SOUTH -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)hitResultPos.getY() + (double)0.5F, (double)hitResultPos.getZ() + (double)1.0F);
//                    case WEST -> targetPos = new Vec3d((double)(hitResultPos.getX() - 1), (double)hitResultPos.getY() + (double)0.5F, (double)hitResultPos.getZ() + (double)0.5F);
//                    case EAST -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)1.0F, (double)hitResultPos.getY() + (double)0.5F, (double)hitResultPos.getZ() + (double)0.5F);
//                    case UP -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)(hitResultPos.getY() + 1), (double)hitResultPos.getZ() + (double)0.5F);
//                    case DOWN -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)hitResultPos.getY() - (double)0.5F, (double)hitResultPos.getZ() + (double)0.5F);
//                    default -> targetPos = new Vec3d((double)hitResultPos.getX() + (double)0.5F, (double)hitResultPos.getY() + (double)0.5F, (double)hitResultPos.getZ() + (double)0.5F);
//                }
            } else {
                targetPos = new Vec3d((double)hitResult.getBlockPos().getX() + (double)0.5F, (double)(hitResult.getBlockPos().getY() + 1), (double)hitResult.getBlockPos().getZ() + (double)0.5F);
            }
        } else {
            targetPos = eyePos.add(lookDirection.multiply((double)40.0F));
        }

        (new Thread(() -> {
            Vec3d currentPosition = player.getEntityPos();
            double distanceX = Math.abs(targetPos.x - currentPosition.x);
            double distanceY = Math.abs(targetPos.y - currentPosition.y);
            double distanceZ = Math.abs(targetPos.z - currentPosition.z);
            double maxDistance = Math.max(distanceX, Math.max(distanceY, distanceZ));
            double stepSizeX = 0.02 * (distanceX / maxDistance);
            double stepSizeY = 0.02 * (distanceY / maxDistance);
            double stepSizeZ = 0.02 * (distanceZ / maxDistance);
            //Configs.axing = true;
            //player.getAbilities().flying = true;

            while(currentPosition.distanceTo(targetPos) > 0.02 && !player.isRemoved() && !player.isDead()) {
                currentPosition = new Vec3d(this.moveTowards(currentPosition.x, targetPos.x, stepSizeX), this.moveTowards(currentPosition.y, targetPos.y, stepSizeY), this.moveTowards(currentPosition.z, targetPos.z, stepSizeZ));
                player.updatePosition(currentPosition.x, currentPosition.y, currentPosition.z);

//                try {
                    LockSupport.parkNanos(1000);
                    //Thread.sleep(1L);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }

            //Configs.axing = false;
            //player.getAbilities().flying = false;
        })).start();
    }

    @Unique
    private double moveTowards(double current, double target, double step) {
        return current < target ? Math.min(current + step, target) : Math.max(current - step, target);
    }
}
