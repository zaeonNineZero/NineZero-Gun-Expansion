package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
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
public class MicroSMGModel implements IOverrideModel
{
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a multi-part model with support for interchangeable parts and animations.
	// Static parts are rendered first, followed by any moving/animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.MICRO_SMG_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the variable iron sight elements.
        // When a scope is equipped, the sights are lowered to avoid clipping in to the scope.
		// We grab the gun's scope attachment slot, check whether it is empty or not, and render a model accordingly.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(attachmentStack.isEmpty())
            RenderUtil.renderModel(SpecialModels.MICRO_SMG_SIGHTS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
        else
            RenderUtil.renderModel(SpecialModels.MICRO_SMG_SIGHTS_LOWERED.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);

		// Render the stock adapter/mount element, which is only present when a stock attachment is equipped.
		// Same as above once again, this time with the stock attachment slot.
        ItemStack attachmentStockStack = Gun.getAttachment(IAttachment.Type.STOCK, stack);
        if(!attachmentStockStack.isEmpty())
			RenderUtil.renderModel(SpecialModels.MICRO_SMG_STOCK_MOUNT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);

		// Render the bottom rail element, which is only present when an underbarrel attachment is equipped.
		// Note that the Micro SMG does not have this slot enabled by default, but it has metadata for rendering these attachments.
        ItemStack attachmentGripStack = Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack);
        if(!attachmentGripStack.isEmpty() || getVariant(stack, "BottomRail") == 1)
			RenderUtil.renderModel(SpecialModels.MICRO_SMG_BOTTOM_RAIL.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);

        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        
        Vec3 boltTranslations = Vec3.ZERO;
        
        Vec3 magTranslations = Vec3.ZERO;
        Vec3 magRotations = Vec3.ZERO;
        Vec3 magRotOffset = Vec3.ZERO;
        
        Vec3 mag2Translations = Vec3.ZERO;
        Vec3 mag2Rotations = Vec3.ZERO;
        Vec3 mag2RotOffset = Vec3.ZERO;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				boltTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bolt_handle");
					
        			magTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "magazine");
        	        magRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "magazine");
        	        magRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "magazine");
					
        			mag2Translations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "magazine");
        	        mag2Rotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "magazine");
        	        mag2RotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "magazine");
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
        
        // Fire animation is done the old way, and added onto the existing animation.
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
        if(isPlayer && correctContext)
        {
        	float cooldownDivider = 1.0F*Math.max((float) gun.getGeneral().getRate()/2F,1);
            float cooldownOffset1 = cooldownDivider - 1.0F;
            float intensity = 1.0F +1;
            
        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown *= cooldownDivider;
            float cooldown_a = cooldown-cooldownOffset1;

            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
            float cooldown_d = Math.min(cooldown_b,cooldown_c);
            
            boltTranslations = boltTranslations.add(0, 0, cooldown_d * 1.6);
        }
        
		// Micro SMG Bolt/Charging handle
        poseStack.pushPose();
        // Apply transformations to this part.
        if(isPlayer && !disableAnimations)
        {
        	if(boltTranslations!=Vec3.ZERO)
        	poseStack.translate(0, 0, boltTranslations.z*0.0625);
    	}
        // Render the transformed model.
        RenderUtil.renderModel(SpecialModels.MICRO_SMG_BOLT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();

		// Magazine model selection (doing this first because we are going to render up to two magazine)
        SpecialModels magModel = SpecialModels.MICRO_SMG_MAGAZINE;
        try {
        	ItemStack magStack = Gun.getAttachment(IAttachment.Type.byTagKey("Magazine"), stack);
            if(!magStack.isEmpty())
            {
	            if (magStack.getItem().builtInRegistryHolder().key().location().getPath().equals("light_magazine"))
		    		magModel = SpecialModels.MICRO_SMG_LIGHT_MAG;
	            else
	            if (magStack.getItem().builtInRegistryHolder().key().location().getPath().equals("extended_magazine"))
			    	magModel = SpecialModels.MICRO_SMG_EXTENDED_MAG;
            }
		}
		catch(Error ignored) {} catch(Exception ignored) {}
        
        // Magazine 1 - transforms and rendering
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
        RenderUtil.renderModel(magModel.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // Magazine 2 - transforms and rendering
        if(isPlayer && isFirstPerson && !disableAnimations && shouldRender2ndMagazine(stack))
        {
        poseStack.pushPose();
		// Apply transformations to this part.
        {
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
        RenderUtil.renderModel(magModel.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
    	}
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack, String tag_name)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt(tag_name);
    }
    
    //Code check for rendering the second magazine.
    public boolean shouldRender2ndMagazine(ItemStack gunStack)
    {
        if(CGMExpandedHelper.isExpandedInstalled())
        {
        	float progress = (ReloadHandler.get().getReloadTimer()>=0.8 ? GunRenderingHandler.get().getReloadDeltaTime(gunStack) : 0);
        	if (GunAnimationHelper.getAnimationValue("reload", gunStack, progress, "magazine2", "renderPart")>=1)
        	return true;
        	else
        	return false;
		}
        return false;
    }
}