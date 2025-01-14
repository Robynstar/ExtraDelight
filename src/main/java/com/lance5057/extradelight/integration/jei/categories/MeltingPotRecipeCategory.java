package com.lance5057.extradelight.integration.jei.categories;

import java.util.List;

import com.lance5057.extradelight.ExtraDelight;
import com.lance5057.extradelight.ExtraDelightItems;
import com.lance5057.extradelight.workstations.meltingpot.MeltingPotRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class MeltingPotRecipeCategory implements IRecipeCategory<MeltingPotRecipe> {
	public static final RecipeType<MeltingPotRecipe> TYPE = RecipeType.create(ExtraDelight.MOD_ID, "meltingpot",
			MeltingPotRecipe.class);
	private final IDrawable background;
	private final Component localizedName;
	private final IDrawable icon;

	public MeltingPotRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(
				ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "textures/gui/jei.png"), 204, 74, 52, 42);
		localizedName = Component.translatable("extradelight.jei.meltingpot");
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(ExtraDelightItems.MELTING_POT.get()));
	}

	@Override
	public RecipeType<MeltingPotRecipe> getRecipeType() {
		return TYPE;
	}

	@Override
	public Component getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, MeltingPotRecipe recipe, IFocusGroup focuses) {
		Ingredient input = recipe.input;

		builder.addSlot(RecipeIngredientRole.INPUT, 6, 7).addIngredients(input);

		builder.addSlot(RecipeIngredientRole.OUTPUT, this.getWidth() / 2 +3, 7)
				.addIngredients(NeoForgeTypes.FLUID_STACK, List.of(recipe.result))
				.setFluidRenderer(recipe.result.getAmount(), false, 16, 16);
	}
}
