package net.lilfox.liltweaks.client;

import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ClientModInitializer;
import net.lilfox.liltweaks.callback.HoneyCallbacks;
import net.lilfox.liltweaks.init.InitHandler;

public class LilTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
    }
}
