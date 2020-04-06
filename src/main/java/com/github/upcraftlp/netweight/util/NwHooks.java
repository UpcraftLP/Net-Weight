package com.github.upcraftlp.netweight.util;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.init.NWItems;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

import java.util.Random;
import java.util.function.Predicate;

public class NwHooks {

    public static final String NBT_KEY_FISH_WEIGHT = "Weight";
    public static final int DEFAULT_FISH_WEIGHT = 250; //250 g
    public static final Predicate<ItemStack> FISHING_ROD_BAIT = itemStack -> itemStack.getItem().isIn(NWTags.FISHING_BAITS);
    public static final Predicate<ItemStack> HELD_FISHING_ROD_BAIT = FISHING_ROD_BAIT.or(itemStack -> itemStack.getItem() == Items.CLOCK);
    public static final LootContextParameter<ItemStack> LOOT_CONTEXT_BAIT = new LootContextParameter<>(new Identifier(NetWeight.MODID, "bait"));

    public static ItemStack getDefaultBait() {
        return new ItemStack(NWItems.CREATIVE_BAIT);
    }

    public static ItemStack findBaitType(PlayerEntity player, ItemStack fishingRod) {
        if(!fishingRod.getItem().isIn(NWTags.FISHING_RODS)) { //TODO check for other fishing items
            return ItemStack.EMPTY;
        }
        else {
            Predicate<ItemStack> predicate = HELD_FISHING_ROD_BAIT; //held bait
            if(predicate.test(player.getOffHandStack())) {
                return player.getOffHandStack();
            }
            else if(predicate.test(player.getMainHandStack())) {
                return player.getMainHandStack();
            }
            else {
                predicate = FISHING_ROD_BAIT; //bait that does not need to be in hand
                for(int i = 0; i < player.inventory.getInvSize(); i++) {
                    ItemStack stack = player.inventory.getInvStack(i);
                    if(predicate.test(stack)) {
                        return stack;
                    }
                }
                return player.abilities.creativeMode ? getDefaultBait() : ItemStack.EMPTY;
            }
        }
    }

    //TODO use luck in calculation
    public static void setFishWeight(ItemStack fish, int min, int max, Random random, float luck) {
        CompoundTag tag = fish.getOrCreateSubTag(NetWeight.MODID);
        int weight = min + random.nextInt(Math.max(max - min, 1));
        tag.putInt(NBT_KEY_FISH_WEIGHT, weight);
    }

    public static int getFishWeight(ItemStack stack) {
        CompoundTag tag = stack.getSubTag(NetWeight.MODID);
        return tag != null && tag.contains(NBT_KEY_FISH_WEIGHT, NbtType.INT) ? tag.getInt(NBT_KEY_FISH_WEIGHT) : DEFAULT_FISH_WEIGHT;
    }
}
