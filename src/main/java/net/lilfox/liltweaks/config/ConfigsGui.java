package net.lilfox.liltweaks.config;

import fi.dy.masa.malilib.gui.GuiBase;
import net.lilfox.liltweaks.ModInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.hendrixshen.magiclib.impl.malilib.config.gui.MagicConfigGui;
import top.hendrixshen.magiclib.util.collect.ValueContainer;

public class ConfigsGui extends MagicConfigGui {

    @Nullable
    private static ConfigsGui INSTANCE = null;

    public ConfigsGui() {
        super(ModInfo.MOD_ID, ModInfo.CONFIG_MANAGER, "lilTweaks.title");

    }

    @Override
    public void init() {
        super.init();
        INSTANCE = this;
    }

    @Override
    public void removed() {
        super.removed();
        INSTANCE = null;
    }


    public static void openGui() {
        GuiBase.openGui(new ConfigsGui());
    }

    @Override
    public boolean hideUnAvailableConfigs() {
        return true;
    }

    public static @NotNull ValueContainer<ConfigsGui> getCurrentInstance() {
        return ValueContainer.ofNullable(INSTANCE);
    }
}
