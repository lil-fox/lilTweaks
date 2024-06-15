package net.lilfox.liltweaks.callback;

import com.sun.jna.platform.win32.OaIdl;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;
import top.hendrixshen.magiclib.malilib.impl.ConfigOption;

public class HoneyCallbacks {


    public static void restoreValues(){
        noHoneyVelocityChanged(null);
        noHoneyJumpVelocityChanged(null);
    }

    public static void noHoneyVelocityChanged(ConfigOption configOption) {
        if (Configs.noHoneySlowness) {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setVelocityMultiplier(Blocks.STONE.getVelocityMultiplier());
        } else {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setVelocityMultiplier(0.4f);
        }
    }

    public static void noHoneyJumpVelocityChanged(ConfigOption configOption) {
        if (Configs.noHoneyJump) {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setJumpVelocityMultiplier(Blocks.STONE.getVelocityMultiplier());
        } else {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setJumpVelocityMultiplier(0.5f);
        }
    }
}
