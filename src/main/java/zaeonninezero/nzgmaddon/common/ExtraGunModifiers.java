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
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.95F;
        }
        
        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 0.9F;
        }
    };
    public static final IGunModifier COMPENSATOR_MUZZLE_EFFECT = new IGunModifier()
    {
    	@Override
        public float recoilModifier()
        {
            return 0.8F;
        }
		
		@Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.96F;
        }

        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 1.15F;
        }
    };
    public static final IGunModifier MUZZLE_BRAKE_EFFECT = new IGunModifier()
    {
    	@Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.9F;
        }
    	
    	@Override
        public float recoilModifier()
        {
            return 0.95F;
        }

        @Override
        public float kickModifier()
        {
            return 0.9F;
        }
		
		@Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.96F;
        }

        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 1.15F;
        }
    };
    public static final IGunModifier EXTENDED_BARREL_EFFECT = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.67F;
        }
        
        @Override
        public double modifyProjectileSpeed(double speed)
        {
            return speed * 1.25;
        }
        
        @Override
        public float modifyProjectileDamage(float damage)
        {
            return damage * 1.1F;
        }
		
		@Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.92F;
        }

        @Override
        public double modifyMuzzleFlashScale(double scale)
        {
            return 1.05F;
        }
    };
    public static final IGunModifier MUFFLED_IMPROVED = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.85F;
        }
        
        @Override
        public double modifyProjectileSpeed(double speed)
        {
            return speed * 0.8;
        }
        
        @Override
        public float modifyProjectileDamage(float damage)
        {
            return damage * 0.95F;
        }
		
		@Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.95F;
        }
    };
    public static final IGunModifier MUFFLED_PERFORMANCE = new IGunModifier()
    {
        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.75F;
        }
        
        @Override
        public double modifyProjectileSpeed(double speed)
        {
            return speed * 0.65;
        }
        
        @Override
        public float modifyProjectileDamage(float damage)
        {
            return damage * 0.95F;
        }
		
		@Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.9F;
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
    public static final IGunModifier SCOPE_ADS = new IGunModifier()
    {
        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.8F;
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
            return 0.35F;
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
	public static final IGunModifier CARBINE_STABILISED = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.35F;
        }

        @Override
        public float kickModifier()
        {
            return 0.4F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.5F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.9F;
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
            return spread * 0.67F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.85F;
        }

        /*@Override
        public int modifyFireRate(int rate)
        {
            return Mth.clamp((int) (rate * 1.1), rate + 1, Integer.MAX_VALUE);
        }*/
    };
	public static final IGunModifier MARKSMAN_STABILISED = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.29F;
        }

        @Override
        public float kickModifier()
        {
            return 0.35F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.25F;
        }

        @Override
        public double modifyAimDownSightSpeed(double speed)
        {
            return speed * 0.75F;
        }

        /*@Override
        public int modifyFireRate(int rate)
        {
            return Math.min(rate + 1, Integer.MAX_VALUE);
        }*/
    };
	
	
    public static final IGunModifier HORIZONTAL_CONTROL = new IGunModifier()
    {
        @Override
        public float recoilModifier()
        {
            return 0.6F;
        }

        @Override
        public float kickModifier()
        {
            return 0.75F;
        }

        @Override
        public float modifyProjectileSpread(float spread)
        {
            return spread * 0.6F;
        }
    };
}