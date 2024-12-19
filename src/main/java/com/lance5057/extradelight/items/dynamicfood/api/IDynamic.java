package com.lance5057.extradelight.items.dynamicfood.api;

import java.util.Collection;

import net.minecraft.world.item.ItemStack;

public interface IDynamic {
	Collection<IPiece> getPieces(ItemStack itemStack);
}
