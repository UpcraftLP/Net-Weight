package com.github.upcraftlp.netweight.init;

import com.github.glasspane.mesh.api.annotation.AutoRegistry;
import com.github.glasspane.mesh.api.registry.AutoRegistryHook;
import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.item.IngredientItem;
import net.minecraft.item.Item;

@AutoRegistry(value = Item.class, modid = NetWeight.MODID, registry = "item")
public class NWItems implements AutoRegistryHook {

    public static final Item BAIT = new IngredientItem();
}
