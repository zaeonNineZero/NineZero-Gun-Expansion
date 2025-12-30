package zaeonninezero.nzgmaddon.client;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import zaeonninezero.nzgmaddon.init.initItems;

public class CreativeGunVariantManager
{
	public static void addItemVariants(NonNullList<ItemStack> items)
    {
        // Submachine Gun, Variant 1
    	ItemStack smg_1 = new ItemStack(initItems.SUBMACHINE_GUN.get());
    	{
    		CompoundTag tag = smg_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.SUBMACHINE_GUN.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    	}
    	items.add(smg_1);
    	
    	
    	// Pump Shotgun, Variant 1
    	ItemStack pumpshotgun_1 = new ItemStack(initItems.PUMP_SHOTGUN.get());
    	{
    		CompoundTag tag = pumpshotgun_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.PUMP_SHOTGUN.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("CustomModelData", 1);
    	}
    	items.add(pumpshotgun_1);
    	// Pump Shotgun, Variant 2
    	ItemStack pumpshotgun_2 = new ItemStack(initItems.PUMP_SHOTGUN.get());
    	{
    		CompoundTag tag = pumpshotgun_2.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.PUMP_SHOTGUN.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    		tag.putInt("HeatShield", 1);
    	}
    	items.add(pumpshotgun_2);
    	
    	
    	// Heavy Assault Rifle, Variant 1
    	ItemStack heavyar_1 = new ItemStack(initItems.HEAVY_ASSAULT_RIFLE.get());
    	{
    		CompoundTag tag = heavyar_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.HEAVY_ASSAULT_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    	}
    	items.add(heavyar_1);
    	
    	
    	// Battle Rifle, Variant 1
    	ItemStack battlerifle_1 = new ItemStack(initItems.BATTLE_RIFLE.get());
    	{
    		CompoundTag tag = battlerifle_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.BATTLE_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("SightVariant", 1);
    	}
    	items.add(battlerifle_1);
    	
    	
    	// Lever-Action Rifle, Variant 1
    	ItemStack leverrifle_1 = new ItemStack(initItems.LEVER_ACTION_RIFLE.get());
    	{
    		CompoundTag tag = leverrifle_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.LEVER_ACTION_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    	}
    	items.add(leverrifle_1);
    	
    	
    	// Automatic Sniper Rifle, Variant 1
    	ItemStack autosniper_1 = new ItemStack(initItems.AUTOMATIC_SNIPER_RIFLE.get());
    	{
    		CompoundTag tag = autosniper_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.AUTOMATIC_SNIPER_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    	}
    	items.add(autosniper_1);
    	// Automatic Sniper Rifle, Variant 2
    	ItemStack autosniper_2 = new ItemStack(initItems.AUTOMATIC_SNIPER_RIFLE.get());
    	{
    		CompoundTag tag = autosniper_2.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.AUTOMATIC_SNIPER_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    		tag.putInt("HandguardVariant", 1);
    	}
    	items.add(autosniper_2);
    	
    	
    	// Bolt-Action Rifle, Variant 1
    	ItemStack boltrifle_1 = new ItemStack(initItems.BOLT_ACTION_RIFLE.get());
    	{
    		CompoundTag tag = boltrifle_1.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.BOLT_ACTION_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 1);
    	}
    	items.add(boltrifle_1);
    	// Bolt-Action Rifle, Variant 2
    	ItemStack boltrifle_2 = new ItemStack(initItems.BOLT_ACTION_RIFLE.get());
    	{
    		CompoundTag tag = boltrifle_2.getOrCreateTag();
        	tag.putInt("AmmoCount", initItems.BOLT_ACTION_RIFLE.get().getGun().getGeneral().getMaxAmmo());
    		tag.putInt("BaseVariant", 2);
    	}
    	items.add(boltrifle_2);
    }
}