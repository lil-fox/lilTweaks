package net.lilfox.liltweaks.gui;


import fi.dy.masa.malilib.gui.GuiBase;
import net.lilfox.liltweaks.Reference;
import org.jetbrains.annotations.NotNull;
import top.hendrixshen.magiclib.api.i18n.I18n;
import top.hendrixshen.magiclib.impl.malilib.config.gui.MagicConfigGui;
import top.hendrixshen.magiclib.util.collect.ValueContainer;

public class GuiConfigs extends MagicConfigGui {

    private static GuiConfigs INSTANCE = null;

    public GuiConfigs() {
        super(Reference.MOD_ID, Reference.configManager, I18n.tr(String.format("%s.config.gui.title", Reference.MOD_ID), Reference.MOD_VERSION));
    }

    @Override
    public void init() {
        super.init();
        GuiConfigs.INSTANCE = this;
    }

    @Override
    public void removed() {
        super.removed();
        GuiConfigs.INSTANCE = null;
    }

    //    @Override
//    public boolean hideUnAvailableConfigs() {
//        return Configs.hideUnavailableConfigs.getBooleanValue();
//    }

    public static @NotNull ValueContainer<GuiConfigs> getCurrentInstance() {
        return ValueContainer.ofNullable(GuiConfigs.INSTANCE);
    }

    public static void openGui() {
        GuiBase.openGui(new GuiConfigs());
    }
}