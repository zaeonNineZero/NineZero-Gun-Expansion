package zaeonninezero.nzgexpansion.init;

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
import zaeonninezero.nzgexpansion.common.ExtraGunModifiers;
import zaeonninezero.nzgexpansion.common.Scopes;
import zaeonninezero.nzgexpansion.nzgExpansion;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class initItems {
	/*
     * Create a Deferred Register to register the items to our mod.
     * This is called in the main mod file, where we will add it to the event bus.
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, nzgExpansion.MOD_ID);

    /*
     * Register a new instance of GunItem into the Deferred Register above.
     * We can just create a new instance of the Gun Item as the API allows us to edit the properties in the JSON data file.
     * In that JSON we can add and remove fields to suit our needs, take a look at the existing guns if you would like to see them
     *      https://github.com/MrCrayfish/MrCrayfishGunMod/tree/1.16.X/src/main/resources/data/cgm/guns
     * I would say, if you wanted to add something to this then make sure you know what you're doing :P
     */
	 
	//Guns
    public static final RegistryObject<GunItem> REVOLVER = ITEMS.register("revolver", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> HEAVY_PISTOL = ITEMS.register("heavy_pistol", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> HEAVY_REVOLVER = ITEMS.register("heavy_revolver", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> MICRO_SMG = ITEMS.register("micro_smg", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> SUBMACHINE_GUN = ITEMS.register("submachine_gun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<GunItem> COMPACT_SMG = ITEMS.register("compact_smg", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> RAPID_SMG = ITEMS.register("rapid_smg", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> PUMP_SHOTGUN = ITEMS.register("pump_shotgun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> HUNTING_SHOTGUN = ITEMS.register("hunting_shotgun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> DOUBLE_BARRELED_SHOTGUN = ITEMS.register("double_barreled_shotgun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<GunItem> AUTOMATIC_SHOTGUN = ITEMS.register("automatic_shotgun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> HEAVY_ASSAULT_RIFLE = ITEMS.register("heavy_assault_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> BATTLE_RIFLE = ITEMS.register("battle_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> BULLPUP_RIFLE = ITEMS.register("bullpup_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<GunItem> MACHINE_GUN = ITEMS.register("machine_gun", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<GunItem> INFANTRY_RIFLE = ITEMS.register("infantry_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<GunItem> AUTOMATIC_SNIPER_RIFLE = ITEMS.register("automatic_sniper_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> HUNTING_RIFLE = ITEMS.register("hunting_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> BOLT_ACTION_RIFLE = ITEMS.register("bolt_action_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<GunItem> SNIPER_RIFLE = ITEMS.register("sniper_rifle", () -> new GunItem(new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	
	//Ammo
	public static final RegistryObject<Item> MEDIUM_BULLET = ITEMS.register("medium_bullet", () -> new AmmoItem(new Item.Properties().tab(nzgExpansion.GROUP)));
	
	//Scopes / Sights
	public static final RegistryObject<Item> DOT_SIGHT = ITEMS.register("dot_sight", () -> new ScopeItem(Scopes.DOT_SIGHT, new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<Item> CHEVRON_SCOPE = ITEMS.register("chevron_scope", () -> new ScopeItem(Scopes.CHEVRON_SCOPE, new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	public static final RegistryObject<Item> BALLISTIC_SCOPE = ITEMS.register("ballistic_scope", () -> new ScopeItem(Scopes.BALLISTIC_SCOPE, new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	
	//Barrels
    public static final RegistryObject<Item> FLASH_HIDER  = ITEMS.register("flash_hider", () -> new BarrelItem(Barrel.create(4,ExtraGunModifiers.FLASH_HIDER_EFFECT), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> MUZZLE_BRAKE  = ITEMS.register("muzzle_brake", () -> new BarrelItem(Barrel.create(5.3f,ExtraGunModifiers.MUZZLE_BRAKE_EFFECT), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> EXTENDED_BARREL  = ITEMS.register("extended_barrel", () -> new BarrelItem(Barrel.create(6,ExtraGunModifiers.EXTENDED_BARREL_EFFECT), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> IMPROVED_SILENCER = ITEMS.register("improved_silencer", () -> new BarrelItem(Barrel.create(12.2F, GunModifiers.SILENCED, ExtraGunModifiers.MUFFLED_IMPROVED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> PERFORMANCE_SILENCER = ITEMS.register("performance_silencer", () -> new BarrelItem(Barrel.create(12.2F, GunModifiers.SILENCED, ExtraGunModifiers.MUFFLED_PERFORMANCE), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	
	//Stocks
    public static final RegistryObject<Item> SHORT_STOCK  = ITEMS.register("short_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.SLIGHT_BETTER_CONTROL), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> DYEABLE_TACTICAL_STOCK  = ITEMS.register("dyeable_tactical_stock", () -> new StockItem(Stock.create(GunModifiers.STABILISED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> SOLID_STOCK = ITEMS.register("solid_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.SOLIDLY_STABILISED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> STABILIZING_STOCK = ITEMS.register("stabilizing_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.EXTRA_STABILISED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> CARBINE_STOCK  = ITEMS.register("carbine_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.CARBINE_STABILISED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
    public static final RegistryObject<Item> MARKSMAN_STOCK  = ITEMS.register("marksman_stock", () -> new StockItem(Stock.create(ExtraGunModifiers.MARKSMAN_STABILISED), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
	
	//Grips / Under Barrel
    public static final RegistryObject<Item> HORIZONTAL_GRIP  = ITEMS.register("horizontal_grip", () -> new UnderBarrelItem(UnderBarrel.create(ExtraGunModifiers.HORIZONTAL_CONTROL), new Item.Properties().stacksTo(1).tab(nzgExpansion.GROUP)));
}