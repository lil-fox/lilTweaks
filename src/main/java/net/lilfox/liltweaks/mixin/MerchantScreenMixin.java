package net.lilfox.liltweaks.mixin;


import com.mojang.blaze3d.systems.RenderSystem;
import fi.dy.masa.itemscroller.villager.VillagerDataStorage;
import fi.dy.masa.malilib.util.GuiUtils;
import net.lilfox.liltweaks.config.Configs;
import net.lilfox.liltweaks.utils.IHasTimer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.screen.MerchantScreenHandler;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fi.dy.masa.itemscroller.util.InventoryUtils;

import java.util.Timer;
import java.util.TimerTask;

@Mixin(MerchantScreen.class)
public class MerchantScreenMixin implements IHasTimer{
    @Unique
    private Timer tradeTaskTimer;
    @Unique
    private long startTime;


    @Unique
    private static final Identifier PROGRESS_BAR_BACK =
            Identifier.ofVanilla("hud/experience_bar_background");

    @Unique
    private static final Identifier PROGRESS_BAR_FILL =
            Identifier.ofVanilla("hud/experience_bar_progress");

    @Inject(method = "init", at = @At(value = "TAIL"))
    private void setTradeTimer(CallbackInfo ci){

        if(Configs.makeTradesGreatAgain.getBooleanValue()) {

            startTime = System.currentTimeMillis();

            tradeTaskTimer = new Timer();
            tradeTaskTimer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Screen screen = GuiUtils.getCurrentScreen();
                            if (screen instanceof MerchantScreen) {
                                MerchantScreenHandler container = ((MerchantScreen) screen).getScreenHandler();
                                if (!VillagerDataStorage.getInstance().getFavoritesForCurrentVillager(container).favorites.isEmpty()) {
                                    InventoryUtils.villagerTradeEverythingPossibleWithAllFavoritedTrades();
                                }

                                ClientPlayerEntity player = MinecraftClient.getInstance().player;

                                player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
                                screen.close();
                            }
                        }
                    }
                    , 20000);
        }
    }

    @Inject(method = "render", at = @At(value = "TAIL"))
    private void renderTradeProgress(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci){

        if(Configs.makeTradesGreatAgain.getBooleanValue()) {

            MinecraftClient client = MinecraftClient.getInstance();

            MerchantScreen screen = (MerchantScreen) client.currentScreen;

            if (screen != null) {
                long currentTime = System.currentTimeMillis();
                float progress = (float) (currentTime - startTime) / 20000;

                int guiLeft = screen.x;
                int guiTop = screen.y;

                int villagerY = guiTop + 14;

                int screenWidth = client.getWindow().getScaledWidth();

                int x = (screenWidth - 182) / 2;
                int y = villagerY - 5 - 25;

                RenderSystem.enableBlend();
                context.drawGuiTexture(PROGRESS_BAR_BACK, x, y, 0, 182, 5);
                context.drawGuiTexture(PROGRESS_BAR_FILL, 182, 5, 0,0, x, y, (int) (182 * progress), 5);
                RenderSystem.disableBlend();


            }
        }
    }


    @Override
    public void lilTweaks$setTimer(Timer timer) {
        tradeTaskTimer = timer;
    }

    @Override
    public Timer lilTweaks$getTimer() {
        return tradeTaskTimer;
    }
}
