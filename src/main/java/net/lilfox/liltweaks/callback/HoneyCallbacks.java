package net.lilfox.liltweaks.callback;

import com.sun.jna.platform.win32.OaIdl;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;


public class HoneyCallbacks {


    public static void restoreValues(){
        noHoneyVelocityChanged(null);
        noHoneyJumpVelocityChanged(null);
    }

    public static void noHoneyVelocityChanged(ConfigBoolean configBoolean) {
        if (Configs.noHoneySlowness.getBooleanValue()) {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setVelocityMultiplier(Blocks.STONE.getVelocityMultiplier());
        } else {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setVelocityMultiplier(0.4f);
        }
    }

    public static void noHoneyJumpVelocityChanged(ConfigBoolean configBoolean) {
        if (Configs.noHoneyJump.getBooleanValue()) {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setJumpVelocityMultiplier(Blocks.STONE.getVelocityMultiplier());
        } else {
            ((AbstractBlockAccessor) Blocks.HONEY_BLOCK).setJumpVelocityMultiplier(0.5f);
        }
    }

}
