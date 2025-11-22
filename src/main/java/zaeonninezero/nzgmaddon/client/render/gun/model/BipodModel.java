package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.guns.client.GunModel;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import zaeonninezero.nzgmaddon.client.SpecialModels;

import javax.annotation.Nullable;

/**
 * Original attachment override model by MrCrayfish
 * Adapted by zaeonNineZero for Nine Zero's Gun Expansion
 */
public class BipodModel implements IOverrideModel
{
	// The Bipod's model has two states, depending on whether the user is sneaking or crawling.
    @Override
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light, int overlay)
    {
    	// Set the default model for the Bipod.
    	BakedModel bipodModel = SpecialModels.BIPOD_FOLDED.getModel();
        boolean isPlayer = entity != null && entity instanceof Player;
        if (isPlayer && correctContext(transformType))
        {
        	Player player = (Player) entity;
        	// If the player is crouching, or crawling on the ground, switch to the 'deployed' bipod model.
	    	if (player.isCrouching() || (player.isVisuallyCrawling() && player.isOnGround()))
	    	bipodModel = SpecialModels.BIPOD_UNFOLDED.getModel();
    	}
        
        //RenderUtil.renderModel(bipodModel, transformType, null, stack, parent, poseStack, renderTypeBuffer, light, overlay);
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, renderTypeBuffer, light, overlay, GunModel.wrap(bipodModel));
    }
    private boolean correctContext(ItemTransforms.TransformType transformType)
    {
    	return (transformType.firstPerson() || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
    }
}