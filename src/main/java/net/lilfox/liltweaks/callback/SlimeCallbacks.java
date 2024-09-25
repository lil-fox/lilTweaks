package net.lilfox.liltweaks.callback;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;


public class SlimeCallbacks {
    public static void restoreValues(){
        noSlimeSlipperyChanged(null);
    }

    public static void noSlimeSlipperyChanged(ConfigBoolean configBoolean) {
        if (Configs.noSlimeSlowness.getBooleanValue()) {
            ((AbstractBlockAccessor) Blocks.SLIME_BLOCK).setSlipperiness(Blocks.STONE.getSlipperiness());
        } else {
            ((AbstractBlockAccessor) Blocks.SLIME_BLOCK).setSlipperiness(0.8f);
        }
    }
}
