# Changelog (1.19.2 Releases -- Developer Edition)


### 1.4.3 (5/11/23)
* Changed the fire rate of the Pump Shotgun and Hunting Shotgun to match up the animations to the new fire sounds. This change was supposed to have been part of v1.4.2 but was overlooked during development by mistake. (Whoops!)
* Implemented another change to the Infantry Rifle model: the top rail now only appears when a scope is attached. Credit to GrechHerald for the idea!

### 1.4.2 Main Release (5/9/23)
* Added a model variant for the Bolt Action Rifle based on the Kar 98k and Springfield M1903.
* Added survival-compatible recipes for the newly added model variants. They cost the same as their standard counterparts.

### 1.4.2 Pre-Release 1 (5/6/23)
* Added some basic animations to the Pump Shotgun, Hunting Shotgun, Bolt Action Rifle, and Sniper Rifle.
  -  The shotguns' slide grips move back and forth after firing.
  -  The rifles' bolts rotate and move back and forth after firing. The models also received some touch-ups with this patch.
  -  All received new chambering sounds, with the shotguns sharing chambering sounds and the rifles getting separate chambering sounds.
  -  The fire sounds for these guns have been edited to include a sample of the chambering sound to match the animations.
* Added experimental "skin variants" for the Pump Shotgun and Heavy Assault Rifle.
  -  The Pump Shotgun's body changes to gray and is not affected by color dyeing.
  -  Conversely, the Heavy AR's body changes to white, which can be changed with dyes.
  -  These skins can be used by setting the "CustomModelData" NBT tag to 1. The use of "CustomModelData" is arbitrary and may change in the future.
* Overhauled the fire sounds for the Heavy AR, using a new set of sound samples. This is an experimental change that will be subject to user feedback.
* Model touch-ups to the Heavy AR to reduce Z-fighting issues.
* As part of the model touch-ups, fixed an issue where a small part of the Bolt Action Rifle's model was inadvertently being tinted by dyes.
* Fixed the Bullpup Rifle having an invalid reload sound. Whoops!

### 1.4.1 (4/24/23)
* Added working recipes for the Improved Silencer and the Performance Silencer, which I forgot for v1.4.0. Whoops!
* Overhauled the fire sounds for the Revolver, using a new set of sound samples.
* Model adjustments to the Sniper Rifle and Bolt Action Rifle, reducing the thickness of the bolt and chamber assemblies.
  -  The Sniper Rifle's bolt also got redesigned, now resembling the AWM's bolt.
* Slightly buffed the Stabilizing Stock's spread reduction.
* Updated ru_ru localization with more accurate translations. Courtesy of mpustovoi.

### 1.4.0 'Gunslingers' (4/15/23)
This is a relatively big update for NZGE, with several new guns and a multitude of changes and tweaks.

* Added model overrides for many of the guns in the addon, adding removable iron sights to many of them.
* The following models received noteworthy changes beyond removable iron sights, with some including animated elements:
  -  The Revolver received a full model overhaul, sporting a more slender design based on the Colt Python. Also, the cylinder now rotates!
  -  The Battle Rifle's design now bears a resemblance to the SCAR-H, helping to differentiate it from CGM's Assault Rifle.
  -  The Machine Gun's bullet belt has received proper animations, 'feeding' bullets from the box into the chamber.
  -  The Infantry Rifle's bolt/breech cycles back with each shot.

* Added the following new weapons: Heavy Pistol, Heavy Revolver, Compact SMG, and Bullpup Rifle.
  -  The Heavy Revolver also features an animated cylinder just like the normal-sized Revolver.
* Added two new silencers: the Improved Silencer and the Performance Silencer.
  -  Both silencers have better stats than CGM's Silencer, at the cost of muzzle velocity. Lead your shots accordingly!

* Updated the fire sounds of several guns for better quality and feel. Enchanted gunfire sounds now use a "flanger" layering effect.
* Adjusted the Stabilizing Stock's stat modifiers - reduced the ADS speed penalty, but decreased spread reduction.
* Adjusted the model and ADS parameters for the Ballistic Scope, and very slightly increased camera FOV zoom.

The en_us, zh_cn, ru_ru localization files have been updated to reflect the content of this update. Other translation updates are pending.

There are likely a few other minor changes and adjustments beyond what I've listed here. Be sure to check the commits on the GitHub repo to see everything I've changed!


### 1.3.2 (12/4/23)
* Added Korean translation, courtesy of 오구리 캡 (oguri_cap_1988). Thank you!
* Removed the underbarrel attachment slot for the Bolt Action Rifle; it was not meant to have this attachment slot in the first place.
* Fixed iron sight position of the Automatic Shotgun.

### 1.3.1 (11/28/23)
* Fixed the Infantry Rifle accepting stock attachments when it's not supposed to. Whoops!

### 1.3.0 'Specialists' (11/26/23)
* Added the Rapid SMG, Automatic Sniper Rifle, and Infantry Rifle.
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
* Corrected an incorrect item key in zh_cn.json - 'item.nzgmaddon.uzi' was uncorrectly labeled as 'item.nzgmaddon.micro_smg'.
* Increased ammo per reload cycle of the Machine Gun to 15.

### 1.2.0 (6/6/23)
* Added the Double Barreled Shotgun, Automatic Shotgun, and Machine Gun.
* Added the high-zoom Ballistic Scope.
* Added Simplified Chinese (zh_cn) translation, courtesy of chemlzh on GitHub. Thanks!
* Updated a few sounds.
* Small adjustments to a few item recipes.

As usual, all new items have usable recipes in the CGM Workbench.


### 1.1.3 (5/12/23)
* Altered stat modifiers of the Carbine Stock to make it feel slightly lighter than the Solid Stock.
* Updated the model of the Medium Bullet.
* Added a link to the NZGE CurseForge page on the mod's entry in the mods menu.
* Cleaned up some unnecessary imports in the source code.

### 1.1.2 (4/26/23)
* Fixed muzzle flash position of the Micro SMG.

### 1.1.1 (3/29/23)
* Renamed the Uzi to the Micro SMG to better fit the mod's naming conventions. The ingame ID is unchanged to prevent the loss of existing Uzi items.
* Changed all sound names and references related to 'Uzi' to 'Micro SMG.'
* Updated Micro SMG model.
* Changed SMG model - it now has a thinner magazine.
* Corrected Marksman Stock recipe to output the correct item.

### 1.1.0 'Accessorized' (3/27/23)
* Added Hunting Shotgun.
* Added Flash Hider, Muzzle Brake, Extended Barrel, Dot Sight, Carbine Stock, Short Stock, Marksman Stock, and Horizontal Grip attachments.
* Crafting recipes for all new items, allowing them to be acquired and used in survival mode.
* Modified the stats of pretty much every gun to make them more competitive with the CGM guns - generally increased damage, reduced recoil, and decreased spread where needed. Check the GitHub  commits for more details on changed stats. (bc5419b)
* Updated stat modifiers for several attachments.
* Changed the fire sounds of the Uzi and SMG.
* Tweaked several gun models.

NineZero's Gun Expansion now requires CGM 1.3.4; if you are running an older CGM version, please update to 1.3.4.


### 1.0.1 (2/1/23)
* Fixed invalid crafting recipes for stocks.

### 1.0.0 (1/29/23)
* Initial release.