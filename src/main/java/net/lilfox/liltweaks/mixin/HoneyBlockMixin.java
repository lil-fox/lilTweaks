package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.utils.IHasSomeVelocity;
import net.minecraft.block.HoneyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HoneyBlock.class)
public class HoneyBlockMixin implements IHasSomeVelocity {
    @Unique
    public double originalVelocity;
    @Unique
    public double originalJumpVelocity;

    @Override
    public void lilTweaks$setVelocity(double value) {
        originalVelocity = value;
    }

    @Override
    public void lilTweaks$setJumpVelocity(double value) {
        originalJumpVelocity = value;
    }

    @Override
    public double lilTweaks$getVelocity() {
        return originalVelocity;
    }

    @Override
    public double lilTweaks$getJumpVelocity() {
        return originalJumpVelocity;
    }
}
