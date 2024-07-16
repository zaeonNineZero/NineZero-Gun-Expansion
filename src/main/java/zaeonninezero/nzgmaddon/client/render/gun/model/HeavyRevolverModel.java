package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgmaddon.client.SpecialModels;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.GunAnimationHelper;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
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
public class HeavyRevolverModel implements IOverrideModel
{
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	// We'll render the non-moving/static parts first, then render the animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.HEAVY_REVOLVER_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

     // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);

        Vec3 cylinderRotations = Vec3.ZERO;
        Vec3 cylinderRotOffset = new Vec3(0, -4.27, 0);
        
        Vec3 swingRotations = Vec3.ZERO;
        Vec3 swingRotOffset = Vec3.ZERO;
        
        Vec3 chamberRotations = Vec3.ZERO;
        
        Vec3 ammoTranslations = Vec3.ZERO;
        Vec3 ammoRotations = Vec3.ZERO;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
        			cylinderRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "cylinder");
        			
        			swingRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "swing_out");
        			swingRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "swing_out");
        			
        			chamberRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "chambers");
        			
    				ammoTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bullets");
    				ammoRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bullets");
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
            float cooldownDivider = 1.0F*Math.max((float) gun.getGeneral().getRate()/5F,1);
            float cooldownOffset1 = cooldownDivider - 1.0F;
            float intensity = 0.05F +1;
            
        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown *= cooldownDivider;
            float cooldown_a = cooldown-cooldownOffset1;

            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
            float cooldown_c = cooldown_b * cooldown_b;
            
            cylinderRotations = cylinderRotations.add(0, 0, cooldown_c * 60);
        }

        // Swing out component.
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer && !disableAnimations)
        {
            if(swingRotations!=Vec3.ZERO)
            GunAnimationHelper.rotateAroundOffset(poseStack, swingRotations, swingRotOffset);
        }
		// Render the transformed model.
        RenderUtil.renderModel(SpecialModels.HEAVY_REVOLVER_SWING.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();

        // Rotating cylinder component.
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer)
        {
        	if (!disableAnimations)
        	{
            	if(swingRotations!=Vec3.ZERO)
                GunAnimationHelper.rotateAroundOffset(poseStack, swingRotations, swingRotOffset);
        	}
        	if(cylinderRotations!=Vec3.ZERO)
        	{
	        	poseStack.translate(0, cylinderRotOffset.y*0.0625, 0);
	        	poseStack.mulPose(Vector3f.ZN.rotationDegrees((float) cylinderRotations.z));
	        	poseStack.translate(0, -cylinderRotOffset.y*0.0625, 0);
        	}
        }
		// Render the transformed model.
        RenderUtil.renderModel(SpecialModels.HEAVY_REVOLVER_CYLINDER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        for (int i=0; i<3; i++)
        {
            // Chamber components.
        	poseStack.pushPose();
    		// Apply transformations to this part.
            if(isPlayer)
            {

            	if (!disableAnimations)
            	{
                	if(swingRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, swingRotations, swingRotOffset);
            	}
            	
            	poseStack.translate(-0.045*0.0625, (cylinderRotOffset.y+0.045)*0.0625, 0);
    	        poseStack.mulPose(Vector3f.ZN.rotationDegrees((float) (cylinderRotations.z + chamberRotations.z) + (60*i)));
    	        poseStack.translate(0, -cylinderRotOffset.y*0.0625, 0);
            }
    		// Render the transformed model.
            RenderUtil.renderModel(SpecialModels.HEAVY_REVOLVER_CHAMBERS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose to compile everything in the render matrix.
            poseStack.popPose();
            
            // Bullet components.
        	poseStack.pushPose();
    		// Apply transformations to this part.
            if(isPlayer)
            {
            	if(ammoTranslations!=Vec3.ZERO && isFirstPerson)
                poseStack.translate(0, 0, ammoTranslations.z*0.0625);
    	        
            	if (!disableAnimations)
            	{
                	if(swingRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, swingRotations, swingRotOffset);
            	}
            	
            	poseStack.translate(-0.045*0.0625, (cylinderRotOffset.y+0.045)*0.0625, 0);
    	        poseStack.mulPose(Vector3f.ZN.rotationDegrees((float) (cylinderRotations.z + chamberRotations.z) + (60*i)));
    	        poseStack.translate(0, -cylinderRotOffset.y*0.0625, 0);
            }
    		// Render the transformed model.
            RenderUtil.renderModel(SpecialModels.HEAVY_REVOLVER_BULLETS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
    		// Pop pose to compile everything in the render matrix.
            poseStack.popPose();
        }
		
		// Get the item's cooldown from the user entity, then do some math to make a suitable animation.
		// In this case, we multiply the cooldown value by itself to create a smooth animation.
        /*boolean isPlayer = (entity != null && entity.equals(Minecraft.getInstance().player));
        boolean correctContext = (transformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        float cooldown = 0F;
        if(isPlayer && correctContext)
        {
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = Math.max((cooldown*2)-1,0);
            cooldown*= cooldown;
        }

		// Rotating cylinder. Same as the one for the revolver, just with a different model.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations.
        if(isPlayer)
        {
			// First we set the rotation pivot point by translating the model.
        	poseStack.translate(0, -4.27 * 0.0625, 0);
        	// Rotate the model based on the cooldown variable. Here we go in the opposite direction of the standard Revolver's cylinder.
        	poseStack.mulPose(Vector3f.ZN.rotationDegrees(60F * cooldown));
			// Finally we translate the model back to its intended position.
        	poseStack.translate(0, 4.27 * 0.0625, 0);
    	}
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.HEAVY_REVOLVER_CYLINDER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();*/
    }
}