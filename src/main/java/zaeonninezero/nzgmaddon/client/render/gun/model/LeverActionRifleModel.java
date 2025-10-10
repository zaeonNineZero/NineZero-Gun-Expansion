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
public class LeverActionRifleModel implements IOverrideModel
{
	private boolean hasExpanded = CGMExpandedHelper.isExpandedInstalled();
	private boolean disableAnimations = !hasExpanded;
	
    @Override
	// This class renders a model with support for NBT and attachment based part variations,
	// and custom animations from CGM Expanded.
	
	// We start by declaring the render function, which handles rendering each part of the gun.
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
    	// Render the item's BakedModel, which will serve as the core of our custom model.
    	// We select which model variant to use by fetching an NBT tag's value.
        BakedModel bakedModel = SpecialModels.LEVER_ACTION_RIFLE_BASE.getModel();
        if (getVariant(stack, "BaseVariant") == 1)
        bakedModel = SpecialModels.LEVER_ACTION_RIFLE_BASE_1.getModel();

        // Render the BakedModel we selected.
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the iron sights element. One of two models can be used depending on NBT
		{
        	BakedModel sightBakedModel = SpecialModels.LEVER_ACTION_RIFLE_SIGHTS.getModel();
            if (getVariant(stack, "SightVariant") == 1)
            sightBakedModel = SpecialModels.LEVER_ACTION_RIFLE_SIGHTS_1.getModel();
            RenderUtil.renderModel(sightBakedModel, transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}
		// Render the top rail element, which is only present when a scope is attached.
		// We have to grab the gun's scope attachment slot and check whether it is empty or not.
		// If the isEmpty function returns false, then we render the rail.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(!attachmentStack.isEmpty() || getVariant(stack, "ForceShowRail") == 1)
		{
        	BakedModel railBakedModel = SpecialModels.LEVER_ACTION_RIFLE_RAIL.getModel();
            if (getVariant(stack, "BaseVariant") == 1)
            railBakedModel = SpecialModels.LEVER_ACTION_RIFLE_RAIL_1.getModel();
            RenderUtil.renderModel(railBakedModel, transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}
        
        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (isFirstPerson || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        boolean useFallbackAnimation = false;
        
        Vec3 leverRotations = Vec3.ZERO;
        Vec3 leverRotOffset = new Vec3(0, -5.8, 5.03);
        
        Vec3 hammerRotations = Vec3.ZERO;
        Vec3 hammerRotOffset = new Vec3(0, -5.5, 7.9);
        
        Vec3 boltTranslations = Vec3.ZERO;
        
        Vec3 bulletTranslations = Vec3.ZERO;
        Vec3 bulletRotations = Vec3.ZERO;
        Vec3 bulletRotOffset = Vec3.ZERO;
        
        if(isPlayer && correctContext && hasExpanded && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				leverRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "lever");
    				hammerRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "hammer");
        			boltTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bolt");
        			
        	        bulletTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullet");
        	        bulletRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bullet");
        	        bulletRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "bullet");

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
        
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
		
		// Fallback animation logic in the event that CGM Expanded isn't installed, or a custom animation couldn't be found.
        // This is particularly complex logic since we have multiple moving parts.
        if(disableAnimations || useFallbackAnimation)
        {
	        if(isPlayer && correctContext)
	        {
	            float cooldownDivider = 2.0F*Math.max((float) gun.getGeneral().getRate()/11F,1);;
	            float cooldownOffset1 = cooldownDivider - 1.5F;
	            float intensity = 1.3F +1;
	            
	        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
	            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
	            cooldown *= cooldownDivider;
	            float cooldown_a = cooldown-cooldownOffset1;
	
	            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
	            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
	            float cooldown_d = Math.min(cooldown_b,cooldown_c);
	            
	            leverRotations = new Vec3(cooldown_d * 50, 0, 0);
	            hammerRotations = new Vec3(((cooldown_c-1) * 45), 0, 0);
    			boltTranslations = new Vec3(0, 0, cooldown_d * 2.0);
	        }
    	}

        // Lever-Action Rifle lever. This part rotates along the x-axis during the animation.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations.
        if(isPlayer)
        {
        	if (hasExpanded)
        	{
            	if(leverRotations!=Vec3.ZERO)
                GunAnimationHelper.rotateAroundOffset(poseStack, leverRotations, leverRotOffset);
        	}
        	else
            if(leverRotations!=Vec3.ZERO)
        	{
	        	poseStack.translate(0, leverRotOffset.y*0.0625, leverRotOffset.z*0.0625);
	        	poseStack.mulPose(Vector3f.XN.rotationDegrees((float) -leverRotations.x));
	        	poseStack.translate(0, -leverRotOffset.y*0.0625, -leverRotOffset.z*0.0625);
        	}
        }
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.LEVER_ACTION_RIFLE_LEVER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();

	    // Lever-Action Rifle hammer. This part rotates backwards along the x-axis and locks in during the animation.
		// Push pose so we can make do transformations without affecting the models above.
	    poseStack.pushPose();
		// Now we apply our transformations.
	    if(isPlayer)
	    {
	    	if (hasExpanded)
	    	{
	            GunAnimationHelper.rotateAroundOffset(poseStack, hammerRotations.add(45,0,0), hammerRotOffset);
	    	}
	    	else
	    	{
	        	poseStack.translate(0, hammerRotOffset.y*0.0625, hammerRotOffset.z*0.0625);
	        	poseStack.mulPose(Vector3f.XN.rotationDegrees((float) -hammerRotations.x-45));
	        	poseStack.translate(0, -hammerRotOffset.y*0.0625, -hammerRotOffset.z*0.0625);
	    	}
	    }
		// Our transformations are done - now we can render the model.
	    RenderUtil.renderModel(SpecialModels.LEVER_ACTION_RIFLE_HAMMER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
	    poseStack.popPose();
	    
	    // Lever-Action Rifle bolt. This part moves back and forth during the animation.
	 	// Push pose so we can make do transformations without affecting the models above.
	    poseStack.pushPose();
		// Now we apply our transformations.
	    if(isPlayer)
	    {
	    	if(boltTranslations!=Vec3.ZERO)
	    	poseStack.translate(0, 0, boltTranslations.z*0.0625);
	    }
		// Our transformations are done - now we can render the model.
	    RenderUtil.renderModel(SpecialModels.LEVER_ACTION_RIFLE_BOLT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
	    poseStack.popPose();
        
        
        // Basic Bullet -- used during custom reload animations.
        if(shouldRenderBullet(stack) && !disableAnimations && isPlayer && isFirstPerson)
        {
    		// Push pose.
            poseStack.pushPose();
            // Initial translation to the starting position.
            poseStack.translate(0.0, -4.15*0.0625, 3.1*0.0625);
            // Apply transformations.
            if(bulletTranslations!=Vec3.ZERO)
            	poseStack.translate(bulletTranslations.x*0.0625, bulletTranslations.y*0.0625, bulletTranslations.z*0.0625);
            if(bulletRotations!=Vec3.ZERO)
            	GunAnimationHelper.rotateAroundOffset(poseStack, bulletRotations, bulletRotOffset);
            // Select the bullet model to render
            BakedModel bulletModel = SpecialModels.MEDIUM_BULLET_LOADED.getModel();
            String itemString = gun.getProjectile().getItem().toString();
	        if (itemString.equals("cgm:basic_bullet"))
	        bulletModel = SpecialModels.BASIC_BULLET_LOADED.getModel();
	        else
	        if (itemString.equals("cgm:advanced_bullet"))
	        bulletModel = SpecialModels.ADVANCED_BULLET_LOADED.getModel();
            
    		// Render the model.
            RenderUtil.renderModel(bulletModel, transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose.
            poseStack.popPose();
        }
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt("CustomModelData");
    }
    public static int getVariant(ItemStack gunStack, String tag_name)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt(tag_name);
    }
    
    //Code check for whether a bullet should be rendered.
    public boolean shouldRenderBullet(ItemStack gunStack)
    {
        if(hasExpanded)
        {
        	float progress = (ReloadHandler.get().getReloadTimer()>=0.95 ? GunRenderingHandler.get().getReloadCycleProgress(gunStack) : 0);
        	if (GunAnimationHelper.getAnimationValue("reload", gunStack, progress, "bullet", "renderBullet")>=1)
        	return true;
		}
        
        return false;
    }
}