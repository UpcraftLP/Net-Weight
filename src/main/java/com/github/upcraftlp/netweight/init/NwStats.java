package com.github.upcraftlp.netweight.init;

import com.github.glasspane.mesh.api.annotation.AutoRegistry;
import com.github.glasspane.mesh.api.registry.AutoRegistryHook;
import com.github.upcraftlp.netweight.NetWeight;
import net.minecraft.util.Identifier;

@AutoRegistry(value = Identifier.class, modid = NetWeight.MODID, registry = "custom_stat")
public class NwStats implements AutoRegistryHook {

    public static final Identifier HEAVIEST_FISH_CAUGHT = new Identifier(NetWeight.MODID, "heaviest_fish_caught");
    public static final Identifier TOTAL_FISH_WEIGHT = new Identifier(NetWeight.MODID, "total_fish_weight");
}
