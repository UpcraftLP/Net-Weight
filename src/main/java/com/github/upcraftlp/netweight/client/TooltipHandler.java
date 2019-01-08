package com.github.upcraftlp.netweight.client;

import com.github.upcraftlp.netweight.*;
import com.github.upcraftlp.netweight.handler.FishHelper;
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
        if(!event.getItemStack().isEmpty()) {
            NBTTagCompound nbt = event.getItemStack().getTagCompound();
            int weight = 0;
            if(nbt != null && nbt.hasKey(FishHelper.NBT_KEY_FISH_WEIGHT, Constants.NBT.TAG_INT)) {
                weight = nbt.getInteger(FishHelper.NBT_KEY_FISH_WEIGHT);
            }
            else if(FishHelper.isItemValidFish(event.getItemStack())) {
                weight = FishHelper.DEFAULT_WEIGHT;
            }

            if(weight != 0) {
                event.getToolTip().add(FishStats.Type.WEIGHT.format(weight));
            }
        }
    }
}
