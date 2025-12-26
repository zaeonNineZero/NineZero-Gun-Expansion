package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.GunModel;
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
public class SubmachineGunModel implements IOverrideModel
{
	private boolean hasExpanded = CGMExpandedHelper.isExpandedInstalled();
	private boolean disableAnimations = false;
	
    @Override
	// This class renders a model with support for NBT and attachment based part variations,
	// and custom animations from CGM Expanded.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Select the Baked Model we'll be rendering, based on the value of the CustomModelData tag.
        BakedModel bakedModel = SpecialModels.SUBMACHINE_GUN_BASE.getModel();
        if (getVariant(stack) == 1 || getVariant(stack, "BaseVariant") == 1)
        bakedModel = SpecialModels.SUBMACHINE_GUN_BASE_1.getModel();
        
        // Render the BakedModel we selected.
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the top rail element, which is only present when a scope is attached.
        ItemStack attachmentScopeStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(!attachmentScopeStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_TOP_RAIL.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

		// Render the side rails element, which is only present when an underbarrel attachment is equipped.
		// This also renders when the "ExtraRails" NBT tag is set to 1.
        boolean renderExtraRails = getVariant(stack, "ExtraRails") == 1;
        if (hasExpanded && renderExtraRails == false)
        {
        	ItemStack attachmentTacticalStack = Gun.getAttachment(IAttachment.Type.TACTICAL, stack);
        	renderExtraRails = !attachmentTacticalStack.isEmpty();
    	}
        if(renderExtraRails)
		{
            RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_SIDE_RAILS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

		// Render the bottom rail element, which is only present when an underbarrel attachment is equipped.
		// This also renders when the "ExtraRails" NBT tag is set to 1.
        ItemStack attachmentGripStack = Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack);
        if(!attachmentGripStack.isEmpty() || renderExtraRails)
		{
            RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_BOTTOM_RAIL.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

		// Render the stock adapter element, which is only present when a stock attachment is equipped.
        ItemStack attachmentStockStack = Gun.getAttachment(IAttachment.Type.STOCK, stack);
        if(!attachmentStockStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_STOCK_ADAPTER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

        // Special animated segment for compat with the CGM Expanded fork.
        // First, some variables for animation building
        boolean isPlayer = entity != null && entity.equals(Minecraft.getInstance().player);
        boolean isFirstPerson = (transformType.firstPerson());
        boolean correctContext = (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        
        Vec3 boltTranslations = Vec3.ZERO;
        Vec3 boltRotations = Vec3.ZERO;
        Vec3 boltRotOffset = Vec3.ZERO;
        
        Vec3 magTranslations = Vec3.ZERO;
        Vec3 magRotations = Vec3.ZERO;
        Vec3 magRotOffset = Vec3.ZERO;
        
        if(hasExpanded && !disableAnimations && isPlayer && correctContext)
        {
        	try {
    				Player player = (Player) entity;
    				boltTranslations = GunAnimationHelper.getSmartAnimationTrans(stack, player, partialTicks, "bolt_handle");
    				boltRotations = GunAnimationHelper.getSmartAnimationRot(stack, player, partialTicks, "bolt_handle");
    				boltRotOffset = GunAnimationHelper.getSmartAnimationRotOffset(stack, player, partialTicks, "bolt_handle");
					
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
        
        // Fire animation is done the old way, and added onto the existing animation.
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
        if(isPlayer && correctContext)
        {
            float cooldownDivider = 1.0F*Math.max((float) gun.getGeneral().getRate()/1F,1);
            float cooldownOffset1 = cooldownDivider - 1.0F;
            float intensity = 1.0F +1;
            
        	ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown *= cooldownDivider;
            float cooldown_a = cooldown-cooldownOffset1;

            float cooldown_b = Math.min(Math.max(cooldown_a*intensity,0),1);
            float cooldown_c = Math.min(Math.max((-cooldown_a*intensity)+intensity,0),1);
            float cooldown_d = Math.min(cooldown_b,cooldown_c);
            
            boltTranslations = boltTranslations.add(0, 0, cooldown_d * 0.125);
        }
        
		// SMG Charging handle
        poseStack.pushPose();
        // Apply transformations to this part.
        if(isPlayer)
        {
        	if(boltTranslations!=Vec3.ZERO)
        	poseStack.translate(0, 0, boltTranslations.z*0.0625);
        	if(boltRotations!=Vec3.ZERO && !disableAnimations)
               GunAnimationHelper.rotateAroundOffset(poseStack, boltRotations, boltRotOffset);
    	}
        // Render the transformed model.
        RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_BOLT_HANDLE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
        
        // Magazine
        poseStack.pushPose();
        // Apply transformations to this part.
        if(hasExpanded && !disableAnimations && isPlayer && isFirstPerson)
        {
        	if(magTranslations!=Vec3.ZERO)
        	poseStack.translate(magTranslations.x*0.0625, magTranslations.y*0.0625, magTranslations.z*0.0625);
        	if(magRotations!=Vec3.ZERO)
               GunAnimationHelper.rotateAroundOffset(poseStack, magRotations, magRotOffset);
    	}
        // Render the transformed model.
        RenderUtil.renderModel(SpecialModels.SUBMACHINE_GUN_MAGAZINE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
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
}