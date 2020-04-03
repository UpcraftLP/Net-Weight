package com.github.upcraftlp.netweight;

import com.github.glasspane.mesh.api.annotation.CalledByReflection;
import com.github.glasspane.mesh.api.logging.MeshLoggerFactory;
import com.github.upcraftlp.netweight.init.NwStats;
import com.github.upcraftlp.netweight.loot.NWFishingLootEntry;
import com.github.upcraftlp.netweight.util.FishingDataManager;
import com.github.upcraftlp.netweight.util.NWTags;
import com.github.upcraftlp.netweight.util.NwStatFormatters;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.loot.v1.LootEntryTypeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceType;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Logger;

@CalledByReflection
public class NetWeight implements ModInitializer {

    public static final String MODID = "net_weight";
    private static final String MOD_NAME = "Net Weight";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "fishing"), () -> new ItemStack(Items.FISHING_ROD));

    public static final Logger logger = MeshLoggerFactory.createPrefixLogger(MODID, MOD_NAME);

    @Override
    public void onInitialize() {
        NWTags.init();
        LootEntryTypeRegistry.INSTANCE.register(new NWFishingLootEntry.Serializer(new Identifier(MODID, "fishing_loot_table")));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FishingDataManager());
        ServerStartCallback.EVENT.register(minecraftServer -> {
            //this is needed to properly display the stats
            Stats.CUSTOM.getOrCreateStat(NwStats.HEAVIEST_FISH_CAUGHT, NwStatFormatters.WEIGHT);
            Stats.CUSTOM.getOrCreateStat(NwStats.TOTAL_FISH_WEIGHT, NwStatFormatters.WEIGHT);
        });
    }

}
