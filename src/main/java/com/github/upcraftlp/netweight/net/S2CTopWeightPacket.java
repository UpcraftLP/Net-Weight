package com.github.upcraftlp.netweight.net;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.client.HeavyFishToast;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class S2CTopWeightPacket {

    public static final Identifier ID = new Identifier(NetWeight.MODID, "s2c_top_weight");

    public static void sendTo(ServerPlayerEntity playerEntity, int weight) {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeInt(weight);
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(playerEntity, ID, byteBuf);
    }

    public static void onPacket(PacketContext ctx, PacketByteBuf byteBuf) {
        int weight = byteBuf.readInt();
        ctx.getTaskQueue().execute(() -> {
            HeavyFishToast.addOrUpdate(weight);
            ctx.getPlayer().playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, ctx.getPlayer().getRandom().nextFloat() * 0.6F + 0.4F);
        });
    }
}
