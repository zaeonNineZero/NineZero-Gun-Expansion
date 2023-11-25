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
    REVOLVER("gun/revolver"),
    MICRO_SMG("gun/micro_smg"),
    SUBMACHINE_GUN("gun/submachine_gun"),
    RAPID_SMG("gun/rapid_smg"),
    PUMP_SHOTGUN("gun/pump_shotgun"),
    HUNTING_SHOTGUN("gun/hunting_shotgun"),
    DOUBLE_BARRELED_SHOTGUN("gun/double_barreled_shotgun"),
    AUTOMATIC_SHOTGUN("gun/automatic_shotgun"),
    HEAVY_ASSAULT_RIFLE("gun/heavy_assault_rifle"),
    BATTLE_RIFLE("gun/battle_rifle"),
    MACHINE_GUN("gun/machine_gun"),
    INFANTRY_RIFLE("gun/infantry_rifle"),
    AUTOMATIC_SNIPER_RIFLE("gun/automatic_sniper_rifle"),
    HUNTING_RIFLE("gun/hunting_rifle"),
    BOLT_ACTION_RIFLE("gun/bolt_action_rifle"),
    SNIPER_RIFLE("gun/sniper_rifle");

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