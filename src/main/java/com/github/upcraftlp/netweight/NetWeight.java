package com.github.upcraftlp.netweight;

import com.github.upcraftlp.glasspane.util.ModUpdateHandler;
import com.github.upcraftlp.netweight.handler.FishHelper;
import com.github.upcraftlp.netweight.override.block.tileentity.TileEntityFurnaceNew;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.*;

import static com.github.upcraftlp.netweight.NetWeight.*;

@SuppressWarnings("WeakerAccess")
@Mod(
        certificateFingerprint = FINGERPRINT_KEY,
        name = MODNAME,
        version = VERSION,
        acceptedMinecraftVersions = MCVERSIONS,
        modid = MODID,
        dependencies = DEPENDENCIES,
        updateJSON = UPDATE_JSON
)
public class NetWeight {

    //Version
    public static final String MCVERSIONS = "[1.12.2,1.13)";
    public static final String VERSION = "@VERSION@";

    //Meta Information
    public static final String MODNAME = "Net Weight";
    public static final String MODID = "net_weight";
    public static final String DEPENDENCIES = "required-after:glasspane;required-after:forge";
    public static final String UPDATE_JSON = "@UPDATE_JSON@";

    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";
    public static final SimpleNetworkWrapper NETWORK_HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    private static final Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModUpdateHandler.registerMod(MODID);
        FishStats.registerStats();
        GameRegistry.registerTileEntity(TileEntityFurnaceNew.class, new ResourceLocation("minecraft", "furnace"));
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        FishHelper.syncConfigValues(); //need to do this late so it picks up the registry replacements
    }
}
