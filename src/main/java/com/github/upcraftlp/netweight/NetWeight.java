package com.github.upcraftlp.netweight;

import com.github.glasspane.mesh.api.annotation.CalledByReflection;
import com.github.glasspane.mesh.api.logging.MeshLoggerFactory;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

@CalledByReflection
public class NetWeight implements ModInitializer {

    public static final String MODID = "net_weight";
    private static final String MOD_NAME = "Net Weight";

    public static final Logger logger = MeshLoggerFactory.createPrefixLogger(MODID, MOD_NAME);

    @Override
    public void onInitialize() {
    }
}
