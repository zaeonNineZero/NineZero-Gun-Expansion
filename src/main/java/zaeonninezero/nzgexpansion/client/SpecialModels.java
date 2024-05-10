package zaeonninezero.nzgexpansion.client;

import zaeonninezero.nzgexpansion.nzgExpansion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = nzgExpansion.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public enum SpecialModels
{
    //REVOLVER("gun/revolver"),
    HEAVY_PISTOL("gun/heavy_pistol"),
    //UZI("gun/uzi"),
    //SUBMACHINE_GUN("gun/submachine_gun"),
    //COMPACT_SMG("gun/compact_smg"),
    //RAPID_SMG("gun/rapid_smg"),
    //PUMP_SHOTGUN("gun/pump_shotgun"),
    //HUNTING_SHOTGUN("gun/hunting_shotgun"),
    DOUBLE_BARRELED_SHOTGUN("gun/double_barreled_shotgun"),
    //AUTOMATIC_SHOTGUN("gun/automatic_shotgun"),
    HEAVY_ASSAULT_RIFLE("gun/heavy_assault_rifle"),
    HEAVY_ASSAULT_RIFLE_1("gun/heavy_assault_rifle_1"),
    //BATTLE_RIFLE("gun/battle_rifle"),
    //BULLPUP_RIFLE("gun/bullpup_rifle"),
    //MACHINE_GUN("gun/machine_gun"),
    //INFANTRY_RIFLE("gun/infantry_rifle"),
    AUTOMATIC_SNIPER_RIFLE("gun/automatic_sniper_rifle"),
    HUNTING_RIFLE("gun/hunting_rifle"),
    //BOLT_ACTION_RIFLE("gun/bolt_action_rifle"),
    //SNIPER_RIFLE("gun/sniper_rifle"),
	
    REVOLVER_BASE("revolver_base"),
    REVOLVER_CYLINDER("revolver_cylinder"),
    REVOLVER_RAIL("revolver_rail"),
	
    HEAVY_REVOLVER_BASE("heavy_revolver_base"),
    HEAVY_REVOLVER_CYLINDER("heavy_revolver_cylinder"),
	
    MICRO_SMG_BASE("micro_smg_base"),
    MICRO_SMG_RAIL("micro_smg_rail"),
    MICRO_SMG_CHARGEHANDLE("micro_smg_chargehandle"),
	
    SUBMACHINE_GUN_BASE("submachine_gun_base"),
    SUBMACHINE_GUN_SIGHTS("submachine_gun_sights"),
	
    COMPACT_SMG_BASE("compact_smg_base"),
    COMPACT_SMG_SIGHTS("compact_smg_sights"),
	
    RAPID_SMG_BASE("rapid_smg_base"),
    RAPID_SMG_SIGHTS("rapid_smg_sights"),
	
    PUMP_SHOTGUN_BASE("pump_shotgun_base"),
    PUMP_SHOTGUN_BASE_1("pump_shotgun_base_1"),
    PUMP_SHOTGUN_SIGHTS("pump_shotgun_sights"),
    PUMP_SHOTGUN_PUMP("pump_shotgun_pump"),
	
    HUNTING_SHOTGUN_BASE("hunting_shotgun_base"),
    HUNTING_SHOTGUN_PUMP("hunting_shotgun_pump"),
	
    AUTOMATIC_SHOTGUN_BASE("automatic_shotgun_base"),
    AUTOMATIC_SHOTGUN_SIGHTS("automatic_shotgun_sights"),
	
    BATTLE_RIFLE_BASE("battle_rifle_base"),
    BATTLE_RIFLE_SIGHTS("battle_rifle_sights"),
	
    BULLPUP_RIFLE_BASE("bullpup_rifle_base"),
    BULLPUP_RIFLE_SIGHTS("bullpup_rifle_sights"),
	
    MACHINE_GUN_BASE("machine_gun_base"),
    MACHINE_GUN_SIGHTS("machine_gun_sights"),
    MACHINE_GUN_BELT("machine_gun_belt"),
    MACHINE_GUN_BULLET1("machine_gun_bullet1"),
    MACHINE_GUN_BULLET2("machine_gun_bullet2"),
    MACHINE_GUN_BULLET3("machine_gun_bullet3"),
    MACHINE_GUN_BULLET4("machine_gun_bullet4"),
    MACHINE_GUN_BULLET5("machine_gun_bullet5"),
    MACHINE_GUN_HANDLE("machine_gun_handle"),
	
    INFANTRY_RIFLE_BASE("infantry_rifle_base"),
    INFANTRY_RIFLE_CHAMBER("infantry_rifle_chamber"),
    
    BOLT_ACTION_RIFLE_BASE("bolt_action_rifle_base"),
    BOLT_ACTION_RIFLE_BASE_1("bolt_action_rifle_base_1"),
    BOLT_ACTION_RIFLE_SIGHTS("bolt_action_rifle_sights"),
    BOLT_ACTION_RIFLE_RAIL("bolt_action_rifle_rail"),
    BOLT_ACTION_RIFLE_CHAMBER("bolt_action_rifle_bolt1"),
    BOLT_ACTION_RIFLE_BOLT("bolt_action_rifle_bolt2"),
	
    SNIPER_RIFLE_BASE("sniper_rifle_base"),
    SNIPER_RIFLE_SIGHTS("sniper_rifle_sights"),
    SNIPER_RIFLE_CHAMBER("sniper_rifle_bolt1"),
    SNIPER_RIFLE_BOLT("sniper_rifle_bolt2");

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
        this.modelLocation = new ResourceLocation(nzgExpansion.MOD_ID, "special/" + modelName);
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
    public static void register(ModelRegistryEvent event)
    {
        for(SpecialModels model : values())
        {
            ForgeModelBakery.addSpecialModel(model.modelLocation);
        }
    }

    /**
     * Clears the cached BakedModel since it's been rebuilt. This is needed since the models may
     * have changed when a resource pack was applied, or if resources are reloaded.
     */
    @SubscribeEvent
    public static void onBake(ModelBakeEvent event)
    {
        for(SpecialModels model : values())
        {
            model.cachedModel = null;
        }
    }
}