package com.github.upcraftlp.netweight.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class NWFishingLootEntry extends LeafEntry {

    public NWFishingLootEntry(int weight, int quality, LootCondition[] conditions, LootFunction[] functions) {
        super(weight, quality, conditions, functions);
    }

    @Override
    protected void drop(Consumer<ItemStack> itemDropper, LootContext context) {
        //TODO select and drop fishes!
        context.get(LootContextParameters.POSITION);
        context.get(LootContextParameters.TOOL);
    }

    public static class Serializer extends LootEntry.Serializer<NWFishingLootEntry> {

        public Serializer(Identifier id) {
            super(id, NWFishingLootEntry.class);
        }

        @Override
        public void toJson(JsonObject json, NWFishingLootEntry entry, JsonSerializationContext context) {
        }

        @Override
        public NWFishingLootEntry fromJson(JsonObject json, JsonDeserializationContext context, LootCondition[] conditions) {
            return null;
        }
    }
}
