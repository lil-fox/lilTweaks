package net.lilfox.liltweaks.listener;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.utils.NetworkUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockClickListener implements AttackBlockCallback {
    public BlockClickListener() {
    }

    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos blockPos, Direction direction) {
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if ((block == Blocks.SMALL_AMETHYST_BUD || block == Blocks.MEDIUM_AMETHYST_BUD || block == Blocks.LARGE_AMETHYST_BUD) && Configs.saveKiddyAmethyst.getBooleanValue()) {
            return ActionResult.SUCCESS;
        } else if (block == Blocks.BUDDING_AMETHYST && Configs.buddingAmethystBreakingRestriction.getBooleanValue()) {
            return ActionResult.SUCCESS;
        } else if (Configs.permaHaste.getBooleanValue() && blockState.calcBlockBreakingDelta(player, world, blockPos) >= 0.7F && blockState.calcBlockBreakingDelta(player, world, blockPos) < 1.0F) {
            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, blockPos, direction));
            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, blockPos, direction));
            world.breakBlock(blockPos, true, player);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }


}
