# Changelog (1.16.5 Releases)


### 1.3.2 (12/5/23)
This release includes the 1.3.0 'Specialists' update, and the 1.3.1 and 1.3.2 hotfixes.

* Added the Rapid SMG, Automatic Sniper Rifle, and Infantry Rifle.
  -  The Infantry Rifle was accepting stock attachments when it's not supposed to in v1.3.0. This has been fixed for this release.
* Overhauled the Hunting Rifle with a new model, sounds, and stats.
* Added the Bolt-Action Rifle, which uses the old model of the Hunting Rifle with reworked stats and some new sounds.
* Added the Pump Shotgun and Chevron Scope, using the Shotgun and Medium Scope models from CGM Audio/Visual Enhancements.
  -  The Chevron Scope has received a new reticle that more closely matches the Long/Ballistic Scopes' reticles.
  -  CGM AVE will be updated with new models for these items to reflect this addition.
* Added the following translations:
  -  Japanese (ja_jp) translation, created by Nyattou and submitted by Omokage-R.
  -  Korean (ko_kr) translation, courtesy of 오구리 캡 (oguri_cap_1988).
* Updated Submachine Gun and Heavy Assault Rifle models.
* Buffed the damage of the Micro SMG to compensate for its high ammo consumption.
* Slightly increased the Double Barreled Shotgun's fire rate to reduce the minimum delay between shots.
* Adjusted some stat modifiers for some of the stocks. The Stabilizing Stock no longer reduces rate of fire.
* Fixed iron sight position of several guns that had misaligned sights.
* Fixed the Ballistic Scope frequently clipping into the camera when shooting high-recoil guns. (Looking at you, Sniper Rifle!)
* Fixed instances of enchantment glint z-fighting with several models. (Issue #7)
* Possibly fixed a compatibility issue with Rubidium, in which the Dot Sight and Ballistic Scope had glowing textures where there shouldn't be any. (Issue #14)
* A myriad of miscellaneous small fixes to improve mod quality and user experience.


### 1.2.2 (6/17/23)
* Updated enchanted gun fire sounds to reduce the volume of the 'laser gun' sound samples.
* Fixed another set of muzzle flash and attachment positioning issues with the Revolver, Micro SMG, and Heavy AR. This fix will bring a new era of world peace. (...ehhh one can dream)

### 1.2.1 (6/11/23)
* Added Russian (ru_ru) localization, courtesy of PortalRU on GitHub. Thanks!
* Increased ammo per reload cycle of the Machine Gun to 15.
* Implemented mod logo and banner images to Catalogue mod menu - parity change to match 1.18+.
* Behind the scenes cleanup to the mods.toml and pack.mcmeta files.

### 1.2.0 (6/9/23)
Given the current situation on CurseForge, I have conducted a thorough scan for the Fractureriser malware on my system, and have verified that both my computer and my recent NZGE file uploads are all clear. However, as a necessary precaution, I HIGHLY recommend scanning all recently downloaded mod files (INCLUDING NZGE) with CurseForge's detection tools to prevent the malware from spreading.

* Added the Double Barreled Shotgun, Automatic Shotgun, and Machine Gun.
* Added the high-zoom Ballistic Scope.
* Added Simplified Chinese (zh_cn) translation, courtesy of chemlzh on GitHub. Thanks!
* Updated a few sounds.
* Small adjustments to a few item recipes.
* Actually remembered to update the version number in the mods.toml file. Hey, didn't this happen with the 1.18 release too?

As usual, all new items have usable recipes in the CGM Workbench.

Due to code differences between 1.16.5 and 1.18.2+, the behavior of the Ballistic Scope may be slightly off compared to how it is on other versions. Additionally, for 1.16.5 release, Ballistic Scope uses dual-rendering like the Medium and Long Scopes. This may cause issues when used with shaders, and I will not be able to provide support beyond requesting that you disable shaders.

### 1.1.3 (5/12/23)
* Ported the Dot Sight from 1.18.2+ versions.
* Altered stat modifiers of the Carbine Stock to make it feel slightly lighter than the Solid Stock.
* Updated the model of the Medium Bullet.
* Added a link to the NZGE CurseForge page on the mod's entry in the mods menu.

No items or features are absent as of this release.

### 1.1.2 (4/26/23)
* Fixed muzzle flash position of the Micro SMG.

Due to being built around an outdated CGM version, the following items/features are currently absent:
* Dot Sight

Please note that 1.16.5 releases of NZGE will be infrequent and later than releases for other versions, due to required code changes for these backports.

### 1.1.1 (4/25/23)
* Initial port to 1.16.5.

1.16.5 releases are built for CGM 1.2.6, the last CGM version released for 1.16.5; this version of CGM lacks the new features and changes added in CGM 1.3.0. As such, the 1.16.5 releases of NZGE may behave differently than builds for 1.18.2+, and may be missing some features at times.

Due to being built around an outdated CGM version, the following items/features are currently absent:
* Dot Sight

The following changes have also been made, but will not lead to significant gameplay impact:
* Capped the recoil kick values of all addon guns to 1.0, to ensure no clipping occurs with the long scope during gun recoil.

Please note that 1.16.5 releases of NZGE will be infrequent and later than releases for other versions, due to required code changes for these backports.