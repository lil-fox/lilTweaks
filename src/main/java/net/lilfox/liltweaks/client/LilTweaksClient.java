package net.lilfox.liltweaks.client;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.lilfox.liltweaks.Reference;

import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.handler.InputHandler;

import net.lilfox.liltweaks.listener.BlockClickListener;

public class LilTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitializationHandler.getInstance().registerInitializationHandler(
                ()-> ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID,Reference.configHandler)
        );
        Configs.init();
        AttackBlockCallback.EVENT.register(new BlockClickListener());
        InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());
        //Reference.configHandler.setPostDeserializeCallback(Configs::postDeserialize);
    }
}
