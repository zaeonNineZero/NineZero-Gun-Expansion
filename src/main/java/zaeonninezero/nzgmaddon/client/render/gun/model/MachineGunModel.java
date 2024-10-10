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
public class MachineGunModel implements IOverrideModel
{
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	
	// Declare our render function that will handle rendering all model components.
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.MACHINE_GUN_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the iron sights element, which is only present when a scope is not attached.
		// We have to grab the gun's scope attachment slot and check whether it is empty or not.
		// If the isEmpty function returns true, then we render the iron sights.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(attachmentStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.MACHINE_GUN_SIGHTS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

        // Special animated segment for compat with the CGM Expanded fork.
		// Heads Up! This section will be pretty big, as the Machine Gun's model has lots of moving parts.
		// Some parts might be light on description/documentation!

        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        
        Vec3 boltTranslations = Vec3.ZERO;
        
        Vec3 magTranslations = Vec3.ZERO;
        Vec3 magRotations = Vec3.ZERO;
        Vec3 magRotOffset = Vec3.ZERO;
        
        Vec3 handleRotations = Vec3.ZERO;
        Vec3 handleRotOffset = Vec3.ZERO;
        
        float bulletMovement = 0F;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				boltTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bolt");
					
        			magTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "magazine");
        	        magRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "magazine");
        	        magRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "magazine");
        	        
        	        //handleRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "handle");
        	        //handleRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "handle");
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
        
        // Fire animation is done the old way, and added onto the existing animations.
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
        if(isPlayer && correctContext)
        {
            float cooldownDivider = 1.0F*Math.max((float) gun.getGeneral().getRate()/2F,1);
            float cooldownOffset1 = cooldownDivider - 1.0F;
            float intensity = 1.0F +1;

            float cooldownOffset2 = cooldownDivider - 1.0F;
            float intensity2 = 0.05F +1;
            
        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown*=cooldownDivider;
            float cooldown_a = cooldown-cooldownOffset1;

            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
            float cooldown_d = Math.min(cooldown_b,cooldown_c);
            
            float cooldown_e = cooldown-cooldownOffset2;
            float cooldown_f = Math.min(Math.max(cooldown_e*intensity2,0),1);
            
            boltTranslations = boltTranslations.add(0, 0, cooldown_d * 2.3);
            bulletMovement = cooldown_f;
            if (disableAnimations)
            handleRotations = new Vec3(0,0,cooldown_f*12);
        }
        

		// Machine Gun Bolt. This animated part kicks backward on firing, then moves back to its resting position.
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer)
        poseStack.translate(0, 0, boltTranslations.z * 0.0625);
		// Render the transformed model.
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BOLT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
		
		
		// Machine Gun Bullets. The bullets move to simulate feeding the belt.
        CompoundTag tag = stack.getOrCreateTag();
        
		// MG Bullet 1.
        if (Gun.hasInfiniteAmmo(stack) || tag.getInt("AmmoCount") >= 5)
        {
        poseStack.pushPose();
		// Transformations
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
            poseStack.translate(0, (bulletMovement * -0.48) * 0.0625, 0);
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET1.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
    	}
		
		// MG Bullet 2.
        if (Gun.hasInfiniteAmmo(stack) || tag.getInt("AmmoCount") >= 4)
        {
        poseStack.pushPose();
		// Transformations
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
            poseStack.translate((bulletMovement * -0.041) * 0.0625, (bulletMovement * -0.475) * 0.0625, 0);
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET2.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
        }
		
		// MG Bullet 3.
        if (Gun.hasInfiniteAmmo(stack) || tag.getInt("AmmoCount") >= 3)
        {
        poseStack.pushPose();
		// Transformations
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
            poseStack.translate((bulletMovement * -0.1) * 0.0625, (bulletMovement * -0.47) * 0.0625, 0);
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET3.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
    	}
		
		// MG Bullet 4.
        if (Gun.hasInfiniteAmmo(stack) || tag.getInt("AmmoCount") >= 2)
        {
        poseStack.pushPose();
		// Transformations
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
            poseStack.translate((bulletMovement * -0.24) * 0.0625, (bulletMovement * -0.47) * 0.0625, 0);
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET4.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
        }
		
		// MG Bullet 5.
        if (Gun.hasInfiniteAmmo(stack) || tag.getInt("AmmoCount") >= 1)
        {
        poseStack.pushPose();
		// Transformations
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
            poseStack.translate((bulletMovement * -0.5) * 0.0625, (bulletMovement * -0.18) * 0.0625, 0);
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET5.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
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
		// Box model selection and rendering
        SpecialModels magModel = SpecialModels.MACHINE_GUN_BOX;
        try {
        	ItemStack magStack = Gun.getAttachment(IAttachment.Type.byTagKey("Magazine"), stack);
            if(!magStack.isEmpty())
            {
	            if (magStack.getItem().builtInRegistryHolder().key().location().getPath().equals("light_magazine"))
		    		magModel = SpecialModels.MACHINE_GUN_LIGHT_BOX;
            }
		}
		catch(Error ignored) {} catch(Exception ignored) {}
        
        RenderUtil.renderModel(magModel.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();

        
		// Machine Gun Handle. This part rotates slightly to simulate the effects of recoil on it.
        poseStack.pushPose();
		// Transformations
        poseStack.translate(0.811 * 0.0625, -3.04 * 0.0625, 0);
        poseStack.mulPose(Vector3f.ZN.rotationDegrees((float) handleRotations.z));
        poseStack.translate(-0.811 * 0.0625, 3.04 * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_HANDLE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
        
        // Phew! We're all done here.
    }
}