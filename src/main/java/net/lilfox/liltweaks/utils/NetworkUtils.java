package net.lilfox.liltweaks.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;

public class NetworkUtils {
    public NetworkUtils() {
    }

    public static void sendPacket(Packet<?> packet) {
        if (MinecraftClient.getInstance().getNetworkHandler() != null) {
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(packet);
        }

    }
}