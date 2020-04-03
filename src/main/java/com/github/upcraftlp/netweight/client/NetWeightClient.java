package com.github.upcraftlp.netweight.client;

import com.github.glasspane.mesh.api.annotation.CalledByReflection;
import com.github.upcraftlp.netweight.net.S2CTopWeightPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@CalledByReflection
public class NetWeightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSidePacketRegistry.INSTANCE.register(S2CTopWeightPacket.ID, S2CTopWeightPacket::onPacket);
    }
}
