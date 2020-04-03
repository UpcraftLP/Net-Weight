package com.github.upcraftlp.netweight.mixin.common;

import com.github.upcraftlp.netweight.util.NwHooks;
import com.github.upcraftlp.netweight.util.NwMessages;
import com.github.upcraftlp.netweight.util.access.FishingBobberData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {

    @Unique
    private static final ThreadLocal<ItemStack> baitStorage = ThreadLocal.withInitial(() -> ItemStack.EMPTY);

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onTryUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if(user.fishHook == null) {
            ItemStack rod = user.getStackInHand(hand);
            ItemStack baitType = NwHooks.findBaitType(user, rod);
            if(!baitType.isEmpty()) {
                ItemStack bait = baitType.copy();
                bait.setCount(1);
                baitStorage.set(bait);
                if(!user.abilities.creativeMode) {
                    bait.decrement(1);
                    user.inventory.markDirty();
                }
            }
            else {
                user.addChatMessage(new TranslatableText(NwMessages.FISHING_STATUS_FAIL_NO_BAIT), true);
                cir.setReturnValue(TypedActionResult.fail(rod));
            }
        }
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void onCastRod(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if(user.fishHook instanceof FishingBobberData) {
            ((FishingBobberData) user.fishHook).nw_setBait(baitStorage.get());
        }
    }

    @Inject(method = "use", at = @At("RETURN"))
    private void afterUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        //cleanup, to ensure we always start with an empty stack as bait
        baitStorage.remove();
    }
}
