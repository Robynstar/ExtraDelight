package com.lance5057.extradelight.displays.food;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;

public class FoodDisplayRenderer implements BlockEntityRenderer<FoodDisplayEntity> {

	public FoodDisplayRenderer(BlockEntityRendererProvider.Context cxt) {

	}

	@Override
	public void render(FoodDisplayEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
			MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) {
			return;
		}

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		IItemHandler itemInteractionHandler = pBlockEntity.getItemHandler();

		Direction dir = pBlockEntity.getBlockState().getValue(FoodDisplayBlock.FACING);

		pPoseStack.pushPose();
		// pPoseStack.mulPose(new Quaternion(0, -dir.toYRot(), 0, true));

		float xoff = 0;
		float yoff = 0;
		float zoff = 0;

		for (int i = 0; i < pBlockEntity.getNumSlots(); i++) {
			xoff = (i % 3) * 0.28f;
			if (i % 3 == 0) {
				zoff += 0.22f;
				yoff -= 0.1f;
			}

			ItemStack item = itemInteractionHandler.getStackInSlot(i);

			if (!item.isEmpty()) {
				BakedModel bakedmodel = itemRenderer.getModel(item, pBlockEntity.getLevel(), null, 0);
				pPoseStack.pushPose();
				pPoseStack.translate(0.5f, 0, 0.5f);
				pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, -dir.toYRot(), 0));
				pPoseStack.translate(xoff - 0.28f, yoff + 0.45, zoff - 0.4);

				if (i % 2 == 0)
					pPoseStack.translate(0, 0.05, 0.05);

				pPoseStack.mulPose(new Quaternionf().rotateXYZ(-45, 0, 0));
				float uniscale = 0.65f;
				pPoseStack.scale(uniscale, uniscale, uniscale);
				itemRenderer.render(item, ItemDisplayContext.GROUND, false, pPoseStack, pBufferSource, pPackedLight,
						pPackedOverlay, bakedmodel);
				pPoseStack.popPose();
			}
		}
		pPoseStack.popPose();
	}

	@Override
	public boolean shouldRenderOffScreen(FoodDisplayEntity pBlockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 64;
	}

	@Override
	public boolean shouldRender(FoodDisplayEntity pBlockEntity, Vec3 pCameraPos) {
		// return Vec3.atCenterOf(pBlockEntity.getBlockPos()).closerThan(pCameraPos,
		// (double) this.getViewDistance());
		return true;
	}
}
