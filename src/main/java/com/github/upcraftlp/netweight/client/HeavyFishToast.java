package com.github.upcraftlp.netweight.client;

import com.github.upcraftlp.netweight.util.NwMessages;
import com.github.upcraftlp.netweight.util.NwStatFormatters;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;

public class HeavyFishToast implements Toast {
    @Override
    public Visibility draw(ToastManager manager, long currentTime) {
        if(this.newDisplay) {
            this.firstDrawTime = currentTime;
            this.newDisplay = false;
        }
        manager.getGame().getTextureManager().bindTexture(TOASTS_TEX);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.blit(0, 0, 0, 64, 160, 32);
        manager.getGame().textRenderer.draw(this.title, 18, 7, -256);
        manager.getGame().textRenderer.draw(this.weightString, 18, 18, -1);
        return currentTime - this.firstDrawTime < 5000L ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
    }


    private long firstDrawTime;
    private boolean newDisplay;
    private final String title = I18n.translate(NwMessages.TOAST_NEW_TOP_WEIGHT);
    private String weightString;

    public HeavyFishToast(int weight) {
        this.weightString = NwStatFormatters.WEIGHT.format(weight);
    }

    public static void addOrUpdate(int weight) {

        ToastManager toastManager = MinecraftClient.getInstance().getToastManager();
        HeavyFishToast toast = toastManager.getToast(HeavyFishToast.class, Toast.field_2208);
        if(toast != null) {
            toast.updateWeight(weight);
        }
        else {
            toastManager.add(new HeavyFishToast(weight));
        }
    }

    private void updateWeight(int weight) {
        this.weightString = NwStatFormatters.WEIGHT.format(weight);
        this.newDisplay = true;
    }
}
