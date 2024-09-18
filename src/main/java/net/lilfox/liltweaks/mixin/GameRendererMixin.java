package net.lilfox.liltweaks.mixin;


import net.lilfox.liltweaks.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    MinecraftClient client;
    @ModifyArg(method = "findCrosshairTarget",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileUtil;raycast(" +
                            "Lnet/minecraft/entity/Entity;" +
                            "Lnet/minecraft/util/math/Vec3d;" +
                            "Lnet/minecraft/util/math/Vec3d;" +
                            "Lnet/minecraft/util/math/Box;" +
                            "Ljava/util/function/Predicate;D)" +
                            "Lnet/minecraft/util/hit/EntityHitResult;"))
    private Predicate<Entity> overrideTargetedEntityCheck(Predicate<Entity> predicate)
    {

        if (Configs.niceBalls.getBooleanValue() && (this.client.player != null
                && !this.client.player.isSneaking()
        && (this.client.player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.SNOWBALL
        || this.client.player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.BOW)))
        {
            predicate = predicate.and((entityIn) -> !(entityIn instanceof AbstractMinecartEntity));
        }

        return predicate;
    }
}
