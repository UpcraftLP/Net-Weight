package com.github.upcraftlp.netweight.mixin.common;

import com.github.upcraftlp.netweight.util.NwHooks;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootContextTypes.class)
public abstract class LootContextTypesMixin {

    @SuppressWarnings("UnresolvedMixinReference") //synthetic static lambda method
    @Inject(method = "method_764", at = @At("RETURN"))
    private static void onCreateFishingContext(LootContextType.Builder builder, CallbackInfo ci) {
        builder.allow(NwHooks.LOOT_CONTEXT_BAIT); //TODO required or allow?
    }
}
