package net.lilfox.liltweaks.mixin;


import net.lilfox.liltweaks.utils.IHasTimer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {

//
//    @Inject(method = "removed", at = @At(value = "HEAD"))
//    private void resetTimer(CallbackInfo ci){
//        if((Object)this instanceof MerchantScreen){
//            if(((IHasTimer)this).getTimer() != null)
//                ((IHasTimer)this).getTimer().cancel();
//        }
//    }
}
