package net.lilfox.liltweaks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import top.hendrixshen.magiclib.api.compat.minecraft.network.chat.ComponentCompat;
import top.hendrixshen.magiclib.api.compat.minecraft.network.chat.MutableComponentCompat;
import top.hendrixshen.magiclib.api.i18n.I18n;
import top.hendrixshen.magiclib.api.malilib.config.MagicConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.GlobalConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigHandler;

public class Reference {

    public static final String MOD_ID = "liltweaks";
    public static final String MOD_NAME = "lilTweaks";
    public static final String MOD_VERSION = "0.1.1";

    public static final String MINECRAFT_ID = "minecraft";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static MagicConfigManager configManager = GlobalConfigManager.getConfigManager(MOD_ID);

    public static MagicConfigHandler configHandler = new MagicConfigHandler(Reference.configManager,1);

    public static String translate(String key, Object... objects) {
        return I18n.tr(Reference.MOD_ID + "." + key, objects);
    }

    public static @NotNull MutableComponentCompat translatable(String key, Object... objects) {
        return ComponentCompat.translatable(Reference.MOD_ID + "." + key, objects);
    }
}
