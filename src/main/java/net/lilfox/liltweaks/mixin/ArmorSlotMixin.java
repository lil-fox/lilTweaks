package net.lilfox.liltweaks.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.screen.slot.ArmorSlot;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorSlot.class)
public class ArmorSlotMixin {
    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    public void acceptAllItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
