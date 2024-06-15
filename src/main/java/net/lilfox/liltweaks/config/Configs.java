package net.lilfox.liltweaks.config;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import net.lilfox.liltweaks.callback.BedrockCallbacks;
import net.lilfox.liltweaks.callback.HoneyCallbacks;
import net.lilfox.liltweaks.callback.SlimeCallbacks;
import net.lilfox.liltweaks.gui.ConfigsGui;
import net.minecraft.client.MinecraftClient;
import top.hendrixshen.magiclib.malilib.api.annotation.Config;
import top.hendrixshen.magiclib.malilib.api.annotation.Hotkey;
import top.hendrixshen.magiclib.malilib.impl.ConfigHandler;
import top.hendrixshen.magiclib.malilib.impl.ConfigManager;

public class Configs {
    private static final String CONFIG_FILE_NAME = "liltweaks.json";
    public static final int CONFIG_VERSION = 1;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean closeInventoryByMouse;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean angelBlock;
    @Hotkey()
    @Config(category = "tweaks")
    public static ConfigHotkey angelBlockUse;
    @Hotkey()
    @Config(category = "tweaks")
    public static boolean permaHaste;
//    @Hotkey()
//    @Config(category = "tweaks")
//    public static boolean permaHasteAggressive;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean buddingAmethystBreakingRestriction;
    @Hotkey()
    @Config(category = "tweaks")
    public static boolean saveKiddyAmethyst;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean noSlimeBouncing;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean noSLimeSlowness;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean noHoneySlowness;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean noHoneyJump;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean niceBalls;

    @Hotkey(hotkey = "U,C")
    @Config(category = "mod")
    public static ConfigHotkey openConfigGui;

    @Hotkey()
    @Config(category = "tweaks")
    public static boolean bormulaOne;

    public Configs() {
    }

    public static void init(ConfigManager cm) {
        openConfigGui.getKeybind().setCallback((keyAction, iKeybind) -> {
            ConfigsGui screen = ConfigsGui.getInstance();
            screen.setParent( MinecraftClient.getInstance().currentScreen);
            MinecraftClient.getInstance().setScreen(screen);
            return true;
        });
        cm.setValueChangeCallback("noHoneySlowness", HoneyCallbacks::noHoneyVelocityChanged);
        cm.setValueChangeCallback("noHoneyJump", HoneyCallbacks::noHoneyJumpVelocityChanged);
        cm.setValueChangeCallback("noSLimeSlowness",SlimeCallbacks::noSlimeSlipperyChanged);
        cm.setValueChangeCallback("bormulaOne", BedrockCallbacks::allSlipperyChanged);

    }

    public static void postDeserialize(ConfigHandler configHandler) {
        HoneyCallbacks.restoreValues();
        SlimeCallbacks.restoreValues();
        BedrockCallbacks.restoreValues();
    }

    public static class ConfigCategory {
        public static final String HOT_KEYS = "mod";

        public ConfigCategory() {
        }
    }
}