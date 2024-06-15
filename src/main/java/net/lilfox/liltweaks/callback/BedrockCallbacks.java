package net.lilfox.liltweaks.callback;

import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import top.hendrixshen.magiclib.malilib.impl.ConfigOption;

import java.lang.reflect.Field;

public class BedrockCallbacks {


    public static void restoreValues() {
        allSlipperyChanged(null);
    }

    public static void allSlipperyChanged(ConfigOption option){

        if(Configs.bormulaOne) {
            ((AbstractBlockAccessor) Blocks.BEDROCK).setSlipperiness(1f);
        }
        else {
            ((AbstractBlockAccessor) Blocks.BEDROCK).setSlipperiness(Blocks.STONE.getSlipperiness());
        }
    }

}
