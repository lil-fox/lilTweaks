package net.lilfox.liltweaks.client;

import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.lilfox.liltweaks.config.ConfigInitializer;
import net.lilfox.liltweaks.effects.FakeEffects;
import net.lilfox.liltweaks.handler.FakeEffectsHandler;
import net.lilfox.liltweaks.handler.InputHandler;
import net.lilfox.liltweaks.listener.BlockClickListener;

public class LilTweaksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ConfigInitializer.init();
        AttackBlockCallback.EVENT.register(new BlockClickListener());

        FakeEffects.register();
        FakeEffectsHandler.register();

        InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());

    }
}
