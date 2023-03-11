package zaeonninezero.nzgmaddon.common;

import com.mrcrayfish.guns.interfaces.IGunModifier;
import net.minecraft.util.Mth;

/**
 * Author: MrCrayfish
 */
public class ExtraGunModifiers
{
    public static final IGunModifier FLASH_HIDER_EFFECT = new IGunModifier()
    {
        @Override
        public double modifyFireSoundRadius(double radius)
        {
            return radius * 0.9;
        }

        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 0.75F;
        }
    };
    public static final IGunModifier MUZZLE_BRAKE_EFFECT = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.87F;
        }

        @Override
        public float kickModifier()
        {
            return 1.15F;
        }

        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 1.15F;
        }
    };
	
    public static final IGunModifier QUICK_ADS = new IGunModifier()
    {
        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 1.05F;
        }
    };
	
	public static final IGunModifier SLIGHT_BETTER_CONTROL = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.5F;
        }

        @Override
        public float kickModifier()
        {
            return 0.85F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.85F;
        }
    };
	
    public static final IGunModifier SOLIDLY_STABILISED = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.3F;
        }

        @Override
        public float kickModifier()
        {
            return 0.45F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.5F;
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
            return 0.25F;
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
    public static final IGunModifier HORIZONTAL_RECOIL_CONTROL = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.65F;
        }

        @Override
        public float kickModifier()
        {
            return 0.65F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.6F;
        }
    };
}