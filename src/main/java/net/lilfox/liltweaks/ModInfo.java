package net.lilfox.liltweaks;

import top.hendrixshen.magiclib.language.api.I18n;
import top.hendrixshen.magiclib.malilib.impl.ConfigHandler;

public class ModInfo {

    public static final String MOD_ID = "liltweaks";
    public static final String MOD_NAME = "lilTweaks";
    public static ConfigHandler configHandler;

    public ModInfo() {
    }

    public static String translate(String key, Object... objects) {
        return I18n.get("liltweaks." + key, objects);
    }

}
