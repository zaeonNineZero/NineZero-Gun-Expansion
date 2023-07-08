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
    REVOLVER("gun/revolver"),
    UZI("gun/uzi"),
    SUBMACHINE_GUN("gun/submachine_gun"),
    RAPID_SMG("gun/rapid_smg"),
    HUNTING_SHOTGUN("gun/hunting_shotgun"),
    DOUBLE_BARRELED_SHOTGUN("gun/double_barreled_shotgun"),
    AUTOMATIC_SHOTGUN("gun/automatic_shotgun"),
    HEAVY_ASSAULT_RIFLE("gun/heavy_assault_rifle"),
    BATTLE_RIFLE("gun/battle_rifle"),
    MACHINE_GUN("gun/machine_gun"),
    HUNTING_RIFLE("gun/hunting_rifle"),
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