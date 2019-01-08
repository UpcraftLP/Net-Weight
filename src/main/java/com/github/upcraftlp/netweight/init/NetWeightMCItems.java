package com.github.upcraftlp.netweight.init;

import com.github.upcraftlp.glasspane.api.registry.AutoRegistry;
import com.github.upcraftlp.netweight.override.block.BlockFurnaceNew;
import com.github.upcraftlp.netweight.override.item.ItemFishFoodNew;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("unused")
@GameRegistry.ObjectHolder("minecraft")
@AutoRegistry("minecraft") //intentionally overriding vanilla stuff here
public class NetWeightMCItems {

    public static final Item FISH = new ItemFishFoodNew(false);
    public static final Item COOKED_FISH = new ItemFishFoodNew(true);

    public static final Block FURNACE = new BlockFurnaceNew(false);
    public static final Block LIT_FURNACE = new BlockFurnaceNew(true);
}
