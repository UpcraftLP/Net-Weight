package com.github.upcraftlp.netweight.api;

import com.github.upcraftlp.netweight.util.FishingDataManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class FishType {

    private final Predicate<ItemStack> isBaitFunction;
    private final boolean requiresThunder;
    private final boolean requiresRain;
    private final ItemStack fish;
    private final int minHeight;
    private final int maxHeight;
    private final float minTemperature;
    private final float maxTemperature;
    private final int minWeight;
    private final int maxWeight;

    public FishType(Predicate<ItemStack> isBaitFunction, boolean requiresRain, boolean requiresThunder, ItemStack fish, int minHeight, int maxHeight, float minTemperature, float maxTemperature, int minWeight, int maxWeight) {
        this.isBaitFunction = isBaitFunction;
        this.requiresRain = requiresRain;
        this.requiresThunder = requiresThunder;
        this.fish = fish;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public boolean requiresRain() {
        return requiresRain;
    }

    public boolean requiresThunder() {
        return requiresThunder;
    }

    public ItemStack getFish() {
        return fish;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean isBait(ItemStack stack) {
        return !stack.isEmpty() && isBaitFunction.test(stack);
    }

    @Override
    public String toString() {
        Identifier name = FishingDataManager.FISH_TYPES.inverse().get(this);
        return String.format("FishType{%s}", name != null ? name.toString() : "unregistered");
    }
}
