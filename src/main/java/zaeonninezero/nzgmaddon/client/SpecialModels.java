package zaeonninezero.nzgmaddon.client;

import zaeonninezero.nzgmaddon.nzgmAddon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = nzgmAddon.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public enum SpecialModels
{
    BASIC_BULLET_LOADED("bullets/basic_bullet_loaded"),
    MEDIUM_BULLET_LOADED("bullets/medium_bullet_loaded"),
    ADVANCED_BULLET_LOADED("bullets/advanced_bullet_loaded"),
    SG_SHELL_LOADED("bullets/shell_loaded"),
	
    REVOLVER_BASE("revolver_base"),
    REVOLVER_RAIL("revolver_rail"),
    REVOLVER_SWING("revolver_swing_out"),
    REVOLVER_CYLINDER("revolver_cylinder"),
    REVOLVER_CHAMBERS("revolver_chambers"),
    REVOLVER_BULLETS("revolver_bullets"),
	
    HEAVY_PISTOL_BASE("heavy_pistol_base"),
    HEAVY_PISTOL_SLIDE("heavy_pistol_slide"),
    HEAVY_PISTOL_MAGAZINE("heavy_pistol_magazine"),
	
    HEAVY_REVOLVER_BASE("heavy_revolver_base"),
    HEAVY_REVOLVER_SWING("heavy_revolver_swing_out"),
    HEAVY_REVOLVER_CYLINDER("heavy_revolver_cylinder"),
    HEAVY_REVOLVER_CHAMBERS("heavy_revolver_chambers"),
    HEAVY_REVOLVER_BULLETS("heavy_revolver_bullets"),
	
    MICRO_SMG_BASE("gun/micro_smg/micro_smg_base"),
    MICRO_SMG_SIGHTS("gun/micro_smg/micro_smg_sights"),
    MICRO_SMG_SIGHTS_LOWERED("gun/micro_smg/micro_smg_sights_lowered"),
    MICRO_SMG_STOCK_MOUNT("gun/micro_smg/micro_smg_stock_mount"),
    MICRO_SMG_BOTTOM_RAIL("gun/micro_smg/micro_smg_bottom_rail"),
    MICRO_SMG_BOLT("gun/micro_smg/micro_smg_bolt"),
    MICRO_SMG_MAGAZINE("gun/micro_smg/micro_smg_magazine"),
    MICRO_SMG_LIGHT_MAG("gun/micro_smg/micro_smg_light_mag"),
    MICRO_SMG_EXTENDED_MAG("gun/micro_smg/micro_smg_magazine"),
	
    SUBMACHINE_GUN_BASE("submachine_gun_base"),
    SUBMACHINE_GUN_BASE_1("submachine_gun_base_1"),
    SUBMACHINE_GUN_TOP_RAIL("submachine_gun_top_rail"),
    SUBMACHINE_GUN_BOTTOM_RAIL("submachine_gun_bottom_rail"),
    SUBMACHINE_GUN_STOCK_ADAPTER("submachine_gun_stock_adapter"),
    SUBMACHINE_GUN_BOLT_HANDLE("submachine_gun_bolt_handle"),
    SUBMACHINE_GUN_MAGAZINE("submachine_gun_magazine"),
	
    COMPACT_SMG_BASE("compact_smg_base"),
    COMPACT_SMG_SIGHTS("compact_smg_sights"),
    COMPACT_SMG_MAGAZINE("compact_smg_magazine"),
	
    RAPID_SMG_BASE("rapid_smg_base"),
    RAPID_SMG_SIGHTS("rapid_smg_sights"),
    RAPID_SMG_BOLT_HANDLE("rapid_smg_bolt_handle"),
    RAPID_SMG_MAGAZINE("rapid_smg_magazine"),
    RAPID_SMG_LIGHT_MAG("rapid_smg_light_magazine"),
	
    PUMP_SHOTGUN_BASE("pump_shotgun_base"),
    PUMP_SHOTGUN_BASE_1("pump_shotgun_base_1"),
    PUMP_SHOTGUN_SIGHTS("pump_shotgun_sights"),
    PUMP_SHOTGUN_TOP_RAIL("pump_shotgun_top_rail"),
    PUMP_SHOTGUN_TOP_RAIL_1("pump_shotgun_top_rail_1"),
    PUMP_SHOTGUN_HEAT_SHIELD("pump_shotgun_heat_shield"),
    PUMP_SHOTGUN_PUMP("pump_shotgun_pump"),
	
    HUNTING_SHOTGUN_BASE("hunting_shotgun_base"),
    HUNTING_SHOTGUN_PUMP("hunting_shotgun_pump"),
	
    DOUBLE_BARRELED_SHOTGUN_BASE("gun/double_barreled_shotgun/double_barreled_shotgun_base"),
    DOUBLE_BARRELED_SHOTGUN_BREAK("gun/double_barreled_shotgun/double_barreled_shotgun_break"),
	
    AUTOMATIC_SHOTGUN_BASE("gun/automatic_shotgun/automatic_shotgun_base"),
    AUTOMATIC_SHOTGUN_SIGHTS("gun/automatic_shotgun/automatic_shotgun_sights"),
    AUTOMATIC_SHOTGUN_BOLT("gun/automatic_shotgun/automatic_shotgun_bolt"),
    AUTOMATIC_SHOTGUN_MAGAZINE("gun/automatic_shotgun/automatic_shotgun_drum"),
    AUTOMATIC_SHOTGUN_LIGHT_MAGAZINE("gun/automatic_shotgun/automatic_shotgun_light_mag"),
    AUTOMATIC_SHOTGUN_EXTENDED_MAGAZINE("gun/automatic_shotgun/automatic_shotgun_extended_drum"),
    
    HEAVY_AR_BASE("heavy_ar_base"),
    HEAVY_AR_BASE_1("heavy_ar_base_1"),
    HEAVY_AR_FORWARD_RAILS("heavy_ar_forward_rails"),
    HEAVY_AR_TOP_RAIL("heavy_ar_top_rail"),
    HEAVY_AR_BOLT_HANDLE("heavy_ar_bolt_handle"),
    HEAVY_AR_MAGAZINE("heavy_ar_magazine"),
    HEAVY_AR_LIGHT_MAG("heavy_ar_light_mag"),
    HEAVY_AR_EXTENDED_MAG("heavy_ar_extended_mag"),
	
    BATTLE_RIFLE_BASE("gun/battle_rifle/battle_rifle_base"),
    BATTLE_RIFLE_SIGHTS("gun/battle_rifle/battle_rifle_sights"),
    BATTLE_RIFLE_SIGHTS_FOLDED("gun/battle_rifle/battle_rifle_sights_folded"),
    BATTLE_RIFLE_SIGHTS_1("gun/battle_rifle/battle_rifle_sights_1"),
    BATTLE_RIFLE_SIGHTS_1_FOLDED("gun/battle_rifle/battle_rifle_sights_1_folded"),
    BATTLE_RIFLE_MAGAZINE("gun/battle_rifle/battle_rifle_magazine"),
    BATTLE_RIFLE_LIGHT_MAG("gun/battle_rifle/battle_rifle_light_mag"),
    BATTLE_RIFLE_EXTENDED_MAG("gun/battle_rifle/battle_rifle_extended_mag"),
    BATTLE_RIFLE_BOLT_HANDLE("gun/battle_rifle/battle_rifle_bolt_handle"),
	
    BULLPUP_RIFLE_BASE("bullpup_rifle_base"),
    BULLPUP_RIFLE_SIGHTS("bullpup_rifle_sights"),
    BULLPUP_RIFLE_BOLT_HANDLE("bullpup_rifle_bolt_handle"),
    BULLPUP_RIFLE_MAGAZINE("bullpup_rifle_magazine"),
    BULLPUP_RIFLE_LIGHT_MAG("bullpup_rifle_light_mag"),
    BULLPUP_RIFLE_EXTENDED_MAG("bullpup_rifle_extended_mag"),
	
    MACHINE_GUN_BASE("machine_gun_base"),
    MACHINE_GUN_SIGHTS("machine_gun_sights"),
    MACHINE_GUN_BOLT("machine_gun_bolt"),
    MACHINE_GUN_BULLET1("machine_gun_bullet1"),
    MACHINE_GUN_BULLET2("machine_gun_bullet2"),
    MACHINE_GUN_BULLET3("machine_gun_bullet3"),
    MACHINE_GUN_BULLET4("machine_gun_bullet4"),
    MACHINE_GUN_BULLET5("machine_gun_bullet5"),
    MACHINE_GUN_BOX("machine_gun_box"),
    MACHINE_GUN_LIGHT_BOX("machine_gun_light_box"),
    MACHINE_GUN_HANDLE("machine_gun_handle"),
	
    LEVER_ACTION_RIFLE_BASE("gun/lever_action_rifle/lever_action_rifle_base"),
    LEVER_ACTION_RIFLE_BASE_1("gun/lever_action_rifle/lever_action_rifle_base_1"),
    LEVER_ACTION_RIFLE_LEVER("gun/lever_action_rifle/lever_action_rifle_lever"),
    LEVER_ACTION_RIFLE_HAMMER("gun/lever_action_rifle/lever_action_rifle_hammer"),
    LEVER_ACTION_RIFLE_BOLT("gun/lever_action_rifle/lever_action_rifle_bolt"),
    LEVER_ACTION_RIFLE_SIGHTS("gun/lever_action_rifle/lever_action_rifle_rear_sights"),
    LEVER_ACTION_RIFLE_SIGHTS_1("gun/lever_action_rifle/lever_action_rifle_rear_sights_1"),
    LEVER_ACTION_RIFLE_RAIL("gun/lever_action_rifle/lever_action_rifle_rail"),
    LEVER_ACTION_RIFLE_RAIL_1("gun/lever_action_rifle/lever_action_rifle_rail_1"),
	
    INFANTRY_RIFLE_BASE("infantry_rifle_base"),
    INFANTRY_RIFLE_CHAMBER("infantry_rifle_chamber"),
    INFANTRY_RIFLE_RAIL("infantry_rifle_rail"),
    INFANTRY_RIFLE_BOTTOM_RAIL("infantry_rifle_bottom_rail"),
    INFANTRY_RIFLE_MAGAZINE("infantry_rifle_magazine"),
    INFANTRY_RIFLE_LIGHT_MAG("infantry_rifle_light_mag"),
    INFANTRY_RIFLE_EXTENDED_MAG("infantry_rifle_extended_mag"),
	
    AUTO_SNIPER_RIFLE_BASE("gun/automatic_sniper_rifle/auto_sniper_rifle_base"),
    AUTO_SNIPER_RIFLE_BASE_1("gun/automatic_sniper_rifle/auto_sniper_rifle_base_1"),
    AUTO_SNIPER_RIFLE_HANDGUARD("gun/automatic_sniper_rifle/auto_sniper_rifle_handguard"),
    AUTO_SNIPER_RIFLE_HANDGUARD_1("gun/automatic_sniper_rifle/auto_sniper_rifle_handguard_1"),
    AUTO_SNIPER_RIFLE_TOP_RAIL("gun/automatic_sniper_rifle/auto_sniper_rifle_top_rail"),
    AUTO_SNIPER_RIFLE_STOCK_ADAPTER("gun/automatic_sniper_rifle/auto_sniper_rifle_stock_adapter"),
    AUTO_SNIPER_RIFLE_BOLT_HANDLE("gun/automatic_sniper_rifle/auto_sniper_rifle_bolt_handle"),
    AUTO_SNIPER_RIFLE_MAGAZINE("gun/automatic_sniper_rifle/auto_sniper_rifle_magazine"),
    AUTO_SNIPER_RIFLE_LIGHT_MAG("gun/automatic_sniper_rifle/auto_sniper_rifle_light_mag"),
    AUTO_SNIPER_RIFLE_EXTENDED_MAG("gun/automatic_sniper_rifle/auto_sniper_rifle_extended_mag"),
    
    HUNTING_RIFLE_BASE("hunting_rifle_base"),
    HUNTING_RIFLE_BREECH("hunting_rifle_breech"),
    HUNTING_RIFLE_SIGHTS("hunting_rifle_rear_sight"),
    
    BOLT_ACTION_RIFLE_BASE("gun/bolt_action_rifle/bolt_action_rifle_base"),
    BOLT_ACTION_RIFLE_BASE_1("gun/bolt_action_rifle/bolt_action_rifle_base_1"),
    BOLT_ACTION_RIFLE_BASE_2("gun/bolt_action_rifle/bolt_action_rifle_base_2"),
    BOLT_ACTION_RIFLE_SIGHTS("gun/bolt_action_rifle/bolt_action_rifle_sights"),
    BOLT_ACTION_RIFLE_RAIL("gun/bolt_action_rifle/bolt_action_rifle_rail"),
    BOLT_ACTION_RIFLE_CHAMBER("gun/bolt_action_rifle/bolt_action_rifle_bolt1"),
    BOLT_ACTION_RIFLE_BOLT("gun/bolt_action_rifle/bolt_action_rifle_bolt2"),
    BOLT_ACTION_RIFLE_BULLET("gun/bolt_action_rifle/bolt_action_rifle_bullet"),
    BOLT_ACTION_RIFLE_LIGHT_MAG("gun/bolt_action_rifle/bolt_action_rifle_light_mag"),
    BOLT_ACTION_RIFLE_EXTENDED_MAG("gun/bolt_action_rifle/bolt_action_rifle_extended_mag"),
	
    SNIPER_RIFLE_BASE("sniper_rifle_base"),
    SNIPER_RIFLE_SIGHTS("sniper_rifle_sights"),
    SNIPER_RIFLE_CHAMBER("sniper_rifle_bolt1"),
    SNIPER_RIFLE_BOLT("sniper_rifle_bolt2"),
    SNIPER_RIFLE_MAGAZINE("sniper_rifle_magazine"),
    SNIPER_RIFLE_LIGHT_MAG("sniper_rifle_light_mag"),
    SNIPER_RIFLE_EXTENDED_MAG("sniper_rifle_extended_mag"),
    
    BIPOD_FOLDED("attachments/bipod/bipod_folded"),
    BIPOD_UNFOLDED("attachments/bipod/bipod_unfolded");

    /**
     * The location of an item model in the [MOD_ID]/models/special/[NAME] folder
     */
    private final ResourceLocation modelLocation;

    /**
     * Cached model
     */
    private BakedModel cachedModel;

    /**
     * Sets the model's location
     *
     * @param modelName name of the model file
     */
    SpecialModels(String modelName)
    {
        this.modelLocation = new ResourceLocation(nzgmAddon.MOD_ID, "special/" + modelName);
    }

    /**
     * Gets the model
     *
     * @return isolated model
     */
    public BakedModel getModel()
    {
        if(this.cachedModel == null)
        {
            this.cachedModel = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
        }
        return this.cachedModel;
    }

    /**
     * Registers the special models into the Forge Model Bakery. This is only called once on the
     * load of the game.
     */
    @SubscribeEvent
    public static void registerAdditional(ModelEvent.RegisterAdditional event)
    {
        for(SpecialModels model : values())
        {
            event.register(model.modelLocation);
        }
    }

    /**
     * Clears the cached BakedModel since it's been rebuilt. This is needed since the models may
     * have changed when a resource pack was applied, or if resources are reloaded.
     */
    @SubscribeEvent
    public static void onBake(ModelEvent.BakingCompleted event)
    {
        for(SpecialModels model : values())
        {
            model.cachedModel = null;
        }
    }
}