package com.github.upcraftlp.netweight.util;

import com.github.upcraftlp.netweight.NetWeight;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class NWTags {

    public static final Tag<Item> FISHING_BAITS = TagRegistry.item(new Identifier(NetWeight.MODID, "fishing_baits"));
    public static final Tag<Item> FISHING_RODS = TagRegistry.item(new Identifier(NetWeight.MODID, "fishing_rods"));
}
