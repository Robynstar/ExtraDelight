package com.lance5057.extradelight.items.dynamicfood.api;

import java.util.Collection;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

public interface IDynamic {
	Collection<BakedModel> getPieces(ItemStack itemStack);
}
