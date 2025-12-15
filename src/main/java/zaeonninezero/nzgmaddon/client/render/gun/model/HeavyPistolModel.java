package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgmaddon.client.SpecialModels;
import zaeonninezero.nzgmaddon.util.CGMExpandedHelper;

import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.GunAnimationHelper;
import com.mrcrayfish.guns.client.util.RenderUtil;
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
public class HeavyPistolModel implements IOverrideModel
{
	private boolean hasExpanded = CGMExpandedHelper.isExpandedInstalled();
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	// We'll render the non-moving/static parts first, then render the animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.HEAVY_PISTOL_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        boolean isDisplayed = (transformType == ItemTransforms.TransformType.FIXED);
        boolean isGUI = (transformType == ItemTransforms.TransformType.GUI);
        
        
        Vec3 slideTranslations = Vec3.ZERO;
        
        Vec3 hammerRotations = Vec3.ZERO;
        float hammerBaseRotation = 65;
        Vec3 hammerRotOffset = new Vec3(0, 2.52-8, 8.5);
        
        Vec3 magTranslations = Vec3.ZERO;
        Vec3 magRotations = Vec3.ZERO;
        Vec3 magRotOffset = Vec3.ZERO;
        
        if(isPlayer && correctContext && hasExpanded && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				slideTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "slide");
    				
    				hammerRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "hammer");
					
        			magTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "magazine");
        	        magRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "magazine");
        	        magRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "magazine");
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
        
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
		
		// Slide and hammer animation logic.
        // This is particularly complex logic since we have multiple moving parts.
        if(isPlayer && correctContext)
        {
            float cooldownDivider = 1.0F*Math.max((float) gun.getGeneral().getRate()/2.8F,1);
            float cooldownOffset1 = cooldownDivider - 1.0F;
            float intensity = 1.0F +1;
            
        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown *= cooldownDivider;
            float cooldown_a = cooldown-cooldownOffset1;

            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
            float cooldown_d = Math.min(cooldown_b,cooldown_c);
            
            slideTranslations = slideTranslations.add(0, 0, cooldown_d * 1.7);
            hammerRotations = hammerRotations.add(((cooldown_c-1) * hammerBaseRotation), 0, 0);
        }

		// Heavy Pistol slide. This animated part kicks backward on firing, then moves back to its resting position.
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer)
        poseStack.translate(0, 0, slideTranslations.z * 0.0625);
		// Render the transformed model.
        RenderUtil.renderModel(SpecialModels.HEAVY_PISTOL_SLIDE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // Hammer. This part rotates backwards along the x-axis, then locks in place during the animation.
     	// Push pose so we can make do transformations without affecting the models above.
     	poseStack.pushPose();
     	// Now we apply our transformations.
     	if(isPlayer && !isDisplayed && !isGUI)
     	{
     	    if (hasExpanded)
     	    {
     	    	GunAnimationHelper.rotateAroundOffset(poseStack, hammerRotations.add(hammerBaseRotation,0,0), hammerRotOffset);
     	    }
     	    else
     	    {
     	    	poseStack.translate(0, hammerRotOffset.y*0.0625, hammerRotOffset.z*0.0625);
     	    	poseStack.mulPose(Vector3f.XN.rotationDegrees((float) -hammerRotations.x-hammerBaseRotation));
     	    	poseStack.translate(0, -hammerRotOffset.y*0.0625, -hammerRotOffset.z*0.0625);
     	    }
     	}
     	// Our transformations are done - now we can render the model.
     	RenderUtil.renderModel(SpecialModels.HEAVY_PISTOL_HAMMER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
     	// Pop pose to compile everything in the render matrix.
     	poseStack.popPose();
        
        // Magazine for Heavy Pistol
        poseStack.pushPose();
		// Apply transformations to this part.
        if(isPlayer && isFirstPerson && !disableAnimations)
        {
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
		// Render the transformed model.
        RenderUtil.renderModel(SpecialModels.HEAVY_PISTOL_MAGAZINE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
    }
}