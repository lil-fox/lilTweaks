package net.lilfox.liltweaks.listener;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.utils.NetworkUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class BlockClickListener implements AttackBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos blockPos, Direction direction) {
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if ((block == Blocks.SMALL_AMETHYST_BUD || block == Blocks.MEDIUM_AMETHYST_BUD || block == Blocks.LARGE_AMETHYST_BUD) && Configs.saveKiddyAmethyst.getBooleanValue()) {
            return ActionResult.SUCCESS;
        } else if (block == Blocks.BUDDING_AMETHYST && Configs.saveBuddyAmethyst.getBooleanValue()) {
            return ActionResult.SUCCESS;
        } else if (Configs.ultraHaste.getBooleanValue() && blockState.calcBlockBreakingDelta(player, world, blockPos) >= 0.7F && blockState.calcBlockBreakingDelta(player, world, blockPos) < 1.0F) {
            //for (int i = 0; i < 5; i++) {
            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockPos, direction));
            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, blockPos, direction));
            //}
            world.breakBlock(blockPos,true);
            if (MinecraftClient.getInstance().isInSingleplayer()) {
                ItemStack stack = player.getStackInHand(hand);
                if (!stack.isEmpty() && stack.isDamageable()) {

                    int damageAmount = stack.isSuitableFor(blockState) ? 1 : 2;
                    stack.setDamage(damageAmount);
                }
            }
            return ActionResult.PASS;

        } else {
            return ActionResult.PASS;
        }
    }
}
