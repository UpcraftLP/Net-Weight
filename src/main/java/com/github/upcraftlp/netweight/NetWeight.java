package com.github.upcraftlp.netweight;

import com.github.glasspane.mesh.api.annotation.CalledByReflection;
import com.github.glasspane.mesh.api.logging.MeshLoggerFactory;
import com.github.upcraftlp.netweight.loot.NWFishingLootEntry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.LootEntryTypeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        LootEntryTypeRegistry.INSTANCE.register(new NWFishingLootEntry.Serializer(new Identifier(MODID, "fishing_loot_table")));
//        StringBuilder sb = new StringBuilder();
//        sb.append("id,temperature,rain,category").append('\n');
//        RegistryHelper.visitRegistry(Registry.BIOME, (identifier, biome) -> sb
//                .append(identifier).append(',')
//                .append(biome.getTemperature()).append(',')
//                .append(biome.getRainfall()).append(',')
//                .append(biome.getCategory().getName()).append('\n'));
//        logger.error("biomes: \n{}", sb);
//        System.exit(0);
    }

}
