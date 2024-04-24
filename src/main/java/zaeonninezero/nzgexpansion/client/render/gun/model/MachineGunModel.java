package zaeonninezero.nzgexpansion.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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
public class MachineGunModel implements IOverrideModel
{
    @Override
	// This class renders a multi-part model that supports animations and removeable parts.
	// We'll render the non-moving/static parts first, then render the animated parts.
	
	// We start by declaring our render function that will handle rendering the core baked model (which is a non-moving part).
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, @Nullable LivingEntity entity, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay)
    {
		// Render the item's BakedModel, which will serve as the core of our custom model.
        BakedModel bakedModel = SpecialModels.MACHINE_GUN_BASE.getModel();
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.NONE, false, poseStack, buffer, light, overlay, GunModel.wrap(bakedModel));

		// Render the iron sights element, which is only present when a scope is not attached.
		// We have to grab the gun's scope attachment slot and check whether it is empty or not.
		// If the isEmpty function returns true, then we render the iron sights.
		ItemStack attachmentStack = Gun.getAttachment(IAttachment.Type.SCOPE, stack);
        if(attachmentStack.isEmpty())
		{
            RenderUtil.renderModel(SpecialModels.MACHINE_GUN_SIGHTS.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		}

		// Now we can work on the animated parts.
		// Heads Up! This section will be pretty big, as the Machine Gun's model has lots of moving parts.
		// Some parts might be light on description/documentation!
		
		// Get the item's cooldown from the user entity, then do some math to make a suitable animation.
		// In this case, we multiply the cooldown value by itself to create a smooth animation.
        float cooldown = 0F;
        if(entity != null && entity.equals(Minecraft.getInstance().player))
        {
            ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
            cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = cooldown*cooldown;
        }

		// Machine Gun Belt. This part moves slightly on firing to simulate the movement of the belt links.
        poseStack.pushPose();
		// Transformations
        poseStack.translate(0, (cooldown * -0.06) * 0.0625, (cooldown * -0.1) * 0.0625);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BELT.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
		
		
		// Machine Gun Bullets. The bullets move to simulate feeding the belt.
		// MG Bullet 1.
        poseStack.pushPose();
		// Transformations
        poseStack.translate(0, (cooldown * -0.48) * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET1.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
		
		// MG Bullet 2.
        poseStack.pushPose();
		// Transformations
        poseStack.translate((cooldown * -0.041) * 0.0625, (cooldown * -0.475) * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET2.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
		
		// MG Bullet 3.
        poseStack.pushPose();
		// Transformations
        poseStack.translate((cooldown * -0.1) * 0.0625, (cooldown * -0.47) * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET3.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
		
		// MG Bullet 4.
        poseStack.pushPose();
		// Transformations
        poseStack.translate((cooldown * -0.24) * 0.0625, (cooldown * -0.47) * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET4.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
		
		// MG Bullet 5.
        poseStack.pushPose();
		// Transformations
        poseStack.translate((cooldown * -0.5) * 0.0625, (cooldown * -0.18) * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_BULLET5.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();

        
		// Machine Gun Handle. This part rotates slightly to simulate the effects of recoil on it.
        poseStack.pushPose();
		// Transformations
        poseStack.translate(0.811 * 0.0625, -3.04 * 0.0625, 0);
        poseStack.mulPose(Vector3f.ZN.rotationDegrees(24F * cooldown));
        poseStack.translate(-0.811 * 0.0625, 3.04 * 0.0625, 0);
		// Render
        RenderUtil.renderModel(SpecialModels.MACHINE_GUN_HANDLE.getModel(), transformType, null, stack, parent, poseStack, buffer, light, overlay);
		// POP POP
        poseStack.popPose();
        
        // Phew! We're all done here.
    }
}