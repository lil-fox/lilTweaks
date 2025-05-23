package net.lilfox.liltweaks.mixin;


import net.lilfox.liltweaks.config.Configs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractBoatEntity.class)
public class BormulaOne{

    @Redirect(method = "getNearbySlipperiness", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float bormulaOne(Block instance){

        BoatEntity self = (BoatEntity) (Object) this;

        if(!(Configs.bormulaOne.getBooleanValue() && self.isOnGround())) {
            return instance.getSlipperiness();
        }

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Entity passenger = self.getControllingPassenger();

        if(passenger == null || player == null || !passenger.getUuid().equals(player.getUuid())) {
            return instance.getSlipperiness();
        }

        if(!self.getWorld().getBlockState(BlockPos.ofFloored(self.getX(), self.getY() - 0.01 , self.getZ())).isOf(Blocks.BEDROCK)) {
            return instance.getSlipperiness();
        }

        return 1.0F;
    }
}
