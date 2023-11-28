# Changelog (1.19.2 Releases)


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