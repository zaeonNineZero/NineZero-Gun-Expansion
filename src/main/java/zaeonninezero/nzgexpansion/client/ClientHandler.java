package zaeonninezero.nzgexpansion.client;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.client.render.gun.model.SimpleModel;
import zaeonninezero.nzgexpansion.client.render.gun.model.*;
import zaeonninezero.nzgexpansion.nzgExpansion;
import zaeonninezero.nzgexpansion.init.initItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = nzgExpansion.MOD_ID, value = Dist.CLIENT)
public class ClientHandler
{
    public static void setup()
    {
        registerModelOverrides();
    }
	
	private static void registerModelOverrides()
    {
        ModelOverrides.register(initItems.HEAVY_PISTOL.get(), new SimpleModel(SpecialModels.HEAVY_PISTOL::getModel));
		ModelOverrides.register(initItems.HUNTING_SHOTGUN.get(), new SimpleModel(SpecialModels.HUNTING_SHOTGUN::getModel));
		ModelOverrides.register(initItems.DOUBLE_BARRELED_SHOTGUN.get(), new SimpleModel(SpecialModels.DOUBLE_BARRELED_SHOTGUN::getModel));
		ModelOverrides.register(initItems.HEAVY_ASSAULT_RIFLE.get(), new SimpleModel(SpecialModels.HEAVY_ASSAULT_RIFLE::getModel));
		ModelOverrides.register(initItems.AUTOMATIC_SNIPER_RIFLE.get(), new SimpleModel(SpecialModels.AUTOMATIC_SNIPER_RIFLE::getModel));
		ModelOverrides.register(initItems.HUNTING_RIFLE.get(), new SimpleModel(SpecialModels.HUNTING_RIFLE::getModel));
		
        ModelOverrides.register(initItems.REVOLVER.get(), new RevolverModel());
        ModelOverrides.register(initItems.HEAVY_REVOLVER.get(), new HeavyRevolverModel());
        ModelOverrides.register(initItems.MICRO_SMG.get(), new MicroSMGModel());
        ModelOverrides.register(initItems.SUBMACHINE_GUN.get(), new SubmachineGunModel());
        ModelOverrides.register(initItems.COMPACT_SMG.get(), new CompactSMGModel());
        ModelOverrides.register(initItems.RAPID_SMG.get(), new RapidSMGModel());
        ModelOverrides.register(initItems.PUMP_SHOTGUN.get(), new PumpShotgunModel());
        ModelOverrides.register(initItems.AUTOMATIC_SHOTGUN.get(), new AutomaticShotgunModel());
        ModelOverrides.register(initItems.BATTLE_RIFLE.get(), new BattleRifleModel());
        ModelOverrides.register(initItems.BULLPUP_RIFLE.get(), new BullpupRifleModel());
        ModelOverrides.register(initItems.INFANTRY_RIFLE.get(), new InfantryRifleModel());
        ModelOverrides.register(initItems.MACHINE_GUN.get(), new MachineGunModel());
        ModelOverrides.register(initItems.BOLT_ACTION_RIFLE.get(), new BoltActionRifleModel());
        ModelOverrides.register(initItems.SNIPER_RIFLE.get(), new SniperRifleModel());
    }
}