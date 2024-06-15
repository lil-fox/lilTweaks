package net.lilfox.liltweaks.mixin;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor {
    @Accessor("velocityMultiplier")
    @Mutable
    void setVelocityMultiplier(float velocityMultiplier);

    @Accessor("jumpVelocityMultiplier")
    @Mutable
    void setJumpVelocityMultiplier(float jumpVelocityMultiplier);


    @Accessor("slipperiness")
    @Mutable
    void setSlipperiness(float velocityMultiplier);
}
