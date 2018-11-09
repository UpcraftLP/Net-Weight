package com.github.upcraftlp.netweight;

import net.minecraft.client.resources.I18n;
import net.minecraft.stats.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.*;

public class FishStats {

    public static final StatBase HEAVIEST_FISH = new StatBasic("stat.net_weight.heaviestFishCaught", new TextComponentTranslation("stat.net_weight.heaviestFishCaught"), Type.WEIGHT).registerStat();
    public static final StatBase TOTAL_FISH_WEIGHT = new StatBasic("stat.net_weight.totalFishWeight", new TextComponentTranslation("stat.net_weight.totalFishWeight"), Type.WEIGHT).registerStat();

    public static class Type {

        //do NOT use a lambda here, for the sake of IStatType#format() being client-only!
        public static final IStatType WEIGHT = new IStatType() {

            @SideOnly(Side.CLIENT)
            @Override
            public String format(int grams) {
                float tons = grams / 1000000.0F;
                float kilos = grams / 1000.0F;
                return tons >= 0.5D ? I18n.format("tooltip.net_weight.weight_tons", tons) : kilos >= 0.5D ? I18n.format("tooltip.net_weight.weight_kilos", kilos) : I18n.format("tooltip.net_weight.weight_grams", grams);
            }
        };
    }
}
