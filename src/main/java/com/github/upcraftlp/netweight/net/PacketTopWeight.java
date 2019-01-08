package com.github.upcraftlp.netweight.net;

import com.github.upcraftlp.netweight.client.HeavyFishToast;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.*;

public class PacketTopWeight implements IMessage, IMessageHandler<PacketTopWeight, IMessage> {

    private int weight = 0;

    @SuppressWarnings("unused")
    public PacketTopWeight() {
        //NO-OP
    }

    public PacketTopWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.weight = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.weight);
    }

    @Override
    @SideOnly(Side.CLIENT) //needed to not crash the server because we're calling client code
    public IMessage onMessage(PacketTopWeight message, MessageContext ctx) {
        HeavyFishToast.addOrUpdate(message.weight);
        Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, Minecraft.getMinecraft().player.getRNG().nextFloat() * 0.6F + 0.4F);
        return null;
    }
}
