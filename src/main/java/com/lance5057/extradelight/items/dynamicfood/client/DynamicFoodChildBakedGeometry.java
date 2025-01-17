package com.lance5057.extradelight.items.dynamicfood.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.data.ModelData;

public class DynamicFoodChildBakedGeometry implements BakedModel {
	private final List<BakedModel> childrenModels;

	public DynamicFoodChildBakedGeometry(List<BakedModel> childrenModels) {
		this.childrenModels = childrenModels;
	}

	@Override
	@NotNull
	@SuppressWarnings("deprecation")
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand) {
		List<BakedQuad> bakedQuads = new ArrayList<>();
		float x = 0;
		for (BakedModel bakedModel : childrenModels) {
			bakedQuads.addAll(shiftedQuad(bakedModel.getQuads(state, side, rand), new Vector3f(0, x, 0)));
			x += -1f;
		}
		return bakedQuads;
	}

	@Override
	@NotNull
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand,
			@NotNull ModelData data, @Nullable RenderType renderType) {
		List<BakedQuad> bakedQuads = new ArrayList<>();
		int x = 0;
		for (BakedModel bakedModel : childrenModels) {
			bakedQuads.addAll(bakedModel.getQuads(state, side, rand, data, renderType));
		}
		return bakedQuads;
	}

	List<BakedQuad> shiftedQuad(List<BakedQuad> quads, Vector3f vec) {
		List<BakedQuad> newQuads = new ArrayList<BakedQuad>();

		for (BakedQuad q : quads) {
			newQuads.add(new BakedQuad(shiftedVerts(q.getVertices().clone(), vec), q.getTintIndex(), q.getDirection(),
					q.getSprite(), q.isShade()));
		}

		return newQuads;
	}

	int[] shiftedVerts(int[] verts, Vector3f vec) {
		for (int i = 0; i < verts.length; i += 2) {
			float f = Float.intBitsToFloat(verts[i]);
			f += vec.x;
			verts[i] = Float.floatToIntBits(f);

			f = Float.intBitsToFloat(verts[i + 1]);
			f += vec.x;
			verts[i + 1] = Float.floatToIntBits(f);
		}

		return verts;
	}

	@Override
	public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack,
			boolean applyLeftHandTransform) {
		for (BakedModel bakedModel : childrenModels) {
			bakedModel.getTransforms().getTransform(transformType).apply(applyLeftHandTransform, poseStack);
		}
		return this;
	}

	@Override
	@NotNull
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand,
			@NotNull ModelData data) {
		Set<ChunkRenderTypeSet> chunkRenderTypeSets = new HashSet<>();
		for (BakedModel bakedModel : childrenModels) {
			chunkRenderTypeSets.add(bakedModel.getRenderTypes(state, rand, data));
		}
		return ChunkRenderTypeSet.union(chunkRenderTypeSets);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return this.childrenModels.stream().anyMatch(BakedModel::useAmbientOcclusion);
	}

	@Override
	public boolean isGui3d() {
		return this.childrenModels.stream().anyMatch(BakedModel::isGui3d);
	}

	@Override
	public boolean usesBlockLight() {
		return this.childrenModels.stream().anyMatch(BakedModel::usesBlockLight);
	}

	@Override
	public boolean isCustomRenderer() {
		return this.childrenModels.stream().anyMatch(BakedModel::isCustomRenderer);
	}

	@Override
	@NotNull
	@SuppressWarnings("deprecation")
	public TextureAtlasSprite getParticleIcon() {
		if (this.childrenModels.isEmpty()) {
			return ModelBakery.FIRE_0.sprite();
		} else {
			return this.childrenModels.get(0).getParticleIcon();
		}
	}

	@Override
	@NotNull
	public TextureAtlasSprite getParticleIcon(@NotNull ModelData modelData) {
		if (this.childrenModels.isEmpty()) {
			return ModelBakery.FIRE_0.sprite();
		} else {
			return this.childrenModels.get(0).getParticleIcon(modelData);
		}
	}

	@Override
	@NotNull
	public ItemOverrides getOverrides() {
		return new ItemOverrides() {
		};
	}
}