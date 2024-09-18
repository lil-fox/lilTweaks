package net.lilfox.liltweaks.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.lilfox.liltweaks.config.Configs;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SlimeBlock.class, priority = 999)
public class SlimeBlockMixin {

    @ModifyArg(method = "bounce", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setVelocity(DDD)V"), index = 1)
    private double resetVerticalVelocity(double d, @Local(argsOnly = true) Entity entity){
        if(Configs.noSlimeBouncing.getBooleanValue() & entity instanceof PlayerEntity) {
            return 0;
        }
        return d;
    }

    @ModifyArg(method = "onSteppedOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"), index = 0)
    private Vec3d noSlowness(Vec3d velocity, @Local(argsOnly = true) Entity entity){

        if(Configs.noSLimeSlowness.getBooleanValue() & entity instanceof PlayerEntity) {
            return entity.getVelocity().multiply(1, 1, 1);
        }
        return velocity;
    }
}
