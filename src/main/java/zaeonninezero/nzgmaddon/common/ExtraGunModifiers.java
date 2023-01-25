package zaeonninezero.nzgmaddon.common;

import com.mrcrayfish.guns.interfaces.IGunModifier;
import net.minecraft.util.Mth;

/**
 * Author: MrCrayfish
 */
public class ExtraGunModifiers
{
    public static final IGunModifier SOLIDLY_STABILISED = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.4F;
        }

        @Override
        public float kickModifier()
        {
            return 0.3F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.6F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.85F;
        }
    };
	public static final IGunModifier EXTRA_STABILISED = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.3F;
        }

        @Override
        public float kickModifier()
        {
            return 0.4F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.4F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.75F;
        }

        @Override
        public int modifyFireRate(int rate)
        {
            return Mth.clamp((int) (rate * 1.1), rate + 1, Integer.MAX_VALUE);
        }
    };
}