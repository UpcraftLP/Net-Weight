package com.github.upcraftlp.netweight.handler;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.config.NetWeightConfig;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

public class FishHelper {

    public static final String NBT_KEY_FISH_WEIGHT = "FishWeight";
    public static final int DEFAULT_WEIGHT = 250;
    private static final Set<Item> VALID_FISHES = new HashSet<>();

    public static boolean isItemValidFish(ItemStack stack) {
        return VALID_FISHES.contains(stack.getItem());
    }

    public static void syncConfigValues() {
        VALID_FISHES.clear();
        for(String s : NetWeightConfig.fishWhitelist) {
            if(!s.trim().isEmpty()) {
                ResourceLocation rl = new ResourceLocation(s);
                if(!rl.getPath().isEmpty()) {
                    Item item = ForgeRegistries.ITEMS.getValue(rl);
                    if(item != null) {
                        VALID_FISHES.add(item);
                    }
                    else {
                        NetWeight.getLogger().error("unable to find item {}, it will be ignored when checking for valid fishes");
                    }
                }
                else {
                    NetWeight.getLogger().error("invalid config value: {} is not a valid resource location!", s);
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static int getFishWeight(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_KEY_FISH_WEIGHT, Constants.NBT.TAG_INT) ? stack.getTagCompound().getInteger(NBT_KEY_FISH_WEIGHT) : DEFAULT_WEIGHT;
    }
}
