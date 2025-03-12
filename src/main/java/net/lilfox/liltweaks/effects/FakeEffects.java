package net.lilfox.liltweaks.effects;

import net.lilfox.liltweaks.ModInfo;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class FakeEffects {

    public static final RegistryEntry<StatusEffect> ULTRA_HASTE = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ModInfo.MOD_ID, "ultra_haste"), new UltraHasteEffect());;

    public static void register(){

    }
}
