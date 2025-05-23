package net.lilfox.liltweaks.effects;

import net.lilfox.liltweaks.ModInfo;
import net.lilfox.liltweaks.config.Configs;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

public class FakeEffects {

    public static final RegistryEntry<StatusEffect> ULTRA_HASTE = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ModInfo.MOD_ID, "ultra_haste"), new UltraHasteEffect(Configs.ultraHaste));;
    public static final RegistryEntry<StatusEffect> BORMULA_ONE = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ModInfo.MOD_ID, "bormula_one"), new BormulaOneEffect(Configs.bormulaOne));

    public static void register(){

    }

    public static Stream<RegistryEntry<StatusEffect>> getAll(){
        return Arrays.stream(FakeEffects.class.getDeclaredFields()).filter(f->
                RegistryEntry.class.isAssignableFrom(f.getType())).filter(f->
                Modifier.isStatic(f.getModifiers())).map(f->{
            try {
                f.setAccessible(true);
                return (RegistryEntry<StatusEffect>) f.get(null);
            }catch (IllegalAccessException e){
                throw new RuntimeException(e);
            }
        });
    }
}
