package com.lance5057.extradelight.items.dynamicfood;

import java.util.Collection;

import com.lance5057.extradelight.items.dynamicfood.api.IDynamic;
import com.lance5057.extradelight.items.dynamicfood.api.IPiece;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DynamicPie extends Item implements IDynamic {
	
	public DynamicPie(Properties properties) {
		super(properties);
	}

	@Override
	public Collection<IPiece> getPieces(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

}
