package com.github.upcraftlp.netweight.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.stat.StatFormatter;

public class NwStatFormatters {

    @SuppressWarnings("Convert2Lambda")
    //do NOT use a lambda here, for the sake of StatFormatter#format() being client-only!
    public static final StatFormatter WEIGHT = new StatFormatter() {

        //TODO config option for other weight units
        @Environment(EnvType.CLIENT)
        @Override
        public String format(int grams) {
            float tons = grams / 1000000.0F;
            float kilos = grams / 1000.0F;
            return tons >= 0.5D ? I18n.translate(NwMessages.FORMAT_WEIGHT_TONS, String.format("%.1f", tons)) : kilos >= 0.5D ? I18n.translate(NwMessages.FORMAT_WEIGHT_KILOS, String.format("%.2f", kilos)) : I18n.translate(NwMessages.FORMAT_WEIGHT_GRAMS, grams);
        }
    };
}
