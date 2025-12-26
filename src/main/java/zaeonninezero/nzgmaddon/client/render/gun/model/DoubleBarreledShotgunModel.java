package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgmaddon.client.SpecialModels;
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
public class DoubleBarreledShotgunModel implements IOverrideModel
{
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a model with support for NBT and attachment based part variations
	// and custom animations from CGM Expanded.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.DOUBLE_BARRELED_SHOTGUN_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        boolean useFallbackAnimation = false;

        Vec3 breakRotations = Vec3.ZERO;
        Vec3 breakRotOffset = Vec3.ZERO;
        
        Vec3 bulletTranslations = Vec3.ZERO;
        Vec3 bulletRotations = Vec3.ZERO;
        Vec3 bulletRotOffset = Vec3.ZERO;
        
        Vec3 bullet2Translations = Vec3.ZERO;
        Vec3 bullet2Rotations = Vec3.ZERO;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;

    				breakRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "break");
    				breakRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "break");
        			
        	        bulletTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullet");
        	        bulletRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bullet");
        	        bulletRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "bullet");
        			
        	        bullet2Translations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullet2");
        	        bullet2Rotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bullet2");

        	    	if(!GunAnimationHelper.hasAnimation("reload", stack) && GunAnimationHelper.getSmartAnimationType(stack, player, partialTicks)=="reload")
        	    	useFallbackAnimation = true;
        		}
	    		catch(NoClassDefFoundError ignored) {
	            	disableAnimations = true;
	    		}
        		catch(Exception e) {
                	GunMod.LOGGER.error("NZGE encountered an error trying to apply animations.");
                	e.printStackTrace();
                	disableAnimations = true;
        		}
        }
        
        // Double Barreled Shotgun break-action. This animated part can be rotated open during custom reload animations.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations.
        if(isPlayer && isFirstPerson)
        {
        	if(breakRotations!=Vec3.ZERO)
                GunAnimationHelper.rotateAroundOffset(poseStack, breakRotations, breakRotOffset);
    	}
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.DOUBLE_BARRELED_SHOTGUN_BREAK.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // SG Shells, which is only used during custom reload animations.
        if(!disableAnimations && !useFallbackAnimation && isPlayer && isFirstPerson)
        {
        	// SG Shell 1:
        	// Push pose so we can make do transformations without affecting the models above.
            poseStack.pushPose();
            // Apply the transformations
            {
            	if(bulletTranslations!=Vec3.ZERO)
                	poseStack.translate(bulletTranslations.x*0.0625, bulletTranslations.y*0.0625, bulletTranslations.z*0.0625);
            	// We're also going to apply break action rotations to the shells.
            	if(breakRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, breakRotations, breakRotOffset);
                // Now we can translation to the starting position.
                poseStack.translate(-0.6*0.0625, -4.265*0.0625, 3.1*0.0625);
                
                if(bulletRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, bulletRotations, bulletRotOffset);
        	}
    		// Render the model.
            RenderUtil.renderModel(SpecialModels.SG_SHELL_LOADED.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose to compile everything in the render matrix.
            poseStack.popPose();
        
            // SG Shell 2:
    		// Push pose so we can make do transformations without affecting the models above.
            poseStack.pushPose();
            // Apply the transformations
            {
            	if(bullet2Translations!=Vec3.ZERO)
                	poseStack.translate(bullet2Translations.x*0.0625, bullet2Translations.y*0.0625, bullet2Translations.z*0.0625);
            	// We're also going to apply break action rotations to the shells.
            	if(breakRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, breakRotations, breakRotOffset);
                // Now we can translation to the starting position.
                poseStack.translate(0.6*0.0625, -4.265*0.0625, 3.1*0.0625);
                
                if(bullet2Rotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, bullet2Rotations, bulletRotOffset);
        	}
    		// Render the model.
            RenderUtil.renderModel(SpecialModels.SG_SHELL_LOADED.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose to compile everything in the render matrix.
            poseStack.popPose();
        }
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt("CustomModelData");
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack, String tag_name)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt(tag_name);
    }
}