//package com.lance5057.extradelight.items.dynamicfood.api;
//
//import org.jetbrains.annotations.NotNull;
//
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.ItemLike;
//
//public interface IPiece extends ItemLike {
//	void applyAttributes(@NotNull ItemStack itemStack);
//
//	@NotNull
//	PieceType getType();
//
//	default boolean isValidRepairItem(ItemStack toRepair, ItemStack repairing) {
//		return false;
//	}
//}
