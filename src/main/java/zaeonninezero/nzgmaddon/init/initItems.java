package zaeonninezero.nzgmaddon.init;

import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.BarrelItem;
import com.mrcrayfish.guns.item.ScopeItem;
import com.mrcrayfish.guns.item.StockItem;
import com.mrcrayfish.guns.item.UnderBarrelItem;
import com.mrcrayfish.guns.item.attachment.impl.Barrel;
import com.mrcrayfish.guns.item.attachment.impl.Stock;
import com.mrcrayfish.guns.item.attachment.impl.UnderBarrel;
import com.mrcrayfish.guns.common.GunModifiers;
import zaeonninezero.nzgmaddon.common.ExtraGunModifiers;
import zaeonninezero.nzgmaddon.nzgmAddon;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class initItems {
	/*
     * Create a Deferred Register to register the items to our mod.
     * This is called in the main mod file, where we will add it to the event bus.
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, nzgmAddon.MOD_ID);

    /*
     * Register a new instance of GunItem into the Deferred Register above.
     * We can just create a new instance of the Gun Item as the API allows us to edit the properties in the JSON data file.
     * In that JSON we can add and remove fields to suit our needs, take a look at the existing guns if you would like to see them
     *      https://github.com/MrCrayfish/MrCrayfishGunMod/tree/1.16.X/src/main/resources/data/cgm/guns
     * I would say, if you wanted to add something to this then make sure you know what you're doing :P
     */
	 
	//Guns
    public static final RegistryObject<GunItem> REVOLVER = ITEMS.register("revolver", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> UZI = ITEMS.register("uzi", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> SUBMACHINE_GUN = ITEMS.register("submachine_gun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> HUNTING_SHOTGUN = ITEMS.register("hunting_shotgun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> HEAVY_ASSAULT_RIFLE = ITEMS.register("heavy_assault_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> BATTLE_RIFLE = ITEMS.register("battle_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> HUNTING_RIFLE = ITEMS.register("hunting_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<GunItem> SNIPER_RIFLE = ITEMS.register("sniper_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
	
	//Ammo
	public static final RegistryObject<Item> MEDIUM_BULLET = ITEMS.register("medium_bullet", () -> new AmmoItem(new Item.Properties().tab(GunMod.GROUP)));
	
	//Barrels
    //public static final RegistryObject<Item> EXTENDED_BARREL  = ITEMS.register("extended_barrel", () -> new BarrelItem(Barrel.create(ExtraGunModifiers.EXTENDED_BARREL_EFFECT), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    //public static final RegistryObject<Item> FLASH_HIDER  = ITEMS.register("flash_hider", () -> new BarrelItem(Barrel.create(ExtraGunModifiers.FLASH_HIDER_EFFECT), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<Item> MUZZLE_BRAKE  = ITEMS.register("muzzle_brake", () -> new BarrelItem(Barrel.create(7,ExtraGunModifiers.MUZZLE_BRAKE_EFFECT), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    //public static final RegistryObject<Item> HEAVY_MUZZLE_BRAKE  = ITEMS.register("heavy_muzzle_brake", () -> new BarrelItem(Barrel.create(ExtraGunModifiers.HEAVY_MUZZLE_EFFECT), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
	
	//Stocks
    public static final RegistryObject<Item> DYEABLE_TACTICAL_STOCK  = ITEMS.register("dyeable_tactical_stock", () -> new StockItem(Stock.create(GunModifiers.STABILISED), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<Item> SOLID_STOCK = ITEMS.register("solid_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.SOLIDLY_STABILISED), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    public static final RegistryObject<Item> STABILIZING_STOCK = ITEMS.register("stabilizing_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.EXTRA_STABILISED), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
    //public static final RegistryObject<Item> CARBINE_STOCK  = ITEMS.register("carbine_stock", () -> new StockItem(Stock.create(GunModifiers.STABILISED), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
	
	//Grips / Under Barrel
    //public static final RegistryObject<Item> ADVANCED_GRIP  = ITEMS.register("advanced_grip", () -> new UnderBarrelItem(UnderBarrel.create(ExtraGunModifiers.ANGLED_RECOIL_CONTROL), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));
}