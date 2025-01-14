package com.lance5057.extradelight.integration.jei;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import com.lance5057.extradelight.ExtraDelight;
import com.lance5057.extradelight.ExtraDelightItems;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.integration.jei.categories.ChillerRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.DoughShapingRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.DryingRackRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.FeastRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.MeltingPotRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.MixingBowlRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.MortarRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.OvenRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.ShapedWithJarRecipeCategory;
import com.lance5057.extradelight.integration.jei.categories.ToolOnBlockRecipeCatagory;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
	private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "main");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new MortarRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new MixingBowlRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new OvenRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new DryingRackRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new DoughShapingRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new ToolOnBlockRecipeCatagory(registry.getJeiHelpers().getGuiHelper()),
				new FeastRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new MeltingPotRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new ChillerRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new ShapedWithJarRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registry) {
		registry.addRecipes(MortarRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.MORTAR.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(MixingBowlRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.MIXING_BOWL.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(OvenRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.OVEN.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(DryingRackRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.DRYING_RACK.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(DoughShapingRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.DOUGH_SHAPING.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(FeastRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.FEAST.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(ToolOnBlockRecipeCatagory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.TOOL_ON_BLOCK.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(MeltingPotRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.MELTING_POT.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(ShapedWithJarRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.SHAPED_JAR.get()).stream().map(RecipeHolder::value).toList());
		registry.addRecipes(ChillerRecipeCategory.TYPE, Minecraft.getInstance().level.getRecipeManager()
				.getAllRecipesFor(ExtraDelightRecipes.CHILLER.get()).stream().map(RecipeHolder::value).toList());

		registry.addIngredientInfo(new ItemStack(ExtraDelightItems.MINT.get()), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.mint"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.CINNAMON_SAPLING.get()),
				new ItemStack(ExtraDelightItems.CINNAMON_LOG.get()), new ItemStack(ExtraDelightItems.CINNAMON_BARK.get()
						)), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.cinnamon"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.WILD_GINGER.get()),
						new ItemStack(ExtraDelightItems.GINGER.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.ginger"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.CORN_SEEDS.get()),
						new ItemStack(ExtraDelightItems.UNSHUCKED_CORN.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.corn"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.WILD_CHILI_BLOCK.get()),
						new ItemStack(ExtraDelightItems.CHILI.get()), new ItemStack(ExtraDelightItems.CHILI_SEEDS.get()
						)), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.chili"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.WILD_MALLOW_ROOT_BLOCK.get()),
						new ItemStack(ExtraDelightItems.MALLOW_ROOT.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.mallow"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.WILD_PEANUT_BLOCK.get()),
						new ItemStack(ExtraDelightItems.PEANUTS_IN_SHELL.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.peanut"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.COFFEE_CHERRIES.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.coffee"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.HAZELNUT_SAPLING.get()),
						new ItemStack(ExtraDelightItems.HAZELNUTS_IN_SHELL.get()),
						new ItemStack(ExtraDelightItems.HAZELNUT_LEAVES.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.hazelnut"));
		registry.addIngredientInfo(List.of(new ItemStack(ExtraDelightItems.APPLE_SAPLING.get()),
						new ItemStack(Items.APPLE),
						new ItemStack(ExtraDelightItems.APPLE_LEAVES.get())), VanillaTypes.ITEM_STACK,
				Component.translatable(ExtraDelight.MOD_ID + ".jei.info.hazelnut"));

		List<ItemStack> hide = List.of(ExtraDelightItems.EASTER_EGG.get().getDefaultInstance());
		registry.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, hide);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_AMETHYST.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_ANDESITE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_BASALT.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_BLACKSTONE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_DEEPSLATE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_DIORITE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_ENDSTONE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_GILDED_BLACKSTONE.get()),
				MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_GRANITE.get()), MortarRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.PESTLE_STONE.get()), MortarRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.MORTAR_STONE.get()), MortarRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.WOODEN_SPOON.get()), MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.STONE_SPOON.get()), MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.IRON_SPOON.get()), MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.GOLD_SPOON.get()), MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.DIAMOND_SPOON.get()), MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.NETHERITE_SPOON.get()),
				MixingBowlRecipeCategory.TYPE);
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.MIXING_BOWL.get()), MixingBowlRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.OVEN.get()), OvenRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.DRYING_RACK.get()), DryingRackRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.DOUGH_SHAPING.get()),
				DoughShapingRecipeCategory.TYPE);

		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.MELTING_POT.getDelegate()),
				MeltingPotRecipeCategory.TYPE);
		
		registry.addRecipeCatalyst(new ItemStack(ExtraDelightItems.CHILLER.getDelegate()),
				ChillerRecipeCategory.TYPE);
	}

}
