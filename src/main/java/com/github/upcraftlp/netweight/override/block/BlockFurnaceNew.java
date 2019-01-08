package com.github.upcraftlp.netweight.override.block;

import com.github.upcraftlp.netweight.override.block.tileentity.TileEntityFurnaceNew;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFurnaceNew extends BlockFurnace {

    public BlockFurnaceNew(boolean isBurning) {
        super(isBurning);
        this.setHardness(3.5F);
        this.setSoundType(SoundType.STONE);
        this.setRegistryName(isBurning ? "lit_furnace" : "furnace");
        this.setTranslationKey("furnace");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        if(isBurning) this.setLightLevel(0.875F);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFurnaceNew();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }
}
