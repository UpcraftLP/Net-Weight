package com.github.upcraftlp.netweight.client;

import com.github.upcraftlp.netweight.FishStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class HeavyFishToast implements IToast {

    private long firstDrawTime;
    private boolean newDisplay;
    private final String title = I18n.format("toast.net_weight.newTopWeight");
    private String weightString;

    public HeavyFishToast(int weight) {
        this.weightString = FishStats.Type.WEIGHT.format(weight);
    }

    public static void addOrUpdate(int weight) {
        GuiToast toastGui = Minecraft.getMinecraft().getToastGui();
        HeavyFishToast toast = toastGui.getToast(HeavyFishToast.class, IToast.NO_TOKEN);
        if(toast != null) {
            toast.updateWeight(weight);
        }
        else {
            toastGui.add(new HeavyFishToast(weight));
        }
    }

    private void updateWeight(int weight) {
        this.weightString = FishStats.Type.WEIGHT.format(weight);
        this.newDisplay = true;
    }

    @Override
    public Visibility draw(GuiToast toastGui, long delta) {
        if(this.newDisplay) {
            this.firstDrawTime = delta;
            this.newDisplay = false;
        }

        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 64, 160, 32);

        toastGui.getMinecraft().fontRenderer.drawString(this.title, 18, 7, -256);
        toastGui.getMinecraft().fontRenderer.drawString(this.weightString, 18, 18, -1);

        return delta - this.firstDrawTime < 5000L ? IToast.Visibility.SHOW : IToast.Visibility.HIDE;
    }
}
