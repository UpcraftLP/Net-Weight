package com.github.upcraftlp.netweight.util;

import com.github.glasspane.mesh.util.JsonUtil;
import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.api.FishType;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class FishingDataManager extends JsonDataLoader implements IdentifiableResourceReloadListener {

    public static final BiMap<Identifier, FishType> FISH_TYPES = HashBiMap.create(256);

    private static final Identifier ID = new Identifier(NetWeight.MODID, "fishing_data");

    public FishingDataManager() {
        super(JsonUtil.GSON, "fish_types");
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return Collections.singleton(ResourceReloadListenerKeys.TAGS);
    }

    @Override
    protected void apply(Map<Identifier, JsonObject> map, ResourceManager manager, Profiler profiler) {
        NetWeight.logger.debug("reloading fish types");
        FISH_TYPES.clear();
        map.forEach((identifier, json) -> {
            JsonArray baits = JsonHelper.getArray(json, "baits");
            ImmutableSet.Builder<Item> validItems = ImmutableSet.builder();
            baits.forEach(jsonElement -> {
                String bait = JsonHelper.asString(jsonElement, "bait");
                if(bait.startsWith("#")) {
                    Identifier tagName = new Identifier(bait.substring(1));
                    Tag<Item> tag = ItemTags.getContainer().getOrCreate(tagName);
                    validItems.addAll(tag.values());
                }
                else {
                    Identifier itemName = new Identifier(bait);
                    validItems.add(Registry.ITEM.getOrEmpty(itemName).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + itemName + "'")));
                }
            });
            Set<Item> validBaits = validItems.build();
            ItemStack fish = ShapedRecipe.getItemStack(JsonHelper.getObject(json, "fish"));
            JsonObject weight = JsonHelper.getObject(json, "weight");
            int minWeight = JsonHelper.getInt(weight, "min");
            int maxWeight = JsonHelper.getInt(weight, "max");
            JsonObject environment = JsonHelper.getObject(json, "environment");
            boolean rain = JsonHelper.getBoolean(environment, "rain_required");
            boolean thunder = JsonHelper.getBoolean(environment, "thunder_required");
            JsonObject temperature = JsonHelper.getObject(environment, "temperature");
            float minTemp = JsonHelper.getInt(temperature, "min");
            float maxTemp = JsonHelper.getInt(temperature, "max");
            JsonObject height = JsonHelper.getObject(environment, "height");
            int minHeight = JsonHelper.getInt(height, "min");
            int maxHeight = JsonHelper.getInt(height, "max");
            FISH_TYPES.put(identifier, new FishType(stack -> validBaits.contains(stack.getItem()), rain, thunder, fish, minHeight, maxHeight, minTemp, maxTemp, minWeight, maxWeight));
            NetWeight.logger.trace("registered fish type {}", identifier);
        });
    }
}
