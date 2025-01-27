package com.lance5057.extradelight.util;

import java.util.function.Supplier;

import com.lance5057.extradelight.ExtraDelightItems;
import com.lance5057.extradelight.ExtraDelightNeoForgeClientEvents;
import com.lance5057.extradelight.data.advancement.EDAdvancementGenerator;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class EDItemGenerator {
	DeferredItem<Item> item;

	private EDItemGenerator(DeferredItem<Item> item) {
		this.item = item;
	}

	public static <I extends Item> EDItemGenerator register(String registrationName, Supplier<? extends I> item) {
		return new EDItemGenerator(ExtraDelightItems.ITEMS.register(registrationName, item));
	}

	public EDItemGenerator advancementSnack() {
		EDAdvancementGenerator.SNACKS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementFeast() {
		EDAdvancementGenerator.FEASTS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementMeal() {
		EDAdvancementGenerator.MEALS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementDessert() {
		EDAdvancementGenerator.DESSERTS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementDrink() {
		EDAdvancementGenerator.DRINKS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementCandy() {
		EDAdvancementGenerator.CANDY.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementCookie() {
		EDAdvancementGenerator.COOKIES.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementButchercraft() {
		EDAdvancementGenerator.BUTCHERCRAFT.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator advancementIngredients() {
		EDAdvancementGenerator.INGREDIENTS.put(item.getRegisteredName(), item);
		return this;
	}

	public EDItemGenerator butchercraftToolTip() {
		ExtraDelightNeoForgeClientEvents.butchercraft.add(item);
		return this;
	}

	public EDItemGenerator feastToolTip() {
		ExtraDelightNeoForgeClientEvents.feasts.add(item);
		return this;
	}

	public EDItemGenerator servingToolTip() {
		ExtraDelightNeoForgeClientEvents.servings.add(item);
		return this;
	}

	public DeferredItem<Item> finish() {
		return item;
	}
}
