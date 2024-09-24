package net.lilfox.liltweaks.callback;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.mixin.AbstractBlockAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class JesusCallback {

    public static void walk(ConfigBoolean configBoolean) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if(player != null){
            player.getAbilities().flying = configBoolean.getBooleanValue();
        }
    }
}
