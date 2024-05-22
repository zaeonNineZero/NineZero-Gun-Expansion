# Changelog (1.19.4 Releases)


### 1.4.4 'Gunslingers' (5/22/23)
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


### 1.3.2 (12/5/23)
* Initial port to 1.19.4, based on v1.3.2.