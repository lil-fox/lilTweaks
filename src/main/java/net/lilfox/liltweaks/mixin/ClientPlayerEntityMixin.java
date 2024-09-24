package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.config.Configs;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {


    @Inject(at = @At("HEAD"), method = "sendAbilitiesUpdate", cancellable = true)
    private void suppressUpdateFlyPacket(CallbackInfo ci) {
        if (Configs.hollyWater.getBooleanValue()) ci.cancel();
        if (Configs.axeEffect.getBooleanValue() && Configs.axing) ci.cancel();
    }
}
