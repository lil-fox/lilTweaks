package net.lilfox.liltweaks.impl;

import net.lilfox.liltweaks.ModInfo;
import net.lilfox.liltweaks.config.ConfigsGui;
import top.hendrixshen.magiclib.api.compat.modmenu.ModMenuApiCompat;

public class ModMenu implements ModMenuApiCompat {
    @Override
    public ConfigScreenFactoryCompat<?> getConfigScreenFactoryCompat() {
        return (screen) -> {
            ConfigsGui configGui = new ConfigsGui();

            configGui.setParent(screen);
            return configGui;
        };
    }

    @Override
    public String getModIdCompat() {
        return ModInfo.MOD_ID;
    }
}
