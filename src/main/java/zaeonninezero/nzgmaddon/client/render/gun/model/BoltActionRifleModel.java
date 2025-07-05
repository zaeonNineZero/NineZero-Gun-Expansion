package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.GunModel;
import com.mrcrayfish.guns.client.handler.GunRenderingHandler;
import com.mrcrayfish.guns.client.handler.ReloadHandler;

import zaeonninezero.nzgmaddon.client.SpecialModels;
import zaeonninezero.nzgmaddon.util.CGMExpandedHelper;

import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.GunAnimationHelper;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 * Modified by zaeonNineZero for Nine Zero's Gun Expansion
 * Attachment detection logic based off of code from Mo' Guns by Bomb787 and AlanorMiga (MigaMi)
 */
public class BoltActionRifleModel implements IOverrideModel
{
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a model with support for NBT and attachment based part variations
	// and custom animations from CGM Expanded.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
    	// Render the item's BakedModel, which will serve as the core of our custom model.
    	// We select which model variant to use by fetching the value of the CustomModelData tag.
        BakedModel bakedModel = SpecialModels.BOLT_ACTION_RIFLE_BASE.getModel();
        if (getVariant(stack) == 1)
        bakedModel = SpecialModels.BOLT_ACTION_RIFLE_BASE_1.getModel();
        else
        if (getVariant(stack) == 2)
        bakedModel = SpecialModels.BOLT_ACTION_RIFLE_BASE_2.getModel();

        // Render the BakedModel we selected.
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the iron sights element, which is only present when a scope is not attached.
		// We have to grab the gun's scope attachment slot and check whether it is empty or not.
		// If the isEmpty function returns true, then we render the iron sights.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(attachmentStack.isEmpty() && !(getVariant(stack) == 2))
		{
            RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_SIGHTS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}
        else
		// Render the top rail element that appears when a scope is attached.
		{
            RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_RAIL.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}
        
        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        boolean useFallbackAnimation = false;
        
        Vec3 boltTranslations = Vec3.ZERO;
        Vec3 boltRotations = Vec3.ZERO;
        Vec3 boltRotOffset = new Vec3(0, -4.15, 0);
        
        Vec3 bulletTranslations = Vec3.ZERO;
        Vec3 bulletRotations = Vec3.ZERO;
        Vec3 bulletRotOffset = Vec3.ZERO;
        
        Vec3 bullet2Translations = Vec3.ZERO;
        
        Vec3 magTranslations = Vec3.ZERO;
        Vec3 magRotations = Vec3.ZERO;
        Vec3 magRotOffset = Vec3.ZERO;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				
        			boltTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bolt");
        	        boltRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bolt");
        			
