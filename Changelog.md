# Changelog (1.19.3 Releases)




### 1.4.4 'Gunslingers' (5/??/23)
This release includes the v1.4.0 'Gunslingers' major update and all patches up to and including the new v1.4.4 patch.

* Added the following new weapons: Heavy Pistol, Heavy Revolver, Compact SMG, and Bullpup Rifle.
* Added two new silencers: the Improved Silencer and the Performance Silencer.
  -  Both silencers have better stats than CGM's Silencer, at the cost of muzzle velocity. Lead your shots accordingly!
  
* Added model overrides for many of the guns in the addon, adding removable iron sights to many of them.
* The following models received noteworthy changes beyond removable iron sights, with some including animated elements:
  -  The Revolver received a full model overhaul, sporting a more slender design based on the Colt Python. Also, the cylinder now rotates!
  -  The Battle Rifle's design now bears a resemblance to the SCAR-H, helping to differentiate it from CGM's Assault Rifle.
  -  The Machine Gun's bullet belt has received proper animations, 'feeding' bullets from the box into the chamber.
  -  The Infantry Rifle's bolt/breech cycles back with each shot, and its top-mounted rail now only appears when a scope is attached.
  -  The Sniper Rifle and Bolt Action Rifle received changes to the bolt assembly. The Sniper Rifle's bolt now resembles the AWM's bolt.
  
* Added some basic animations to the Pump Shotgun, Hunting Shotgun, Bolt Action Rifle, and Sniper Rifle.
  -  The shotguns' slide grips move back and forth after firing.
  -  The rifles' bolts rotate and move back and forth after firing. The models also received some touch-ups with this patch.
  -  All received new chambering sounds, with the shotguns sharing chambering sounds and the rifles getting separate chambering sounds.
  -  The fire sounds for these guns have been edited to include a sample of the chambering sound to match the animations.
  -  Changed the fire rate of the Pump Shotgun and Hunting Shotgun to match up the animations to the new fire sounds. (v1.4.3 change)
  
* Added experimental "skin variants" for the Pump Shotgun, Heavy Assault Rifle, and Bolt Action Rifle.
  -  For the Pump Shotgun: The body becomes an undyeable gray, similar to the Hunting Shotgun.
  -  For the Assault Rifle: The body becomes white by default, and changes to match the dye coloration.
  -  For the Bolt Action Rifle: The stock extends forward to wrap around the barrel, like an early bolt-action service rifle.
  -  These variants are all craftable in survival mode for the same cost as the default gun versions.
  -  Technical notes: the model code checks the "CustomModelData" tag to determine the model variant. The use of "CustomModelData" is arbitrary and may change in the future.

* Updated the fire sounds of several guns for better quality and feel. Enchanted gunfire sounds now use a "flanger" layering effect.
  -  The new fire sounds for the Heavy AR are an experimental change that will be subject to user feedback.
* Model touch-ups to various guns and attachments to reduce Z-fighting issues.
* Adjusted the Stabilizing Stock's stat modifiers - reduced the ADS speed penalty, but decreased spread reduction.
* Stat modifier tweaks across the board for almost every attachment, generally reducing ADS speed penalties.
  -  Adjusted the model and ADS parameters for the Ballistic Scope, and very slightly increased camera FOV zoom.
  -  The Stabilizing Stock's spread reduction is reduced to match its reduced ADS speed penalty.
  -  Reduced the fire rate penalty of the Marksman Stock to one extra tick - penalty is no longer a multiplier.

The en_us, zh_cn, ru_ru localization files have been updated to reflect the content of this update. Other translation updates are pending.

There are likely a few other minor changes and adjustments beyond what I've listed here. Be sure to check the commits on the GitHub repo to see everything I've changed!

### 1.3.2 (12/4/23)
* Added Korean translation, courtesy of 오구리 캡 (oguri_cap_1988). Thank you!
* Removed the underbarrel attachment slot for the Bolt Action Rifle; it was not meant to have this attachment slot in the first place.
* Fixed iron sight position of the Automatic Shotgun.
* Fixed missing .cgmmeta file for the Pump Shotgun. Whoops!

### 1.3.1 'Specialists' (11/28/23)
This release includes both the 1.3.0 'Specialists' update and the 1.3.1 hotfix.

* Added the Rapid SMG, Automatic Sniper Rifle, and Infantry Rifle.
  -  The Infantry Rifle was accepting stock attachments when it's not supposed to in v1.3.0. This has been fixed for this release.
* Overhauled the Hunting Rifle with a new model, sounds, and stats.
* Added the Bolt-Action Rifle, which uses the old model of the Hunting Rifle with reworked stats and some new sounds.
* Added the Pump Shotgun and Chevron Scope, using the Shotgun and Medium Scope models from CGM Audio/Visual Enhancements.
  -  The Chevron Scope has received a new reticle that more closely matches the Long/Ballistic Scopes' reticles.
  -  CGM AVE will be updated with new models for these items to reflect this addition.
* Added Japanese (ja_jp) translation, created by Nyattou and submitted by Omokage-R. Thanks!
* Updated Submachine Gun and Heavy Assault Rifle models.
* Buffed the damage of the Micro SMG to compensate for its high ammo consumption.
* Slightly increased the Double Barreled Shotgun's fire rate to reduce the minimum delay between shots.
* Adjusted some stat modifiers for some of the stocks. The Stabilizing Stock no longer reduces rate of fire.
* Fixed instances of enchantment glint z-fighting with several models. (Issue #7)
* Possibly fixed a compatibility issue with Rubidium, in which the Dot Sight and Ballistic Scope had glowing textures where there shouldn't be any. (Issue #14)


### 1.2.2 (6/17/23)
* Updated enchanted gun fire sounds to reduce the volume of the 'laser gun' sound samples.

### 1.2.1 (6/11/23)
* Added Russian (ru_ru) localization, courtesy of PortalRU on GitHub. Thanks!
* Increased ammo per reload cycle of the Machine Gun to 15.

### 1.2.0 (6/6/23)
* Added the Double Barreled Shotgun, Automatic Shotgun, and Machine Gun.
* Added the high-zoom Ballistic Scope.
* Added Simplified Chinese (zh_cn) translation, courtesy of chemlzh on GitHub. Thanks!
* Updated a few sounds.
* Small adjustments to a few item recipes.

As usual, all new items have usable recipes in the CGM Workbench.

Please note that there is not yet a Simplified Chinese translation key for the NZGE creative mode tab.

### 1.1.3 (5/12/23)
* Altered stat modifiers of the Carbine Stock to make it feel slightly lighter than the Solid Stock.
* Updated the model of the Medium Bullet.
* Added a link to the NZGE CurseForge page on the mod's entry in the mods menu.

### 1.1.2 (4/26/23)
* Fixed muzzle flash position of the Micro SMG.

### 1.1.1 (4/2/23)
* Initial port to 1.19.3.
* As part of code adjustments and changes made for the 1.19.3, a new creative inventory tab for all NZGE items has been added.