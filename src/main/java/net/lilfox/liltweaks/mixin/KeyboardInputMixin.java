package net.lilfox.liltweaks.mixin;

import net.lilfox.liltweaks.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {

    @Inject(at = @At("RETURN"), method = "tick(ZF)V")
    private void tick(boolean slowDown, float f, CallbackInfo ci) {
        if (Configs.hollyWater.getBooleanValue()) {
            ClientWorld world = MinecraftClient.getInstance().world;
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (world != null && player != null) {
                if (!world.getBlockState(new BlockPos(new Vec3i((int) player.getPos().x, (int) player.getPos().y, (int) player.getPos().z))).getFluidState().isEmpty() ||
                        !world.getBlockState(new BlockPos((new Vec3i((int) player.getPos().x, (int) player.getPos().y, (int) player.getPos().z)).add(0, -1, 0))).getFluidState().isEmpty()) {
                    player.getAbilities().flying = true;
                } else {
                    player.getAbilities().flying = false;
                }
            }
        }
    }
}
