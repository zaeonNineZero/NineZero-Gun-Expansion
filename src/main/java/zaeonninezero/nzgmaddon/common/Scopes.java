package zaeonninezero.nzgmaddon.common;

import com.mrcrayfish.guns.item.attachment.impl.Scope;
import com.mrcrayfish.guns.common.GunModifiers;

/**
 * Original by MrCrayfish - Copied and modified by NineZero for NZGE
 */
public class Scopes
{
    public static final Scope DOT_SIGHT = Scope.builder().aimFovModifier(0.8F).modifiers(ExtraGunModifiers.QUICK_ADS).build();
    public static final Scope CHEVRON_SCOPE = Scope.builder().aimFovModifier(0.45F).modifiers(GunModifiers.SLOW_ADS).build();
    public static final Scope BALLISTIC_SCOPE = Scope.builder().aimFovModifier(0.19F).modifiers(GunModifiers.SLOWER_ADS).build();
}