package zaeonninezero.nzgmaddon.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.guns.client.GunModel;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import zaeonninezero.nzgmaddon.client.SpecialModels;

import javax.annotation.Nullable;

/**
 * Original attachment override model by MrCrayfish
 * Adapted by zaeonNineZero for Nine Zero's Gun Expansion
 */
public class SolidStockModel implements IOverrideModel
{
	// The Solid Stock has multiple model states, determined by what data tag the gun has.
    @Override
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource renderTypeBuffer, int light, int overlay)
    {
    	// Set the default model for the stock.
    	BakedModel stockModel = SpecialModels.SOLID_STOCK.getModel();
    	if (parent!=null && parent.getItem() instanceof GunItem)
        {
	        if (parent.is(ItemTags.create(new ResourceLocation("cgm", "use_slim_stocks"))))
	        	stockModel = SpecialModels.SOLID_STOCK_SLIM.getModel();
	        else
	        if (parent.is(ItemTags.create(new ResourceLocation("cgm", "use_raised_stocks"))))
		    	stockModel = SpecialModels.SOLID_STOCK_RAISED.getModel();
    	}
        
        //RenderUtil.renderModel(bipodModel, transformType, null, stack, parent, poseStack, renderTypeBuffer, light, overlay);
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, renderTypeBuffer, light, overlay, GunModel.wrap(stockModel));
    }
}