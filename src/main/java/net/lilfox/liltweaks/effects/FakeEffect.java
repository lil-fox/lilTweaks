package net.lilfox.liltweaks.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Colors;
import top.hendrixshen.magiclib.impl.malilib.config.option.MagicConfigBooleanHotkeyed;

public class FakeEffect extends StatusEffect {

    private final MagicConfigBooleanHotkeyed config;

    protected FakeEffect(MagicConfigBooleanHotkeyed config) {
        super(StatusEffectCategory.BENEFICIAL, Colors.YELLOW);
        this.config = config;
    }

    public boolean isEnabled() {
        return config.getBooleanValue();
    }
}
