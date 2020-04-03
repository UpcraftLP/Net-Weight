package com.github.upcraftlp.netweight.util;

import com.github.upcraftlp.netweight.NetWeight;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum FishSources implements StringIdentifiable {
    FRESH_WATER("fresh_water"), //river, lake
    LAVA("lava"),
    SALT_WATER("salt_water"), //ocean
    DIRTY_WATER("dirty_water"); //swamp

    private final String name;

    FishSources(String name) {
        this.name = new Identifier(NetWeight.MODID, name).toString();
    }

    @Override
    public String asString() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.asString();
    }
}
