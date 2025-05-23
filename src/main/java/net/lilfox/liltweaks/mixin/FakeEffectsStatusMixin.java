package net.lilfox.liltweaks.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import net.lilfox.liltweaks.ModInfo;
import net.lilfox.liltweaks.effects.FakeEffect;

import net.lilfox.liltweaks.effects.UltraHasteEffect;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InGameHud.class)
public class FakeEffectsStatusMixin {

    @ModifyArg(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"), index = 0)
    private Identifier fakeEffectColor(Identifier texture, @Local StatusEffectInstance statusEffectInstance){

//        System.out.println(statusEffectInstance.getEffectType().getType());
//        System.out.println(UltraHasteEffect.class);

        if(Registries.STATUS_EFFECT.get(statusEffectInstance.getEffectType().getKey().orElseThrow()) instanceof FakeEffect){
//            System.out.println("###############FAKE####################");
            return Identifier.of(ModInfo.MOD_ID, "hud/effect_background_fake");
        }
        return texture;
    }
}
