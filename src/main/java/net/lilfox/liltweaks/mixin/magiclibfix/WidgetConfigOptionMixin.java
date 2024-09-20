package net.lilfox.liltweaks.mixin.magiclibfix;

import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ConfigButtonKeybind;
import fi.dy.masa.malilib.gui.interfaces.IKeybindConfigGui;
import fi.dy.masa.malilib.gui.widgets.*;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigHotkey;


@Mixin(
        value = {WidgetConfigOption.class},
        priority = 999,
        remap = false
)
public abstract class WidgetConfigOptionMixin extends WidgetConfigOptionBase<GuiConfigsBase.ConfigOptionWrapper> {

    public WidgetConfigOptionMixin(int x, int y, int width, int height, WidgetListConfigOptionsBase<?, ?> parent, GuiConfigsBase.ConfigOptionWrapper entry, int listIndex) {
        super(x, y, width, height, parent, entry, listIndex);
    }

    //TODO rewrite completely
    @Inject(method = "addHotkeyConfigElements",
            at = @At(value = "HEAD"), cancellable = true)
    private void deleteIdioticTriggerButtonFromMagicLibNew(int x, int y, int configWidth, String configName, IHotkey hotkey, CallbackInfo ci) {
        configWidth -= 22; // adjust the width to match other configs due to the settings widget
        IKeybind keybind = hotkey.getKeybind();
        ConfigButtonKeybind keybindButton = new ConfigButtonKeybind(x, y, configWidth, 20, keybind, ((WidgetConfigOptionAccessor)this).getHost());
        x += configWidth + 2;

        this.addWidget(new WidgetKeybindSettings(x, y, 20, 20, keybind, configName, this.parent, ((WidgetConfigOptionAccessor)this).getHost().getDialogHandler()));
        x += 22;

        this.addButton(keybindButton, ((WidgetConfigOptionAccessor)this).getHost().getButtonPressListener());
        ((WidgetConfigOptionInvoker)this).invokeAddKeybindResetButton(x, y, keybind, keybindButton);

        ci.cancel();
    }




}