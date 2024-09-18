package net.lilfox.liltweaks.handler;

import fi.dy.masa.malilib.hotkeys.IMouseInputHandler;
import fi.dy.masa.malilib.util.GuiUtils;
import fi.dy.masa.malilib.util.PositionUtils;
import net.lilfox.liltweaks.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class InputHandler implements IMouseInputHandler {
    private static final InputHandler INSTANCE = new InputHandler();

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return INSTANCE;
    }

    public boolean onMouseClick(int mouseX, int mouseY, int eventButton, boolean eventButtonState) {

        MinecraftClient mc = MinecraftClient.getInstance();

        if (GuiUtils.getCurrentScreen() == null
                && mc.player != null
                && eventButtonState
                && mc.options.useKey.matchesMouse(eventButton)
                && Configs.angelBlock.getBooleanValue()
                &&  mc.crosshairTarget != null
                && mc.crosshairTarget.getType() == HitResult.Type.MISS) { //Configs.angelBlockUse.getKeybind().isKeybindHeld() &&
            BlockPos posFront = PositionUtils.getPositionInfrontOfEntity(mc.player);

            if (mc.world.isAir(posFront) || !mc.world.getBlockState(posFront).getFluidState().isEmpty()) {
                Direction facing = PositionUtils.getClosestLookingDirection(mc.player).getOpposite();
                Vec3d hitVec = PositionUtils.getHitVecCenter(posFront, facing);
                BlockHitResult context = new BlockHitResult(hitVec, facing, posFront, false);
                ItemStack stack = mc.player.getMainHandStack();
                if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, context);
                    return true;
                }

                stack = mc.player.getOffHandStack();
                if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                    mc.interactionManager.interactBlock(mc.player, Hand.OFF_HAND, context);
                    return true;
                }
            }
        }

        return false;
    }
}
