package zaeonninezero.nzgexpansion.client;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
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
        ModelOverrides.register(initItems.DOT_SIGHT.get(), new DotSightModel());
        ModelOverrides.register(initItems.CHEVRON_SCOPE.get(), new ChevronScopeModel());
        ModelOverrides.register(initItems.BALLISTIC_SCOPE.get(), new BallisticScopeModel());
    }
}