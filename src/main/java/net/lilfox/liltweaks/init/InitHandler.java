package net.lilfox.liltweaks.init;

import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.lilfox.liltweaks.ModInfo;
import net.lilfox.liltweaks.callback.HoneyCallbacks;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.handler.InputHandler;
import net.lilfox.liltweaks.listener.BlockClickListener;
import top.hendrixshen.magiclib.malilib.impl.ConfigHandler;
import top.hendrixshen.magiclib.malilib.impl.ConfigManager;
public class InitHandler implements IInitializationHandler {
    public InitHandler() {
    }

    public void registerModHandlers() {
        ConfigManager cm = ConfigManager.get("liltweaks");
        cm.parseConfigClass(Configs.class);
        ModInfo.configHandler = new ConfigHandler("liltweaks", cm, 1);
        ModInfo.configHandler.postDeserializeCallback = Configs::postDeserialize;
        ConfigHandler.register(ModInfo.configHandler);
        Configs.init(cm);
        AttackBlockCallback.EVENT.register(new BlockClickListener());
        InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());

    }
}
