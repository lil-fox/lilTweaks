package net.lilfox.liltweaks.mixin.ultrahaste;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ClientPlayerInteractionManager.class})
public interface ClientPlayerInteractionManagerAccessor {
    @Accessor
    void setBlockBreakingCooldown(int var1);
}
