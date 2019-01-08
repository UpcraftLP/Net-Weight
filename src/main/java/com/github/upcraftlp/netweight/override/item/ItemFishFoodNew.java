package com.github.upcraftlp.netweight.override.item;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.handler.FishHelper;
import net.minecraft.item.*;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class ItemFishFoodNew extends ItemFishFood {

    private static final float WEIGHT_MODIFIER = 1.5F;
    private final boolean cooked;

    public ItemFishFoodNew(boolean cooked) {
        super(cooked);
        this.cooked = cooked;
        this.setTranslationKey("fish");
        this.setRegistryName("minecraft", cooked ? "cooked_fish" : "fish");
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Nullable
    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return NetWeight.MODID;
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return Math.round(Math.max(super.getHealAmount(stack) * this.getWeightModifier(stack), cooked ? 0.5F : 0.2F));
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return super.getSaturationModifier(stack) * this.getWeightModifier(stack) * (cooked ? 1.2F : 0.9F);
    }

    @SuppressWarnings({"ConstantConditions", "WeakerAccess"})
    protected float getWeightModifier(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(FishHelper.NBT_KEY_FISH_WEIGHT, Constants.NBT.TAG_INT) ? stack.getTagCompound().getInteger(FishHelper.NBT_KEY_FISH_WEIGHT) / 1000.0F * WEIGHT_MODIFIER : 0.7F;
    }
}
