package com.github.upcraftlp.netweight.mixin.common;

import com.github.upcraftlp.netweight.NetWeight;
import com.github.upcraftlp.netweight.init.NwStats;
import com.github.upcraftlp.netweight.net.S2CTopWeightPacket;
import com.github.upcraftlp.netweight.util.NwHooks;
import com.github.upcraftlp.netweight.util.NwStatFormatters;
import com.github.upcraftlp.netweight.util.access.FishingBobberData;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin implements FishingBobberData {

    @Shadow @Nullable public abstract PlayerEntity getOwner();

    @Shadow @Final private PlayerEntity owner;
    @Unique
    private ItemStack bait = ItemStack.EMPTY;

    @Override
    public void nw_setBait(ItemStack bait) {
        this.bait = bait;
    }

    @Override
    public ItemStack nw_getBait() {
        return this.bait;
    }

    @Inject(method = "use", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/loot/context/LootContext$Builder;setLuck(F)Lnet/minecraft/loot/context/LootContext$Builder;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onGenerateLoot(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, int i, LootContext.Builder lootBuilder) {
        ItemStack bait = this.nw_getBait();
        if(!bait.isEmpty()) {
            lootBuilder.put(NwHooks.LOOT_CONTEXT_BAIT, bait);
        }
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V"), locals = LocalCapture.PRINT)
    private void onGenerateFishedItem(ItemStack usedItem, CallbackInfoReturnable<Integer> cir, int i, LootContext.Builder builder, LootTable lootTable, List<ItemStack> drops, Iterator<ItemStack> iterator, ItemStack itemStack) {
        if(this.getOwner() instanceof ServerPlayerEntity) {
            CompoundTag tag = itemStack.getTag();
            if(tag != null && tag.contains(NetWeight.MODID, NbtType.COMPOUND)) {
                ServerPlayerEntity player = (ServerPlayerEntity) this.getOwner();
                    int currentWeight = tag.getCompound(NetWeight.MODID).getInt(NwHooks.NBT_KEY_FISH_WEIGHT);
                    if(currentWeight > 0) {
                        player.increaseStat(Stats.CUSTOM.getOrCreateStat(NwStats.TOTAL_FISH_WEIGHT, NwStatFormatters.WEIGHT), currentWeight);
                        int heaviestFish = player.getStatHandler().getStat(Stats.CUSTOM, NwStats.HEAVIEST_FISH_CAUGHT);
                        if(heaviestFish < currentWeight) {
                            player.increaseStat(Stats.CUSTOM.getOrCreateStat(NwStats.HEAVIEST_FISH_CAUGHT, NwStatFormatters.WEIGHT), currentWeight - heaviestFish);
                            S2CTopWeightPacket.sendTo(player, currentWeight);
                        }
                    }


            }
        }

    }
}
