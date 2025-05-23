package net.lilfox.liltweaks.handler;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.effects.FakeEffect;
import net.lilfox.liltweaks.effects.FakeEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;

public class FakeEffectsHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {

                FakeEffects.getAll().forEach(entry ->{
                    StatusEffect effect = Registries.STATUS_EFFECT.get(entry.getKey().orElseThrow());

                    if (effect == null) return;

                    boolean isEnabled = ((FakeEffect)effect).isEnabled();
                    boolean isRendered = client.player.hasStatusEffect(entry);

                    if (isEnabled && !isRendered){
                        client.player.addStatusEffect(new StatusEffectInstance(entry, -1, 0, true, true, true));
                    } else if (!isEnabled && isRendered) {
                        client.player.removeStatusEffectInternal(entry);
                    }
                });

            }

        });
    }
}
