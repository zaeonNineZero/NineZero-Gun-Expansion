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
public class PumpShotgunModel implements IOverrideModel
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
        BakedModel bakedModel = SpecialModels.PUMP_SHOTGUN_BASE.getModel();
        if (getVariant(stack) == 1 || getVariant(stack, "BaseVariant") == 1)
        bakedModel = SpecialModels.PUMP_SHOTGUN_BASE_1.getModel();

        // Render the BakedModel we selected.
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the iron sights element.
		// This time we have two criteria needed to render the sights element:
		// 1. A scope must NOT be attached.
		// 2. A stock must be attached.
		ItemStack scopeStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
		ItemStack stockStack = Gun.getAttachment(IAttachment.Type.STOCK, stack);
        if(scopeStack.isEmpty() && !stockStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.PUMP_SHOTGUN_SIGHTS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}
        
        // Render the top rail element.
        // This element appears when a scope is attached.
        // The actual model element used depends (again) on the base model variant.
        if(!scopeStack.isEmpty())
     	{
        	BakedModel topRailModel = SpecialModels.PUMP_SHOTGUN_TOP_RAIL.getModel();
            if (getVariant(stack) == 1)
            topRailModel = SpecialModels.PUMP_SHOTGUN_TOP_RAIL_1.getModel();
    	 	RenderUtil.renderModel(topRailModel, transformType, null, stack, parent, poseStack, buffer, light, overlay);
     	}

        // Heat Shield element, an optional part that renders if HeatShield is equal to 1.
        if(getVariant(stack, "HeatShield") != 0)
     	{
            RenderUtil.renderModel(SpecialModels.PUMP_SHOTGUN_HEAT_SHIELD.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
     	}
        
        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        boolean useFallbackAnimation = false;
        
        Vec3 pumpTranslations = Vec3.ZERO;
        
        Vec3 bulletTranslations = Vec3.ZERO;
        Vec3 bulletRotations = Vec3.ZERO;
        Vec3 bulletRotOffset = Vec3.ZERO;
        
        if(isPlayer && correctContext && !disableAnimations)
        {
        	try {
    				Player player = (Player) entity;
    				
        			pumpTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "pump");
        			
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
                	GunMod.LOGGER.error("NZGE encountered an error trying to apply animations.");
                	e.printStackTrace();
                	disableAnimations = true;
        		}
        }
		
		// Fallback animation logic in the event that CGM Expanded isn't installed, or a custom animation couldn't be found.
        if(disableAnimations || useFallbackAnimation)
        {
	        if(isPlayer && correctContext)
	        {
	            float cooldownDivider = 3.7F;
	            float cooldownOffset1 = 0.7F;
	            float intensity = 3.6F +1;
	            
	        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
	            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
	            cooldown *= cooldownDivider;
	            float cooldown_a = cooldown-cooldownOffset1;
	
	            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
	            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
	            float cooldown_d = Math.min(cooldown_b,cooldown_c);
	            
	            pumpTranslations = new Vec3(0, 0, cooldown_d * 1.8);
	        }
        }

		// Pump Shotgun slide. This animated part cycles backward then forward after firing.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations. This will only occur under certain conditions.
		ItemStack gripStack = Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack);
        if(isPlayer && (gripStack.isEmpty() || !disableAnimations))
        poseStack.translate(0, 0, pumpTranslations.z * 0.0625);
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.PUMP_SHOTGUN_PUMP.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // SG Shell, which is only used during custom reload animations.
        if(!disableAnimations && !useFallbackAnimation)
        {
    		// Push pose so we can make do transformations without affecting the models above.
            poseStack.pushPose();
            // Initial translation to the starting position.
            poseStack.translate(0.0, -5.15*0.0625, 2.2*0.0625);
            // Apply the transformations
            if(isPlayer && isFirstPerson)
            {
            	if(bulletTranslations!=Vec3.ZERO)
                	poseStack.translate(bulletTranslations.x*0.0625, bulletTranslations.y*0.0625, bulletTranslations.z*0.0625);
                if(bulletRotations!=Vec3.ZERO)
                    GunAnimationHelper.rotateAroundOffset(poseStack, bulletRotations, bulletRotOffset);
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