package net.lilfox.liltweaks.mixin;


import net.lilfox.liltweaks.config.Configs;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientPlayerInteractionManager.class})
public class ClientPlayerInteractionManagerMixin {

    @Inject(
            method = {"updateBlockBreakingProgress"},
            at = {@At("HEAD")}
    )
    private void dropCooldown(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (Configs.permaHaste.getBooleanValue()) {
            ((ClientPlayerInteractionManagerAccessor)this).setBlockBreakingCooldown(0);
        }

    }
}
