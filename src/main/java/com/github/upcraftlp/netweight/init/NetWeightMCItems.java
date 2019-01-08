package com.github.upcraftlp.netweight.init;

import com.github.upcraftlp.glasspane.api.registry.AutoRegistry;
import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.override.block.BlockFurnaceNew;
import com.github.upcraftlp.netweight.override.item.ItemFishFoodNew;
import net.minecraft.block.Block;
import net.minecraft.item.*;

@SuppressWarnings({"ConstantConditions", "WeakerAccess", "unused"})
@AutoRegistry(NetWeight.MODID) //intentionally overriding vanilla stuff here
public class NetWeightMCItems {

    public static final Item FISH = new ItemFishFoodNew(false);
    public static final Item COOKED_FISH = new ItemFishFoodNew(true);

    public static final Block FURNACE = new BlockFurnaceNew(false);
    public static final Block LIT_FURNACE = new BlockFurnaceNew(true);

    public static final Item FURNACE_ITEM = new ItemBlock(FURNACE).setRegistryName(FURNACE.getRegistryName());
}
