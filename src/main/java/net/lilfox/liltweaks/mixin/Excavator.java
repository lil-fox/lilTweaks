package net.lilfox.liltweaks.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerInteractionManager.class)
public class Excavator {

//    @Inject(method = "attackBlock", at=@At("HEAD"),cancellable = true)
//    private void excavate(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
//
//
//        if(Configs._excavator.getBooleanValue()){
//            MinecraftClient client = MinecraftClient.getInstance();
//
//            if(client.player == null || client.world == null) return;
//
//            ItemStack tool = client.player.getMainHandStack();
//
//            if(!(tool.getItem() instanceof PickaxeItem)) return;
//
//            World world = client.world;
//
//            for( int x = -1; x <= 1; x++ ){
//                for( int y = -1; y <= 1; y++ ){
//                    for ( int z = -1; z <= 1; z++){
//                        BlockPos newPos = pos.add(x,y,z);
//                        if(newPos.equals(pos)) continue;
//                        BlockState state = world.getBlockState(newPos);
//
//                        if(!state.isAir()){
//                            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, newPos, direction));
//                            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, newPos, direction));
//                        }
//                    }
//                }
//            }
//
//            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction));
//            NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, direction));
//
//            cir.setReturnValue(true);
//        }
//
//    }
}
