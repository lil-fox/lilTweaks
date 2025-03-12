package net.lilfox.liltweaks.config;

import net.lilfox.liltweaks.ModInfo;
import net.lilfox.liltweaks.callback.BormulaOne;
import top.hendrixshen.magiclib.api.malilib.annotation.Config;
import top.hendrixshen.magiclib.api.malilib.config.MagicConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigFactory;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigBooleanHotkeyed;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigHotkey;

public class Configs {

    private static final MagicConfigManager cm = ModInfo.CONFIG_MANAGER;
    private static final MagicConfigFactory cf = cm.getConfigFactory();

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed closeInventoryByMouse =
            cf.newConfigBooleanHotkeyed("closeInventoryByMouse", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed ultraHaste =
            cf.newConfigBooleanHotkeyed("ultraHaste", false);

//    @Config(category = ConfigCategory.TWEAKS)
//    public static MagicConfigBooleanHotkeyed _excavator =
//            cf.newConfigBooleanHotkeyed("_excavator", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed saveBuddyAmethyst =
            cf.newConfigBooleanHotkeyed("saveBuddyAmethyst", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed saveKiddyAmethyst =
            cf.newConfigBooleanHotkeyed("saveKiddyAmethyst", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed niceBalls =
            cf.newConfigBooleanHotkeyed("niceBalls", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed bormulaOne =
            cf.newConfigBooleanHotkeyed("bormulaOne", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noSleepy =
            cf.newConfigBooleanHotkeyed("noSleepy", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noAncHorny =
            cf.newConfigBooleanHotkeyed("noAncHorny", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed axeEffect =
            cf.newConfigBooleanHotkeyed("axeEffect", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed angelBlock =
            cf.newConfigBooleanHotkeyed("angelBlock", false);

    public static boolean axing;


    @Config(category = ConfigCategory.SETTINGS)
    public static MagicConfigHotkey openConfigGui = cf.newConfigHotkey("openConfigGui", "U,C");


    public static class ConfigCategory {
        public static final String TWEAKS = "tweaks";
        public static final String SETTINGS = "settings";
    }

    public static void init() {
        cm.parseConfigClass(Configs.class);
        MagicConfigManager.setHotkeyCallback(openConfigGui, ConfigsGui::openGui, true);

        bormulaOne.setValueChangeCallback(BormulaOne::onSwitch);
    }

}
