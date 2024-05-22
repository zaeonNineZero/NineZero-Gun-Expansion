package zaeonninezero.nzgexpansion.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgexpansion.client.SpecialModels;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 * Modified by zaeonNineZero for Nine Zero's Gun Expansion
 * Attachment detection logic based off of code from Mo' Guns by Bomb787 and AlanorMiga (MigaMi)
 */
public class InfantryRifleModel implements IOverrideModel
{
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	// We'll render the non-moving/static parts first, then render the animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.INFANTRY_RIFLE_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the top rail element that appears when a scope is attached.
		// We have to grab the gun's scope attachment slot and check whether it is empty or not.
		// If the isEmpty function returns false, then we render the attachment rail.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(!attachmentStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.INFANTRY_RIFLE_RAIL.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

		// Next, we do the animated parts.
		
		// Get the item's cooldown from the user entity, then do some math to make a suitable animation.
		// In this case, we multiply the cooldown value by itself to create a smooth animation.
        boolean isPlayer = (entity != null && entity.equals(Minecraft.getInstance().player) ? true : false);
        float cooldown = 0F;
        if(isPlayer)
        {
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown*= cooldown;
        }

		// Infantry Rifle charging handle. This animated part kicks backward on firing, then moves back to its resting position.
		// Push pose so we can make do transformations without affecting the models above.
        poseStack.pushPose();
		// Now we apply our transformations.
		// All we need to do is move the model based on the cooldown variable.
        if(isPlayer)
        poseStack.translate(0, 0, (cooldown * 1.9) * 0.0625);
		// Our transformations are done - now we can render the model.
        RenderUtil.renderModel(SpecialModels.INFANTRY_RIFLE_CHAMBER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose to compile everything in the render matrix.
        poseStack.popPose();
    }
}