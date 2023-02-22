package zaeonninezero.nzgmaddon.common;

import com.mrcrayfish.guns.interfaces.IGunModifier;
import net.minecraft.util.Mth;

/**
 * Author: MrCrayfish
 */
public class ExtraGunModifiers
{
    public static final IGunModifier EXTENDED_BARREL_EFFECT = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.7F;
        }
		
        @Override
        public float recoilModifier()
        {
            return 0.9F;
        }

        @Override
        public float kickModifier()
        {
            return 1.1F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.95F;
        }
    };
    public static final IGunModifier FLASH_HIDER_EFFECT = new IGunModifier()
    {
        @Override
        public float modifyMuzzleFlashScale()
        {
            return 0.6F;
        }
		
        @Override
        public double modifyFireSoundRadius(double radius)
        {
            return radius * 0.8;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.95F;
        }
    };
    public static final IGunModifier MUZZLE_BRAKE_EFFECT = new IGunModifier()
    {
        @Override
        public float modifyMuzzleFlashScale()
        {
            return 1.05F;
        }
		
        @Override
        public float recoilModifier()
        {
            return 0.85F;
        }

        @Override
        public float kickModifier()
        {
            return 1.15F;
        }
    };
    public static final IGunModifier HEAVY_MUZZLE_EFFECT = new IGunModifier()
    {
        @Override
        public float modifyMuzzleFlashScale()
        {
            return 1.3F;
        }
		
        @Override
        public float recoilModifier()
        {
            return 0.7F;
        }

        @Override
        public float kickModifier()
        {
            return 1.4F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.9F;
        }
    };
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
    public static final IGunModifier ANGLED_RECOIL_CONTROL = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.7F;
        }

        @Override
        public float kickModifier()
        {
            return 0.7F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.4F;
        }
    };
}