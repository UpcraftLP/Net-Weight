package com.github.upcraftlp.netweight.client;

import com.github.upcraftlp.netweight.*;
import com.github.upcraftlp.netweight.handler.FishHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = NetWeight.MODID, value = Side.CLIENT)
public class TooltipHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onRenderTooltip(ItemTooltipEvent event) {
        if(!event.getItemStack().isEmpty() && event.getItemStack().hasTagCompound()) {
            NBTTagCompound nbt = event.getItemStack().getTagCompound();
            if(nbt.hasKey(FishHandler.KEY_FISH_WEIGHT, Constants.NBT.TAG_INT)) {
                event.getToolTip().add(FishStats.Type.WEIGHT.format(nbt.getInteger(FishHandler.KEY_FISH_WEIGHT)));
            }
        }
    }
}
