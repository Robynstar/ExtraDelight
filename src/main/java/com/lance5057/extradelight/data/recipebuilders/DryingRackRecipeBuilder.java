package com.lance5057.extradelight.data.recipebuilders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.workstations.dryingrack.DryingRackSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class DryingRackRecipeBuilder implements RecipeBuilder {

    private final Item result;

    private final Ingredient ingredient;

    private final float experience;

    private final int cookingTime;

    private final DryingRackSerializer serializer;

    private final Map<String, CriterionTriggerInstance> criteria = new LinkedHashMap<>();

    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    @Nullable
    private String group;

    private DryingRackRecipeBuilder(Item pResult,
                                    Ingredient pIngredient,
                                    float pExperience,
                                    int pCookingTime,
                                    DryingRackSerializer dryingRackSerializer) {
        this.result = pResult;
        this.ingredient = pIngredient;
        this.experience = pExperience;
        this.cookingTime = pCookingTime;
        this.serializer = dryingRackSerializer;
    }

    public static DryingRackRecipeBuilder drying(Ingredient pIngredient,
                                                 Item pResult,
                                                 float pExperience,
                                                 int pCookingTime) {
        return new DryingRackRecipeBuilder(pResult, pIngredient, pExperience, pCookingTime,
            ExtraDelightRecipes.DRYING_RACK_SERIALIZER.get());
    }

    @Override
    public DryingRackRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTriggerInstance) {
        this.criteria.put(criterionName, criterionTriggerInstance);
        return this;
    }

    public DryingRackRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        ResourceLocation recipeId = resourceLocation.withPrefix("drying/");
        Advancement.Builder advancementBuilder = this.advancement.addCriterion("has_the_recipe",
                RecipeUnlockedTrigger.unlocked(recipeId))
            .rewards(AdvancementRewards.Builder.recipe(recipeId))
            .requirements(RequirementsStrategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);
        consumer.accept(new Result(recipeId, group, ingredient, result, experience, cookingTime, advancementBuilder,
            resourceLocation.withPrefix("recipes/drying/"), this.serializer));
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;

        private final String group;

        private final Ingredient ingredient;

        private final Item result;

        private final float experience;

        private final int cookingTime;

        private final Advancement.Builder advancement;

        private final ResourceLocation advancementId;

        private final DryingRackSerializer serializer;

        public Result(ResourceLocation pId,
                      String pGroup,
                      Ingredient pIngredient,
                      Item pResult,
                      float pExperience,
                      int pCookingTime,
                      Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId,
                      DryingRackSerializer pSerializer) {
            this.id = pId;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.experience = pExperience;
            this.cookingTime = pCookingTime;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
            this.serializer = pSerializer;
        }

        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.ingredient.toJson());
            pJson.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result)
                .toString());
            pJson.addProperty("experience", this.experience);
            pJson.addProperty("cookingtime", this.cookingTime);
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