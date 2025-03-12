package net.lilfox.liltweaks.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class UltraHasteEffect extends StatusEffect {


    protected UltraHasteEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16711782, ParticleTypes.CHERRY_LEAVES);

    }

}
