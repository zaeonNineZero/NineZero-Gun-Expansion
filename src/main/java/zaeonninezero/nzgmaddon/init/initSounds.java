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
	public static final RegistryObject<SoundEvent> ITEM_REVOLVER_SILENCED_FIRE = register("item.revolver.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_REVOLVER_COCK = register("item.revolver.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_PISTOL_FIRE = register("item.heavy_pistol.fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_PISTOL_ENCHANTED_FIRE = register("item.heavy_pistol.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_PISTOL_SILENCED_FIRE = register("item.heavy_pistol.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_PISTOL_COCK = register("item.heavy_pistol.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_REVOLVER_FIRE = register("item.heavy_revolver.fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_REVOLVER_ENCHANTED_FIRE = register("item.heavy_revolver.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_REVOLVER_SILENCED_FIRE = register("item.heavy_revolver.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_REVOLVER_COCK = register("item.heavy_revolver.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_MICRO_SMG_FIRE = register("item.micro_smg.fire");
	public static final RegistryObject<SoundEvent> ITEM_MICRO_SMG_ENCHANTED_FIRE = register("item.micro_smg.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_MICRO_SMG_SILENCED_FIRE = register("item.micro_smg.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_MICRO_SMG_COCK = register("item.micro_smg.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_SMG_FIRE = register("item.smg.fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_ENCHANTED_FIRE = register("item.smg.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_SILENCED_FIRE = register("item.smg.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_SMG_COCK = register("item.smg.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_COMPACT_SMG_FIRE = register("item.compact_smg.fire");
	public static final RegistryObject<SoundEvent> ITEM_COMPACT_SMG_ENCHANTED_FIRE = register("item.compact_smg.enchanted_fire");
	
	public static final RegistryObject<SoundEvent> ITEM_RAPID_SMG_FIRE = register("item.rapid_smg.fire");
	public static final RegistryObject<SoundEvent> ITEM_RAPID_SMG_ENCHANTED_FIRE = register("item.rapid_smg.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_RAPID_SMG_SILENCED_FIRE = register("item.rapid_smg.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_RAPID_SMG_COCK = register("item.rapid_smg.cock");

	public static final RegistryObject<SoundEvent> ITEM_PUMP_SHOTGUN_FIRE = register("item.pump_shotgun.fire");
	public static final RegistryObject<SoundEvent> ITEM_PUMP_SHOTGUN_ENCHANTED_FIRE = register("item.pump_shotgun.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_PUMP_SHOTGUN_SILENCED_FIRE = register("item.pump_shotgun.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_PUMP_SHOTGUN_COCK = register("item.pump_shotgun.cock");

	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_FIRE = register("item.hunting_shotgun.fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_ENCHANTED_FIRE = register("item.hunting_shotgun.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_SHOTGUN_SILENCED_FIRE = register("item.hunting_shotgun.silenced_fire");
	
	public static final RegistryObject<SoundEvent> ITEM_DOUBLE_BARRELED_FIRE = register("item.double_barreled.fire");
	public static final RegistryObject<SoundEvent> ITEM_DOUBLE_BARRELED_ENCHANTED_FIRE = register("item.double_barreled.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_DOUBLE_BARRELED_COCK = register("item.double_barreled.cock");

	public static final RegistryObject<SoundEvent> ITEM_AUTOMATIC_SHOTGUN_FIRE = register("item.automatic_shotgun.fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTOMATIC_SHOTGUN_ENCHANTED_FIRE = register("item.automatic_shotgun.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTOMATIC_SHOTGUN_SILENCED_FIRE = register("item.automatic_shotgun.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTOMATIC_SHOTGUN_COCK = register("item.automatic_shotgun.cock");

	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_FIRE = register("item.heavy_ar.fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_ENCHANTED_FIRE = register("item.heavy_ar.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_SILENCED_FIRE = register("item.heavy_ar.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HEAVY_AR_COCK = register("item.heavy_ar.cock");

	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_FIRE = register("item.battle_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_ENCHANTED_FIRE = register("item.battle_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_SILENCED_FIRE = register("item.battle_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BATTLE_RIFLE_COCK = register("item.battle_rifle.cock");

	public static final RegistryObject<SoundEvent> ITEM_BULLPUP_RIFLE_FIRE = register("item.bullpup.fire");
	public static final RegistryObject<SoundEvent> ITEM_BULLPUP_RIFLE_ENCHANTED_FIRE = register("item.bullpup.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BULLPUP_RIFLE_SILENCED_FIRE = register("item.bullpup.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BULLPUP_RIFLE_COCK = register("item.bullpup.cock");

	public static final RegistryObject<SoundEvent> ITEM_MACHINE_GUN_FIRE = register("item.machine_gun.fire");
	public static final RegistryObject<SoundEvent> ITEM_MACHINE_GUN_ENCHANTED_FIRE = register("item.machine_gun.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_MACHINE_GUN_SILENCED_FIRE = register("item.machine_gun.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_MACHINE_GUN_COCK = register("item.machine_gun.cock");

	public static final RegistryObject<SoundEvent> ITEM_INFANTRY_RIFLE_FIRE = register("item.infantry_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_INFANTRY_RIFLE_ENCHANTED_FIRE = register("item.infantry_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_INFANTRY_RIFLE_SILENCED_FIRE = register("item.infantry_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_INFANTRY_RIFLE_COCK = register("item.infantry_rifle.cock");

	public static final RegistryObject<SoundEvent> ITEM_AUTO_SNIPER_RIFLE_FIRE = register("item.auto_sniper_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTO_SNIPER_RIFLE_ENCHANTED_FIRE = register("item.auto_sniper_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTO_SNIPER_RIFLE_SILENCED_FIRE = register("item.auto_sniper_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_AUTO_SNIPER_RIFLE_COCK = register("item.auto_sniper_rifle.cock");

	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_FIRE = register("item.hunting_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_ENCHANTED_FIRE = register("item.hunting_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_SILENCED_FIRE = register("item.hunting_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_HUNTING_RIFLE_COCK = register("item.hunting_rifle.cock");

	public static final RegistryObject<SoundEvent> ITEM_BOLT_ACTION_RIFLE_FIRE = register("item.bolt_action_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLT_ACTION_RIFLE_ENCHANTED_FIRE = register("item.bolt_action_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLT_ACTION_RIFLE_SILENCED_FIRE = register("item.bolt_action_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_BOLT_ACTION_RIFLE_COCK = register("item.bolt_action_rifle.cock");
	
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_FIRE = register("item.sniper_rifle.fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_ENCHANTED_FIRE = register("item.sniper_rifle.enchanted_fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_SILENCED_FIRE = register("item.sniper_rifle.silenced_fire");
	public static final RegistryObject<SoundEvent> ITEM_SNIPER_RIFLE_COCK = register("item.sniper_rifle.cock");
	
	//Method to help us register sounds
	private static RegistryObject<SoundEvent> register(String key) {
		return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(nzgmAddon.MOD_ID, key)));
	}
}