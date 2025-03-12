package net.lilfox.liltweaks.handler;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.effects.FakeEffects;
import net.minecraft.entity.effect.StatusEffectInstance;

public class FakeEffectsHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {

                if(Configs.ultraHaste.getBooleanValue()) {

                    boolean hasUltraHaste = client.player.getStatusEffect(FakeEffects.ULTRA_HASTE) != null;
                    if (!hasUltraHaste) {
                        client.player.addStatusEffect(new StatusEffectInstance(FakeEffects.ULTRA_HASTE, -1, 0, false, true, true));

                    }
                }
                else{
                    client.player.removeStatusEffectInternal(FakeEffects.ULTRA_HASTE);
                }
            }

        });
    }
}
