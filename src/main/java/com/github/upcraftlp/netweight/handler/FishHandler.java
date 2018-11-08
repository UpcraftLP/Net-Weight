package com.github.upcraftlp.netweight.handler;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.config.NetWeightConfig;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = NetWeight.MODID)
public class FishHandler {

    public static final String KEY_FISH_WEIGHT = "FishWeight";

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        event.getDrops().forEach(stack -> {
            if(!stack.isEmpty()) { //just in case somebody puts "minecraft:air" in the config :P
                if(Arrays.stream(NetWeightConfig.fishWhitelist).anyMatch(Predicate.isEqual(stack.getItem().getRegistryName().toString()))) { //TODO metadata? nbt?
                    if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
                    PotionEffect luck = event.getEntityPlayer().getActivePotionEffect(MobEffects.LUCK);
                    double multiplier = 0.5D;
                    if(luck != null) multiplier += luck.getAmplifier() * 0.5D;
                    stack.getTagCompound().setShort(KEY_FISH_WEIGHT, (short) ((Math.abs(event.getEntityPlayer().getRNG().nextGaussian() * multiplier) * 1000) + 35));
                }
            }
        });
    }
}
