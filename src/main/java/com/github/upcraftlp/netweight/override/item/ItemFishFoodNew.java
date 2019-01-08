package com.github.upcraftlp.netweight.override.item;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.handler.*;
import net.minecraft.item.*;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class ItemFishFoodNew extends ItemFishFood {

    public ItemFishFoodNew(boolean cooked) {
        super(cooked);
        this.setTranslationKey("fish");
        this.setRegistryName(cooked ? "cooked_fish" : "fish");
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Nullable
    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return NetWeight.MODID;
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return super.getSaturationModifier(stack) * this.getWeightModifier(stack);
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return Math.round(super.getHealAmount(stack) * this.getWeightModifier(stack));
    }

    @SuppressWarnings({"ConstantConditions", "WeakerAccess"})
    protected float getWeightModifier(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(FishHelper.NBT_KEY_FISH_WEIGHT, Constants.NBT.TAG_INT) ? stack.getTagCompound().getInteger(FishHelper.NBT_KEY_FISH_WEIGHT) / 1000.0F : 0.7F;
    }
}
