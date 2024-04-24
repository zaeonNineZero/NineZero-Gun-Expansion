# Changelog (1.18.2 Releases)


### 1.4.1 'Gunslingers' (4/24/23)
This release includes the v1.4.0 'Gunslingers' major update and the v1.4.1 patch.

* Added model overrides for many of the guns in the addon, adding removable iron sights to many of them.
* The following models received noteworthy changes beyond removable iron sights, with some including animated elements:
  -  The Revolver received a full model overhaul, sporting a more slender design based on the Colt Python. Also, the cylinder now rotates!
  -  The Battle Rifle's design now bears a resemblance to the SCAR-H, helping to differentiate it from CGM's Assault Rifle.
  -  The Machine Gun's bullet belt has received proper animations, 'feeding' bullets from the box into the chamber.
  -  The Infantry Rifle's bolt/breech cycles back with each shot.
  -  The Sniper Rifle and Bolt Action Rifle received changes to the bolt assembly. The Sniper Rifle's bolt now resembles the AWM's bolt.

* Added the following new weapons: Heavy Pistol, Heavy Revolver, Compact SMG, and Bullpup Rifle.
  -  The Heavy Revolver also features an animated cylinder just like the normal-sized Revolver.
* Added two new silencers: the Improved Silencer and the Performance Silencer.
  -  Both silencers have better stats than CGM's Silencer, at the cost of muzzle velocity. Lead your shots accordingly!
  -  Includes working recipes for the Improved Silencer and the Performance Silencer, which were missing in v1.4.0 for MC 1.19.2.

* Updated the fire sounds of several guns for better quality and feel. Enchanted gunfire sounds now use a "flanger" layering effect.
  -  Overhauled the fire sounds for the Revolver, using a new set of sound samples. (From v1.4.1)
* Adjusted the Stabilizing Stock's stat modifiers - reduced the ADS speed penalty, but decreased spread reduction.
* Adjusted the model and ADS parameters for the Ballistic Scope, and very slightly increased camera FOV zoom.

The en_us, zh_cn, ru_ru localization files have been updated to reflect the content of this update. Other translation updates are pending.


### 1.3.2 (12/4/23)
* Added Korean translation, courtesy of 오구리 캡 (oguri_cap_1988). Thank you!
* Removed the underbarrel attachment slot for the Bolt Action Rifle; it was not meant to have this attachment slot in the first place.
* Fixed iron sight position of the Automatic Shotgun.

### 1.3.1 (11/28/23)
* Fixed the Infantry Rifle accepting stock attachments when it's not supposed to. Whoops!

### 1.3.0 'Specialists' (11/27/23)
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
* Increased ammo per reload cycle of the Machine Gun to 15.

### 1.2.0 (6/6/23)
* Added the Double Barreled Shotgun, Automatic Shotgun, and Machine Gun.
* Added the high-zoom Ballistic Scope.
* Added Simplified Chinese (zh_cn) translation, courtesy of chemlzh on GitHub. Thanks!
* Updated a few sounds.
* Small adjustments to a few item recipes.
* Actually remembered to update the version number in the mods.toml file.

As usual, all new items have usable recipes in the CGM Workbench.


### 1.1.3 (5/12/23)
* Altered stat modifiers of the Carbine Stock to make it feel slightly lighter than the Solid Stock.
* Updated the model of the Medium Bullet.
* Added a link to the NZGE CurseForge page on the mod's entry in the mods menu.
* Cleaned up some unnecessary imports in the source code.

### 1.1.2 (4/26/23)
* Fixed muzzle flash position of the Micro SMG.

### 1.1.1 (3/29/23)
* Initial port to 1.18.2.