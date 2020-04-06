package com.github.upcraftlp.netweight.init;

import com.github.glasspane.mesh.api.annotation.AutoRegistry;
import com.github.glasspane.mesh.api.registry.AutoRegistryHook;
import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.item.IngredientItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

@AutoRegistry(value = Item.class, modid = NetWeight.MODID, registry = "item")
public class NWItems implements AutoRegistryHook {

    public static final Item CREATIVE_BAIT = new IngredientItem(16);

    public static final Item TROUT = new IngredientItem(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build()));
    public static final Item EEL = new IngredientItem(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build()));

}