        	        bulletTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullet");
        	        bulletRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bullet");
        	        bulletRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "bullet");
					
        			magTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "magazine");
        	        magRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "magazine");
        	        magRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "magazine");
        	        
        	        bullet2Translations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullet2");

        	    	if(!GunAnimationHelper.hasAnimation("fire", stack) && GunAnimationHelper.getSmartAnimationType(stack, player, partialTicks)=="fire")
        	    	useFallbackAnimation = true;
        		}
	    		catch(NoClassDefFoundError ignored) {
	            	disableAnimations = true;
	    		}
        		catch(Exception e) {
                	GunMod.LOGGER.error("Redundant Guns encountered an error trying to apply animations.");
                	e.printStackTrace();
                	disableAnimations = true;
        		}
        }
		
		// Fallback animation logic in the event that CGM Expanded isn't installed, or a custom animation couldn't be found.
        if(disableAnimations || useFallbackAnimation)
        {
	        if(isPlayer && correctContext)
	        {
	            float cooldownDivider = 3.0F;
	            float cooldownOffset1 = 1.0F;
	            float intensity = 1.9F +1;
	            float boltLeadTime = 0.4F;
	            
	        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
	            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
	            cooldown *= cooldownDivider;
	            float cooldown_a = cooldown-cooldownOffset1;
	
	            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
	            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
	            float cooldown_d = Math.min(cooldown_b,cooldown_c);
	
	            float cooldown_e = Math.min(Math.max(cooldown_a*intensity+boltLeadTime,0),1);
	            float cooldown_f = Math.min(Math.max((-cooldown_a*intensity+boltLeadTime)+intensity,0),1);
	            float cooldown_g = Math.min(cooldown_e,cooldown_f);
	            
    			boltTranslations = new Vec3(0, 0, (cooldown_d * 2.5));
    	        boltRotations = new Vec3(0, 0, -(67.5F * Math.min(cooldown_g*2F,1)));
	        }
    	}

        // Bolt-Action Rifle bolt and chamber. This animated part cycles backward then forward after firing.
        // This element consists of two parts.
        
        // Part 1: Rotating bolt handle
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations.
        if(isPlayer)
        {
        	if(boltTranslations!=Vec3.ZERO)
        	poseStack.translate(0, 0, boltTranslations.z*0.0625);
        	if (!disableAnimations)
        	{
            	if(boltRotations!=Vec3.ZERO)
                GunAnimationHelper.rotateAroundOffset(poseStack, boltRotations, boltRotOffset);
        	}
        	else
        	{
	        	poseStack.translate(0, boltRotOffset.y*0.0625, 0);
	        	poseStack.mulPose(Vector3f.ZN.rotationDegrees((float) boltRotations.z));
	        	poseStack.translate(0, -boltRotOffset.y*0.0625, 0);
        	}
        }
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_BOLT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // Part 2: Non-rotating bolt/chamber
		// Push pose.
        poseStack.pushPose();
		// Apply transformations.
        if(isPlayer)
        poseStack.translate(0, 0, boltTranslations.z*0.0625);
		// Render the model.
        RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_CHAMBER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose.
        poseStack.popPose();
        
        
        // Rifle Bullets:
        // Rifle bullet 1 -- used during custom reload animations.
        if(shouldRenderBullet(stack,0))
        {
    		// Push pose.
            poseStack.pushPose();
            // Initial translation to the starting position.
            poseStack.translate(0.0, -4.15*0.0625, 3.1*0.0625);
            // Apply transformations.
            if(!disableAnimations && !useFallbackAnimation && isPlayer && isFirstPerson)
            {
            	if(bulletTranslations!=Vec3.ZERO)
                	poseStack.translate(bulletTranslations.x*0.0625, bulletTranslations.y*0.0625, bulletTranslations.z*0.0625);
                if(bulletRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, bulletRotations, bulletRotOffset);
        	}
    		// Render the model.
            RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_BULLET.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose.
            poseStack.popPose();
        }
        // Rifle bullet 2 -- used during reload and cycling animations.
        if(!disableAnimations && !useFallbackAnimation && shouldRenderBullet(stack,1))
        {
    		// Push pose.
            poseStack.pushPose();
            // Initial translation to the starting position.
            poseStack.translate(0.0, -4.15*0.0625, 3.1*0.0625);
            // Apply transformations.
            if(isPlayer)
            {
            	if(bulletTranslations!=Vec3.ZERO)
                	poseStack.translate(bullet2Translations.x*0.0625, bullet2Translations.y*0.0625, bullet2Translations.z*0.0625);
        	}
    		// Render the model.
            RenderUtil.renderModel(SpecialModels.BOLT_ACTION_RIFLE_BULLET.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose.
            poseStack.popPose();
        }
        
        // Magazine transforms
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Magazine model selection and rendering
        boolean hasMag = false;
        SpecialModels magModel = SpecialModels.BOLT_ACTION_RIFLE_EXTENDED_MAG;
        try {
        	ItemStack magStack = Gun.getAttachment(IAttachment.Type.byTagKey("Magazine"), stack);
            if(!magStack.isEmpty())
            {
            	hasMag = true;
	            if (magStack.getItem().builtInRegistryHolder().key().location().getPath().equals("light_magazine"))
		    		magModel = SpecialModels.BOLT_ACTION_RIFLE_LIGHT_MAG;
            }
		}
		catch(Error ignored) {} catch(Exception ignored) {}
        
        if (hasMag)
        RenderUtil.renderModel(magModel.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt("CustomModelData");
    }
    
    //Code check for whether a bullet should be rendered.
    public boolean shouldRenderBullet(ItemStack gunStack, int bullet)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        if(CGMExpandedHelper.isExpandedInstalled())
        {
        	float progress = (ReloadHandler.get().getReloadTimer()>=0.8 ? GunRenderingHandler.get().getReloadDeltaTime(gunStack) : 0);
        	boolean hasBullet = (Gun.hasInfiniteAmmo(gunStack) || (tag.getInt("AmmoCount") >= bullet));
        	if ((bullet>0 && (hasBullet || GunAnimationHelper.getAnimationValue("reload", gunStack, progress, "bullet2", "forceShowBullet")>=1))
        	|| (bullet==0 && GunAnimationHelper.getAnimationValue("reload", gunStack, progress, "bullet", "forceShowBullet")>=1))
        	return true;
        	else
        	return false;
		}
        
        return (Gun.hasInfiniteAmmo(gunStack) || (tag.getInt("AmmoCount") >= bullet));
    }
}