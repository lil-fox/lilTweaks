package net.lilfox.liltweaks.callback;

import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;
import top.hendrixshen.magiclib.malilib.impl.ConfigOption;

public class SlimeCallbacks {
    public static void restoreValues(){
        noSlimeSlipperyChanged(null);
    }

    public static void noSlimeSlipperyChanged(ConfigOption configOption) {
        if (Configs.noSLimeSlowness) {
            ((AbstractBlockAccessor) Blocks.SLIME_BLOCK).setSlipperiness(Blocks.STONE.getSlipperiness());
        } else {
            ((AbstractBlockAccessor) Blocks.SLIME_BLOCK).setSlipperiness(0.8f);
        }
    }
}
