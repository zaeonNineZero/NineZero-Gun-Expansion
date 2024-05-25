package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgmaddon.client.SpecialModels;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 * Modified by zaeonNineZero for Nine Zero's Gun Expansion
 * Attachment detection logic based off of code from Mo' Guns by Bomb787 and AlanorMiga (MigaMi)
 */
public class PumpShotgunModel implements IOverrideModel
{
    @Override
	// This class renders a multi-part model that supports animations, removeable parts.
	// We're also including support for model variations based on the CustomModelData NBT tag.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
    	// We select which model variant to use by fetching the value of the CustomModelData tag.
        BakedModel bakedModel = SpecialModels.PUMP_SHOTGUN_BASE.getModel();
        if (getVariant(stack) == 1)
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
        
        // Next, we do the animated parts.
		
		// Get the item's cooldown from the user entity, then process it into a usable animation.
        boolean isPlayer = (entity != null && entity.equals(Minecraft.getInstance().player) ? true : false);
        boolean correctContext = (transformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        GunItem gunStack = (GunItem) stack.getItem();
        Gun gun = gunStack.getModifiedGun(stack);
        float boltMovement = 0F;
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
            
            boltMovement = cooldown_d;
        }

		// Pump Shotgun slide. This animated part cycles backward then forward after firing.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations. We will ONLY do so if a grip is not attached.
		ItemStack gripStack = Gun.getAttachment(IAttachment.Type.UNDER_BARREL, stack);
        if(isPlayer && gripStack.isEmpty())
        poseStack.translate(0, 0, (boltMovement * 1.8) * 0.0625);
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.PUMP_SHOTGUN_PUMP.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
    }
    
    //NBT fetch code for skin variants - ported from the "hasAmmo" function under common/Gun.java
    public static int getVariant(ItemStack gunStack)
    {
        CompoundTag tag = gunStack.getOrCreateTag();
        return tag.getInt("CustomModelData");
    }
}