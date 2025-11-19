package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.config.Configs;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HappyGhastEntity.class)
public abstract class FastHappiness {
    @Inject(
            method = "getControlledMovementInput",
            at = @At("RETURN"),
            cancellable = true
    )
    private void boost(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfoReturnable<Vec3d> cir) {
        if(Configs.extraHappiness.getBooleanValue()) {
            cir.setReturnValue(cir.getReturnValue().multiply(10));
        }
    }
}
