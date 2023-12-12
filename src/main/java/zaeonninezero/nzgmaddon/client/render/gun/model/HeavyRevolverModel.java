package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.client.GunModel;
import zaeonninezero.nzgmaddon.client.SpecialModels;
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
 * NZ NOTE: This is a placeholder model and will be replaced later.
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
        float cooldown = 0F;
        if(entity != null && entity.equals(Minecraft.getInstance().player))
        {
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = cooldown*cooldown;
        }

		// Revolver rotating cylinder. Cylinder rotates (err, simulates rotation) to its next chamber after firing.
		// Push pose - this preps the renderer for our transformations.
        poseStack.pushPose();
		// Now we apply our transformations, in this case translation and rotation.
        poseStack.translate(0, -4.43 * 0.0625, 0);
        poseStack.mulPose(Vector3f.ZN.rotationDegrees(60F * cooldown));
        poseStack.translate(0, 4.43 * 0.0625, 0);
		// Transformations done - now we can render the cylinder model.
        RenderUtil.renderModel(SpecialModels.REVOLVER_CYLINDER.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// Pop pose - this applies our model transformations and rendering, and clears the poseStack.
        poseStack.popPose();
    }
}