package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.config.Configs;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> {
    @Shadow
    protected int x;
    @Shadow
    protected int y;
    @Shadow
    @Final
    protected T handler;


    @Shadow
    public abstract void close();

    @Shadow protected abstract boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button);

    @Inject(
            method = {"mouseClicked"},
            at = {@At("HEAD")}
    )
    protected void closeOnClick(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (Configs.closeInventoryByMouse && this.isClickOutsideBounds(mouseX, mouseY, this.x, this.y, button) && this.handler.getCursorStack().isEmpty()) {
            this.close();
        }

    }
}
