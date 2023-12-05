package zaeonninezero.nzgexpansion.client;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.client.render.gun.model.SimpleModel;
import zaeonninezero.nzgexpansion.nzgExpansion;
import zaeonninezero.nzgexpansion.init.initItems;
import zaeonninezero.nzgexpansion.client.SpecialModels;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.ItemStack;
import com.mrcrayfish.guns.item.GunItem;

@Mod.EventBusSubscriber(modid = nzgExpansion.MOD_ID, value = Dist.CLIENT)
public class ClientHandler
{
    public static void setup()
    {
        registerModelOverrides();
    }
	
	private static void registerModelOverrides()
    {
        ModelOverrides.register(initItems.REVOLVER.get(), new SimpleModel(SpecialModels.REVOLVER::getModel));
		ModelOverrides.register(initItems.MICRO_SMG.get(), new SimpleModel(SpecialModels.MICRO_SMG::getModel));
		ModelOverrides.register(initItems.SUBMACHINE_GUN.get(), new SimpleModel(SpecialModels.SUBMACHINE_GUN::getModel));
		ModelOverrides.register(initItems.RAPID_SMG.get(), new SimpleModel(SpecialModels.RAPID_SMG::getModel));
		ModelOverrides.register(initItems.PUMP_SHOTGUN.get(), new SimpleModel(SpecialModels.PUMP_SHOTGUN::getModel));
		ModelOverrides.register(initItems.HUNTING_SHOTGUN.get(), new SimpleModel(SpecialModels.HUNTING_SHOTGUN::getModel));
		ModelOverrides.register(initItems.DOUBLE_BARRELED_SHOTGUN.get(), new SimpleModel(SpecialModels.DOUBLE_BARRELED_SHOTGUN::getModel));
		ModelOverrides.register(initItems.AUTOMATIC_SHOTGUN.get(), new SimpleModel(SpecialModels.AUTOMATIC_SHOTGUN::getModel));
		ModelOverrides.register(initItems.HEAVY_ASSAULT_RIFLE.get(), new SimpleModel(SpecialModels.HEAVY_ASSAULT_RIFLE::getModel));
		ModelOverrides.register(initItems.BATTLE_RIFLE.get(), new SimpleModel(SpecialModels.BATTLE_RIFLE::getModel));
		ModelOverrides.register(initItems.MACHINE_GUN.get(), new SimpleModel(SpecialModels.MACHINE_GUN::getModel));
		ModelOverrides.register(initItems.INFANTRY_RIFLE.get(), new SimpleModel(SpecialModels.INFANTRY_RIFLE::getModel));
		ModelOverrides.register(initItems.AUTOMATIC_SNIPER_RIFLE.get(), new SimpleModel(SpecialModels.AUTOMATIC_SNIPER_RIFLE::getModel));
		ModelOverrides.register(initItems.HUNTING_RIFLE.get(), new SimpleModel(SpecialModels.HUNTING_RIFLE::getModel));
		ModelOverrides.register(initItems.BOLT_ACTION_RIFLE.get(), new SimpleModel(SpecialModels.BOLT_ACTION_RIFLE::getModel));
		ModelOverrides.register(initItems.SNIPER_RIFLE.get(), new SimpleModel(SpecialModels.SNIPER_RIFLE::getModel));
    }
	
	public static void onRegisterCreativeTab(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(new ResourceLocation(nzgExpansion.MOD_ID, "creative_tab"), builder ->
        {
            builder.title(Component.translatable("itemGroup." + nzgExpansion.MOD_ID));
            builder.icon(() -> {
                ItemStack stack = new ItemStack(initItems.REVOLVER.get());
                stack.getOrCreateTag().putBoolean("IgnoreAmmo", true);
                return stack;
            });
            builder.displayItems((flags, output) ->
            {
                initItems.ITEMS.getEntries().forEach(registryObject ->
                {
                    if(registryObject.get() instanceof GunItem item)
                    {
                        ItemStack stack = new ItemStack(item);
                        stack.getOrCreateTag().putInt("AmmoCount", item.getGun().getGeneral().getMaxAmmo());
                        output.accept(stack);
                        return;
                    }
                    output.accept(registryObject.get());
                });
            });
        });
    }
}