package com.github.upcraftlp.netweight.override.block.tileentity;

import com.github.upcraftlp.netweight.handler.FishHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityFurnaceNew extends TileEntityFurnace {

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return newState.getBlock() != Blocks.FURNACE && newState.getBlock() != Blocks.LIT_FURNACE;
    }    @Override
    public void smeltItem() {
        if(this.canSmelt()) {
            ItemStack inputStack = this.getStackInSlot(0);
            ItemStack resultStack = FurnaceRecipes.instance().getSmeltingResult(inputStack);
            ItemStack currentOutputStack = this.getStackInSlot(2);

            boolean isFish = FishHelper.isItemValidFish(inputStack);
            if(currentOutputStack.isEmpty() || isFish) {
                ItemStack copy = resultStack.copy();
                if(isFish) {
                    if(!copy.hasTagCompound()) copy.setTagCompound(new NBTTagCompound());
                    int weight = FishHelper.getFishWeight(inputStack);
                    copy.getTagCompound().setInteger(FishHelper.NBT_KEY_FISH_WEIGHT, weight);
                }
                this.setInventorySlotContents(2, copy);
            }
            else if(currentOutputStack.getItem() == resultStack.getItem()) {
                currentOutputStack.grow(resultStack.getCount());
            }

            if(inputStack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && inputStack.getMetadata() == 1 && !this.getStackInSlot(1).isEmpty() && this.getStackInSlot(1).getItem() == Items.BUCKET) {
                this.setInventorySlotContents(1, new ItemStack(Items.WATER_BUCKET));
            }
            inputStack.shrink(1);
        }
    }



    @Override
    public boolean canSmelt()
    {
        if (this.getStackInSlot(0).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack resultStack = FurnaceRecipes.instance().getSmeltingResult(this.getStackInSlot(0));

            if (resultStack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack currentOutputStack = this.getStackInSlot(2);
                if (currentOutputStack.isEmpty())
                {
                    return true;
                }
                else if(FishHelper.isItemValidFish(this.getStackInSlot(0))) {
                    return false;
                }
                else if (!currentOutputStack.isItemEqual(resultStack))
                {
                    return false;
                }
                else if (currentOutputStack.getCount() + resultStack.getCount() <= this.getInventoryStackLimit() && currentOutputStack.getCount() + resultStack.getCount() <= currentOutputStack.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return currentOutputStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }
}
