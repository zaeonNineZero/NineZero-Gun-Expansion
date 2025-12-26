package zaeonninezero.nzgmaddon.util;

import com.mrcrayfish.guns.GunMod;

public class CGMExpandedHelper
{
	private static boolean expandedInstalled = false;
	private static boolean detectFail = false;
	
    public static boolean isExpandedInstalled()
    {
    	if (detectFail)
    		return false;
    	
    	try
    	{
    		if (GunMod.hasCGMExpanded())
    		{
    			expandedInstalled = true;
    			return expandedInstalled;
    		}
    		else
    		return false; //Fallback return line that shouldn't occur; hasCGMExpanded() always returns true.
    	}
    	catch (NoSuchMethodError ignored)
    	{
    		detectFail = true;
        	return false;
    	}
    }
}