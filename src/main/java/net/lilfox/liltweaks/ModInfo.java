package net.lilfox.liltweaks;

import top.hendrixshen.magiclib.api.malilib.config.MagicConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.GlobalConfigManager;
import top.hendrixshen.magiclib.impl.malilib.config.MagicConfigHandlerImpl;

public class ModInfo {

    public static final String MOD_ID = "liltweaks";


    public static final MagicConfigManager CONFIG_MANAGER = GlobalConfigManager
            .getConfigManager(MOD_ID);

    public static final MagicConfigHandlerImpl CONFIG_HANDLER = new MagicConfigHandlerImpl(CONFIG_MANAGER, 1);
}
