package com.github.upcraftlp.netweight;

import com.github.upcraftlp.glasspane.util.ModUpdateHandler;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
    public static final String DEPENDENCIES = "after:glasspane;required-after:forge";
    public static final String UPDATE_JSON = "@UPDATE_JSON@";

    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";

    private static final Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(Loader.isModLoaded("glasspane")) ModUpdateHandler.registerMod(MODID);
        FishStats.registerStats();
    }
}
