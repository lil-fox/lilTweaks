package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.Reference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    //TODO set timer
    @Inject(method = "setScreen", at = @At(value = "HEAD"))
    public void logCurrentScreen(Screen screen, CallbackInfo ci){

        Reference.LOGGER.info("CURRENT SCREEN: {}", screen);
    }
}
