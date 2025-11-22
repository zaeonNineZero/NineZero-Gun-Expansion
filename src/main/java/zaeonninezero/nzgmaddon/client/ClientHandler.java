package zaeonninezero.nzgmaddon.client;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
//import com.mrcrayfish.guns.client.render.gun.model.SimpleModel;
import zaeonninezero.nzgmaddon.client.render.gun.model.*;
import zaeonninezero.nzgmaddon.nzgmAddon;
import zaeonninezero.nzgmaddon.init.initItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = nzgmAddon.MOD_ID, value = Dist.CLIENT)
public class ClientHandler
{
    public static void setup()
    {
        registerModelOverrides();
    }
	
	private static void registerModelOverrides()
    {
		ModelOverrides.register(initItems.REVOLVER.get(), new RevolverModel());
        ModelOverrides.register(initItems.HEAVY_PISTOL.get(), new HeavyPistolModel());
        ModelOverrides.register(initItems.HEAVY_REVOLVER.get(), new HeavyRevolverModel());
        ModelOverrides.register(initItems.UZI.get(), new MicroSMGModel());
        ModelOverrides.register(initItems.SUBMACHINE_GUN.get(), new SubmachineGunModel());
        ModelOverrides.register(initItems.COMPACT_SMG.get(), new CompactSMGModel());
        ModelOverrides.register(initItems.RAPID_SMG.get(), new RapidSMGModel());
        ModelOverrides.register(initItems.PUMP_SHOTGUN.get(), new PumpShotgunModel());
        ModelOverrides.register(initItems.HUNTING_SHOTGUN.get(), new HuntingShotgunModel());
        ModelOverrides.register(initItems.DOUBLE_BARRELED_SHOTGUN.get(), new DoubleBarreledShotgunModel());
        ModelOverrides.register(initItems.AUTOMATIC_SHOTGUN.get(), new AutomaticShotgunModel());;
		ModelOverrides.register(initItems.HEAVY_ASSAULT_RIFLE.get(), new HeavyAssaultRifleModel());
        ModelOverrides.register(initItems.BATTLE_RIFLE.get(), new BattleRifleModel());
        ModelOverrides.register(initItems.BULLPUP_RIFLE.get(), new BullpupRifleModel());
        ModelOverrides.register(initItems.MACHINE_GUN.get(), new MachineGunModel());
        ModelOverrides.register(initItems.LEVER_ACTION_RIFLE.get(), new LeverActionRifleModel());
        ModelOverrides.register(initItems.INFANTRY_RIFLE.get(), new InfantryRifleModel());
        ModelOverrides.register(initItems.AUTOMATIC_SNIPER_RIFLE.get(), new AutomaticSniperRifleModel());
        ModelOverrides.register(initItems.BOLT_ACTION_RIFLE.get(), new BoltActionRifleModel());
        ModelOverrides.register(initItems.HUNTING_RIFLE.get(), new HuntingRifleModel());
        ModelOverrides.register(initItems.SNIPER_RIFLE.get(), new SniperRifleModel());

		ModelOverrides.register(initItems.SOLID_STOCK.get(), new SolidStockModel());
		ModelOverrides.register(initItems.MARKSMAN_STOCK.get(), new MarksmanStockModel());
		ModelOverrides.register(initItems.BIPOD.get(), new BipodModel());
    }
}