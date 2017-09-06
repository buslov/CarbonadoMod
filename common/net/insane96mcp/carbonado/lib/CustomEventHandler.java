package net.insane96mcp.carbonado.lib;

import net.insane96mcp.carbonado.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.reflect.internal.Trees.This;

public class CustomEventHandler {
	@SubscribeEvent
	public static void LivingHurtEvent(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			DamageSource source = event.getSource();
			DamageSource[] validSources = new DamageSource[] {
				
			};
					
			ItemStack[] armorList = new ItemStack[] {
				new ItemStack(ModItems.carbonadoHelmetItem), 
				new ItemStack(ModItems.carbonadoChestplateItem), 
				new ItemStack(ModItems.carbonadoLeggingsItem), 
				new ItemStack(ModItems.carbonadoBootsItem)
			};
			
			float[] materialPerPiece = new float[] { 5, 8, 7, 4 };
			
			if (source == DamageSource.OUT_OF_WORLD)
				return;
			
			
		    int materialsUsed = 0;
		    Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
		    for (ItemStack armorPiece : playerArmor) {
		    	for (int i = 0; i < armorList.length; i++) {
			        if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, armorList[i])) {
						materialsUsed += materialPerPiece[i];
						break;
					}
				}
			}
		    
			float amount = event.getAmount();
		    
		    if(materialsUsed >= 1) {
		    	float maxReduction = 0.2f;
		    	float reductionPerMaterial = maxReduction / 24f;
		    	float percentageReduction = reductionPerMaterial * materialsUsed;
		    	amount = amount * (1f - percentageReduction);
		        event.setAmount(amount);
		    }
		}
	}
	
	@SubscribeEvent
	public static void LivingSpawnEvent(LivingSpawnEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof EntityWitherSkeleton) {
			if (event.getWorld().rand.nextInt(100) < 5) {
				entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.carbonadoSwordItem, 1));
				((EntityWitherSkeleton)entity).setLeftHanded(true);
			}
		}
	}
	
	 @SubscribeEvent
	 public static void WorldEventLoad(WorldEvent.Load event) {
		 IWorldEventListener WorldEventListener = new WorldEventListener();
		 event.getWorld().addEventListener(WorldEventListener);
	 }
}
