package com.lance5057.extradelight.items.dynamicfood.client;

import java.util.function.Function;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;
import net.neoforged.neoforge.client.model.geometry.UnbakedGeometryHelper;

public class DynamicFoodUnbakedGeometry implements IUnbakedGeometry<DynamicFoodUnbakedGeometry> {

//    @Override
//    public Collection<Material> getMaterials(IGeometryBakingContext context, Function modelGetter, Set missingTextureErrors) {
//        return Collections.emptyList();
//    }

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		
		return new DynamicFoodParentBakedGeometry();
	}
}