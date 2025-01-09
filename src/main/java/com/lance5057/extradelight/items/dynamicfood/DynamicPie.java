package com.lance5057.extradelight.items.dynamicfood;

import java.util.Collection;

import com.lance5057.extradelight.ExtraDelightComponents;
import com.lance5057.extradelight.items.dynamicfood.api.DynamicItemComponent;
import com.lance5057.extradelight.items.dynamicfood.api.IDynamic;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DynamicPie extends Item implements IDynamic {

	public DynamicPie(Properties properties) {
		super(properties);
	}

	@Override
	public Collection<ItemStack> getPieces(ItemStack itemStack) {
		DynamicItemComponent comp = itemStack.getComponents().get(ExtraDelightComponents.DYNAMIC_FOOD.get());
		if (comp != null) {
			return comp.getItems();
		}
		return null;
	}

}
