package net.lilfox.liltweaks.callback;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;

public class BormulaOne {

    public BormulaOne() {
    }

    public static void restoreValues() {
        onSwitch((ConfigBoolean)null);
    }

    public static void onSwitch(ConfigBoolean configBoolean) {
        if (Configs.bormulaOne.getBooleanValue()) {
            ((AbstractBlockAccessor)Blocks.BEDROCK).setSlipperiness(1.0F);
        } else {
            ((AbstractBlockAccessor)Blocks.BEDROCK).setSlipperiness(Blocks.STONE.getSlipperiness());
        }

    }
}
