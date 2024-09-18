package net.lilfox.liltweaks.callback;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.lang.reflect.Field;

public class BedrockCallbacks {


    public static void restoreValues() {
        allSlipperyChanged(null);
    }

    public static void allSlipperyChanged(ConfigBoolean configBoolean){

        if(Configs.bormulaOne.getBooleanValue()) {
            ((AbstractBlockAccessor) Blocks.BEDROCK).setSlipperiness(1f);
        }
        else {
            ((AbstractBlockAccessor) Blocks.BEDROCK).setSlipperiness(Blocks.STONE.getSlipperiness());
        }
    }

}
