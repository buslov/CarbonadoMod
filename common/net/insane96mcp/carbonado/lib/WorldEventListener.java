package net.insane96mcp.carbonado.lib;

import net.insane96mcp.carbonado.init.ModBlocks;
import net.insane96mcp.carbonado.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import scala.inline;
import scala.tools.nsc.backend.icode.analysis.CopyPropagation.Const;

public class WorldEventListener implements IWorldEventListener {
	
    public EntityFallingBlock isAnvil(Entity entity) {
        if(!(entity instanceof EntityFallingBlock))
        	return null;
        EntityFallingBlock entityFallingBlock = (EntityFallingBlock)entity;
        if (entityFallingBlock.getBlock().getBlock() == Blocks.ANVIL)
            return entityFallingBlock;
        return null;
    }

    private final int MAX_SHARD_DROP = 128;

    private final int MIN_HEIGHT_FALLTIME = 27;
    private final int MAX_HEIGHT_FALLTIME = 108;
    
	@Override
	public void onEntityRemoved(Entity entity) {
		// TODO Auto-generated method stub
		EntityFallingBlock anvil = isAnvil(entity);
		if(anvil == null)
			return;

		if (anvil.fallTime < MIN_HEIGHT_FALLTIME)
			return;
		
		World world = entity.getEntityWorld();
		BlockPos pos = entity.getPosition().down();
		IBlockState blockBelow = world.getBlockState(pos);
		if(blockBelow.getBlock() == ModBlocks.carbonadoBlock) {
            world.destroyBlock(pos, false);
            int dropCount = anvil.fallTime * MAX_SHARD_DROP / MAX_HEIGHT_FALLTIME;
    		System.out.println(anvil.fallTime + " " + dropCount);
            EntityItem shards = null;
            for (int i = 0; i < dropCount; i++) {
            	shards = new EntityItem(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(ModItems.carbonadoShardItem, 1));
            	world.spawnEntity(shards);
            }
        }

	}
	
	@Override
	public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLightSet(BlockPos pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoundToAllNearExcept(EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x,
			double y, double z, float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRecord(SoundEvent soundIn, BlockPos pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord,
			double xSpeed, double ySpeed, double zSpeed, int... parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(int p_190570_1_, boolean p_190570_2_, boolean p_190570_3_, double p_190570_4_,
			double p_190570_6_, double p_190570_8_, double p_190570_10_, double p_190570_12_, double p_190570_14_,
			int... p_190570_16_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityAdded(Entity entityIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastSound(int soundID, BlockPos pos, int data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
		// TODO Auto-generated method stub
		
	}
}
