package zaeonninezero.nzgexpansion.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgexpansion.client.SpecialModels;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
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
public class HeavyRevolverModel implements IOverrideModel
{
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	// We'll render the non-moving/static parts first, then render the animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.HEAVY_REVOLVER_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Now we can work on the animated parts.
		
		// Get the item's cooldown from the user entity, then do some math to make a suitable animation.
		// In this case, we multiply the cooldown value by itself to create a smooth animation.
        boolean isPlayer = (entity != null && entity.equals(Minecraft.getInstance().player) ? true : false);
        float cooldown = 0F;
        if(isPlayer)
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
        poseStack.popPose();
    }
}