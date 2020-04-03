package com.github.upcraftlp.netweight.client;

import com.github.glasspane.mesh.api.annotation.CalledByReflection;
import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.net.S2CTopWeightPacket;
import com.github.upcraftlp.netweight.util.NwHooks;
import com.github.upcraftlp.netweight.util.NwStatFormatters;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ItemTooltipCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

@CalledByReflection
public class NetWeightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSidePacketRegistry.INSTANCE.register(S2CTopWeightPacket.ID, S2CTopWeightPacket::onPacket);
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, list) -> {
            CompoundTag tag = itemStack.getSubTag(NetWeight.MODID);
            if(tag != null && tag.contains(NwHooks.NBT_KEY_FISH_WEIGHT, NbtType.INT)) {
                int weight = tag.getInt(NwHooks.NBT_KEY_FISH_WEIGHT);
                if(weight <= 0) {
                    weight = NwHooks.DEFAULT_FISH_WEIGHT;
                }
                list.add(new LiteralText("")); //empty line
                list.add(new LiteralText(NwStatFormatters.WEIGHT.format(weight)).formatted(Formatting.GRAY));
            }
        });
    }
}
