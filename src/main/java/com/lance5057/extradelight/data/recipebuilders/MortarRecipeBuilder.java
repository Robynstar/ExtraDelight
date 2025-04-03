package com.lance5057.extradelight.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.workstations.mortar.recipes.MortarRecipe;

public class MortarRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient ingredient;
    private final int count;
    private final int grinds;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final MortarRecipe.Serializer serializer;
    private final Map<String, CriterionTriggerInstance> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private MortarRecipeBuilder(ItemStack pResult, Ingredient pIngredient, int pGrind,
                                MortarRecipe.Serializer serializer) {
        this.result = pResult;
        this.ingredient = pIngredient;
        this.count = pResult.getCount();
        this.grinds = pGrind;
        this.serializer = serializer;
    }

    public static MortarRecipeBuilder grind(Ingredient pIngredient, ItemStack pResult, int grinds) {
        return new MortarRecipeBuilder(pResult, pIngredient, grinds, ExtraDelightRecipes.MORTAR_SERIALIZER.get());
    }

    public MortarRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    public MortarRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("mortar/");
        Advancement.Builder advancementBuilder = this.advancement
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);

        ensureValid(recipeId);
        output.accept(new Result(recipeId, group == null ? "" : group, ingredient, result.getItem(), grinds, count,
                                 advancementBuilder, id.withPrefix("recipes/mortar/"), this.serializer));
    }

    /**
     * Makes sure that this obtainable.
     */
    private void ensureValid(ResourceLocation pId) {
        if (this.advancement
                .getCriteria()
                .isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final int count;
        private final int grinds;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final MortarRecipe.Serializer serializer;

        public Result(ResourceLocation pId, String pGroup, Ingredient pIngredient, Item pResult, int pGrinds,
                      int pCount, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId,
                      MortarRecipe.Serializer pSerializer) {
            this.id = pId;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.count = pCount;
            this.grinds = pGrinds;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
            this.serializer = pSerializer;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.ingredient.toJson());

            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("item", ForgeRegistries.ITEMS
                    .getKey(result)
                    .toString());
            if (count > 1) {
                objectResult.addProperty("count", count);
            }
            pJson.add("result", objectResult);

            pJson.addProperty("grinds", this.grinds);
        }

        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is
         * no advancement.
         */
        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be
         * null if {@link #serializeAdvancement} is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}