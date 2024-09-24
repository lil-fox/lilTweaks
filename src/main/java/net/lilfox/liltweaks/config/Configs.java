package net.lilfox.liltweaks.config;

import net.lilfox.liltweaks.Reference;
import net.lilfox.liltweaks.callback.BedrockCallbacks;
import net.lilfox.liltweaks.callback.HoneyCallbacks;
import net.lilfox.liltweaks.callback.SlimeCallbacks;
import net.lilfox.liltweaks.gui.GuiConfigs;
import net.lilfox.liltweaks.utils.DependenciesUtils;
import net.minecraft.client.world.ClientWorld;
import top.hendrixshen.magiclib.api.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.api.dependency.annotation.Dependency;
import top.hendrixshen.magiclib.api.malilib.annotation.Config;
import top.hendrixshen.magiclib.api.malilib.config.MagicConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigFactory;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigHandler;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigBooleanHotkeyed;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigHotkey;


public class Configs {
    private static final MagicConfigManager cm = Reference.configManager;
    private static final MagicConfigFactory cf = Configs.cm.getConfigFactory();

    public static ClientWorld world;

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed closeInventoryByMouse = cf.newConfigBooleanHotkeyed("closeInventoryByMouse", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed angelBlock = cf.newConfigBooleanHotkeyed("angelBlock", false);

//    @Config(category = ConfigCategory.TWEAKS)
//    public static MagicConfigHotkey angelBlockUse;

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed ultraHaste = cf.newConfigBooleanHotkeyed("permaHaste", false);

//    @Hotkey()
//    @Config(category = "tweaks")
//    public static boolean permaHasteAggressive;


    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed saveBuddyAmethyst = cf.newConfigBooleanHotkeyed("buddingAmethystBreakingRestriction", false);


    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed saveKiddyAmethyst = cf.newConfigBooleanHotkeyed("saveKiddyAmethyst", false);



    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noSlimeBouncing = cf.newConfigBooleanHotkeyed("noSlimeBouncing", false);



    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noSLimeSlowness = cf.newConfigBooleanHotkeyed("noSLimeSlowness", false);



    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noHoneySlowness = cf.newConfigBooleanHotkeyed("noHoneySlowness", false);



    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noHoneyLowJump = cf.newConfigBooleanHotkeyed("noHoneyJump", false);



    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed niceBalls = cf.newConfigBooleanHotkeyed("niceBalls", false);



    @Config(category = ConfigCategory.HOT_KEYS)
    public static MagicConfigHotkey openConfigGui = Configs.cf.newConfigHotkey("openConfigGui", "U,C");
    ;


    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed bormulaOne = cf.newConfigBooleanHotkeyed("bormulaOne", false);


    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed hollyWater = cf.newConfigBooleanHotkeyed("hollyWater", false);

    @Dependencies(require = {@Dependency(DependenciesUtils.itemscroller)})
    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed makeTradesGreatAgain = cf.newConfigBooleanHotkeyed("makeTradesGreatAgain", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noSleepy = cf.newConfigBooleanHotkeyed("npSleepy", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed noAncHorny = cf.newConfigBooleanHotkeyed("noAncHorny", false);

    @Config(category = ConfigCategory.TWEAKS)
    public static MagicConfigBooleanHotkeyed axeEffect = cf.newConfigBooleanHotkeyed("axeEffect", false);

    public static boolean axing = false;

    public Configs() {
    }

    public static void init() {
        cm.parseConfigClass(Configs.class);

        MagicConfigManager.setHotkeyCallback(openConfigGui, GuiConfigs::openGui, true);
        noHoneySlowness.setValueChangeCallback(HoneyCallbacks::noHoneyVelocityChanged);
        noHoneyLowJump.setValueChangeCallback(HoneyCallbacks::noHoneyJumpVelocityChanged);
        noSLimeSlowness.setValueChangeCallback(SlimeCallbacks::noSlimeSlipperyChanged);
        bormulaOne.setValueChangeCallback(BedrockCallbacks::allSlipperyChanged);

        
    }

    public static void postDeserialize(MagicConfigHandler magicConfigHandler) {
        HoneyCallbacks.restoreValues();
        SlimeCallbacks.restoreValues();
        BedrockCallbacks.restoreValues();
    }


    public static class ConfigCategory {
        public static final String TWEAKS = "tweaks";
        public static final String HOT_KEYS = "mod";

        public ConfigCategory() {
        }
    }
}