package com.github.upcraftlp.netweight.util;

import com.github.upcraftlp.netweight.NetWeight;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public final class NwMessages {

    public static final String FISHING_STATUS_FAIL_NO_BAIT = Util.createTranslationKey("status", new Identifier(NetWeight.MODID, "fishing_fail_no_bait"));

    public static final String TOAST_NEW_TOP_WEIGHT = Util.createTranslationKey("toast", new Identifier(NetWeight.MODID, "new_top_weight"));

    public static final String FORMAT_WEIGHT_TONS = Util.createTranslationKey("format", new Identifier(NetWeight.MODID, "weight_tons"));
    public static final String FORMAT_WEIGHT_KILOS = Util.createTranslationKey("format", new Identifier(NetWeight.MODID, "weight_kilos"));
    public static final String FORMAT_WEIGHT_GRAMS = Util.createTranslationKey("format", new Identifier(NetWeight.MODID, "weight_grams"));

}
