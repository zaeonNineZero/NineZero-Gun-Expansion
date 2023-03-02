package zaeonninezero.nzgmaddon.init;

import zaeonninezero.nzgmaddon.nzgmAddon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class initSounds {
	/*
     * This creates a Deferred Register where all of the sounds will be registered
     * This is called and added to the event bus in the main mod file.
     */
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, nzgmAddon.MOD_ID);

	public static final RegistryObject<SoundEvent> ITEM_ASSAULT_RIFLE_RELOAD = register("item.assault_rifle.reload");
	public static final RegistryObject<SoundEvent> ITEM_RIFLE_RELOAD = register("item.rifle.reload");
	public static final RegistryObject<SoundEvent> ITEM_SHOTGUN_RELOAD = register("item.shotgun.reload");
	public static final RegistryObject<SoundEvent> ITEM_GRENADE_LAUNCHER_RELOAD = register("item.grenade_launcher.reload");
	public static final RegistryObject<SoundEvent> ITEM_BAZOOKA_RELOAD = register("item.bazooka.reload");
	
	public static final RegistryObject<SoundEvent> ITEM_REVOLVER_FIRE = register("item.revolver.fire");
	public static final RegistryObject<SoundEvent> ITEM_REVOLVER_ENCHANTED_FIRE = register("item.revolver.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_REVOLVER_COCK = register("item.revolver.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_UZI_FIRE = register("item.uzi.fire");
	public static final RegistryObject<SoundEvent> ITEM_UZI_ENCHANTED_FIRE = register("item.uzi.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_UZI_SILENCED_FIRE = register("item.uzi.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_UZI_COCK = register("item.uzi.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_SMG_FIRE = register("item.smg.fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_ENCHANTED_FIRE = register("item.smg.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_SILENCED_FIRE = register("item.smg.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_COCK = register("item.smg.cock");

	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_FIRE = register("item.hunting_shotgun.fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_ENCHANTED_FIRE = register("item.hunting_shotgun.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_SILENCED_FIRE = register("item.hunting_shotgun.silenced_fire");

	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_FIRE = register("item.heavy_ar.fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_ENCHANTED_FIRE = register("item.heavy_ar.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_SILENCED_FIRE = register("item.heavy_ar.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_COCK = register("item.heavy_ar.cock");

	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_FIRE = register("item.battle_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_ENCHANTED_FIRE = register("item.battle_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_SILENCED_FIRE = register("item.battle_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_COCK = register("item.battle_rifle.cock");

	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_FIRE = register("item.hunting_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_ENCHANTED_FIRE = register("item.hunting_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_SILENCED_FIRE = register("item.hunting_rifle.silenced_fire");
	
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_FIRE = register("item.sniper_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_ENCHANTED_FIRE = register("item.sniper_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_SILENCED_FIRE = register("item.sniper_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_COCK = register("item.sniper_rifle.cock");
	
	//Method to help us register sounds
	private static RegistryObject<SoundEvent> register(String key) {
		return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(nzgmAddon.MOD_ID, key)));
	}
}