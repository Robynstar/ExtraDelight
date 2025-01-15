package com.lance5057.extradelight.items.dynamicfood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lance5057.extradelight.ExtraDelight;
import com.lance5057.extradelight.ExtraDelightComponents;
import com.lance5057.extradelight.items.dynamicfood.api.DynamicItemComponent;
import com.lance5057.extradelight.items.dynamicfood.api.IDynamic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DynamicSandwich extends Item implements IDynamic {
	static final ModelResourceLocation base_model = ModelResourceLocation
			.standalone(ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "extra/sandwich_bread"));
	static final ModelResourceLocation missing_model = ModelResourceLocation
			.standalone(ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "extra/empty_sandwich"));

	public DynamicSandwich(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<BakedModel> getPieces(ItemStack itemStack) {
		List<BakedModel> i = new ArrayList<BakedModel>();
//		Minecraft.getInstance().getItemRenderer().getModel(itemStack, null, null, 0);

		BakedModel bm = Minecraft.getInstance().getModelManager().getModel(base_model);
		i.add(bm);

		DynamicItemComponent comp = itemStack.getComponents().get(ExtraDelightComponents.DYNAMIC_FOOD.get());
		if (comp != null) {
			{
				if (!comp.getItems().isEmpty())
					i.add(Minecraft.getInstance().getItemRenderer().getModel(comp.getItems().get(0), null, null, 0));
				else
					i.add(Minecraft.getInstance().getModelManager().getModel(missing_model));
			}
		} else
			i.add(Minecraft.getInstance().getModelManager().getModel(missing_model));

		return i;
	}

}
