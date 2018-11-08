package com.github.upcraftlp.netweight.config;

import com.github.upcraftlp.netweight.NetWeight;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config.net_weight.general.title")
@Config(modid = NetWeight.MODID, name= "glasspanemods/NetWeight") //--> /config/glasspanemods/NetWEight.cfg
public class NetWeightConfig {

    @Config.RequiresMcRestart
    @Config.Name("Fish Items")
    @Config.Comment("A list of item registry names that are to be treated as fish and thus be assigned a weight")
    public static String[] fishWhitelist = new String[] {
        "minecraft:fish"
    };

    @Mod.EventBusSubscriber(modid = NetWeight.MODID)
    public static class Handler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {
            if(NetWeight.MODID.equals(event.getModID())) {
                ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
            }
        }
    }
}
