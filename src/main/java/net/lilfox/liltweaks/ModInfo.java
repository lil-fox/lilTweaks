package net.lilfox.liltweaks;

import top.hendrixshen.magiclib.api.malilib.config.MagicConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.GlobalConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigHandler;

public class ModInfo {

    public static final String MOD_ID = "liltweaks";


    public static final MagicConfigManager CONFIG_MANAGER = GlobalConfigManager
            .getConfigManager(MOD_ID);

    public static final MagicConfigHandler CONFIG_HANDLER = new MagicConfigHandler(CONFIG_MANAGER, 1);
}
