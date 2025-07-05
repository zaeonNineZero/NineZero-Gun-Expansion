package zaeonninezero.nzgmaddon.util;

import com.mrcrayfish.guns.GunMod;

public class CGMExpandedHelper
{
	private static boolean detectFail = false;
	
    public static boolean isExpandedInstalled()
    {
    	if (detectFail)
    		return false;
    	
    	try
    	{
    		return GunMod.hasCGMExpanded();
    	}
    	catch (NoSuchMethodError ignored)
    	{
    		detectFail = true;
    	}
    	
    	return false;
    }
}