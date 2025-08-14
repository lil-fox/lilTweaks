package net.lilfox.liltweaks.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.lilfox.liltweaks.config.Configs;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBoatEntity.class)
public class BormulaOne{


    @ModifyExpressionValue(method = "getNearbySlipperiness", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float bormulaOne(float original){


        AbstractBoatEntity self = (AbstractBoatEntity) (Object) this;

        if(!(Configs.bormulaOne.getBooleanValue() && self.isOnGround())) {
            return original;
        }

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Entity passenger = self.getControllingPassenger();

        if(passenger == null || player == null || !passenger.getUuid().equals(player.getUuid())) {
            return original;
        }

        if(!self.getWorld().getBlockState(BlockPos.ofFloored(self.getX(), self.getY() - 0.01 , self.getZ())).isOf(Blocks.BEDROCK)) {
            return original;
        }

        return 1.0F;
    }
}
