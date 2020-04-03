package com.github.upcraftlp.netweight.util;

import com.github.upcraftlp.netweight.NetWeight;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class NwHooks {

    public static final String NBT_KEY_FISH_WEIGHT = "Weight";
    public static final Predicate<ItemStack> FISHING_ROD_BAIT = itemStack -> itemStack.getItem().isIn(NWTags.FISHING_BAITS);
    public static final Predicate<ItemStack> HELD_FISHING_ROD_BAIT = FISHING_ROD_BAIT.or(itemStack -> itemStack.getItem() == Items.CLOCK);
    public static final LootContextParameter<ItemStack> LOOT_CONTEXT_BAIT = new LootContextParameter<>(new Identifier(NetWeight.MODID, "bait"));

    public static ItemStack getDefaultBait() {
        return new ItemStack(Items.WHEAT_SEEDS);
    }

    public static ItemStack findBaitType(PlayerEntity player, ItemStack fishingRod) {
        if(fishingRod.getItem().isIn(NWTags.FISHING_RODS)) { //TODO check for other fishing items
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
}
