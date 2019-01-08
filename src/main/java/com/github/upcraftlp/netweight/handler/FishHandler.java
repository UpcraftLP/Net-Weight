package com.github.upcraftlp.netweight.handler;

import com.github.upcraftlp.netweight.*;
import com.github.upcraftlp.netweight.net.PacketTopWeight;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NetWeight.MODID)
public class FishHandler {

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        event.getDrops().forEach(stack -> {
            if(!stack.isEmpty() && event.getEntityPlayer() instanceof EntityPlayerMP) {
                if(FishHelper.isItemValidFish(stack)) {
                    EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();
                    if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
                    PotionEffect luck = player.getActivePotionEffect(MobEffects.LUCK);
                    double multiplier = 0.5D;
                    if(luck != null) multiplier += luck.getAmplifier() * 0.5D;
                    int weight = (int) ((Math.abs(player.getRNG().nextGaussian() * multiplier) * 1000) + 35);
                    stack.getTagCompound().setInteger(FishHelper.NBT_KEY_FISH_WEIGHT, weight);
                    int previous = ((EntityPlayerMP) event.getEntityPlayer()).getStatFile().readStat(FishStats.HEAVIEST_FISH_CAUGHT);
                    if(previous < weight) {
                        player.addStat(FishStats.HEAVIEST_FISH_CAUGHT, weight - previous);
                        NetWeight.NETWORK_HANDLER.sendTo(new PacketTopWeight(weight), (EntityPlayerMP) event.getEntityPlayer());
                    }
                    player.addStat(FishStats.TOTAL_FISH_WEIGHT, weight);
                }
            }
        });
    }
}
