package com.lance5057.extradelight.data.recipebuilders;

import com.google.gson.JsonObject;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.recipe.ToolOnBlockRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ToolOnBlockBuilder implements RecipeBuilder {
    private final BlockItem in;
    private final Ingredient tool;
    private final BlockItem out;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final ToolOnBlockRecipe.Serializer serializer;
    private final Map<String, CriterionTriggerInstance> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private ToolOnBlockBuilder(BlockItem pResult, BlockItem block, Ingredient pIngredient,
                               ToolOnBlockRecipe.Serializer pSerializer) {
        this.in = pResult;
        this.tool = pIngredient;
        this.out = block;
        this.serializer = pSerializer;
    }

    public static ToolOnBlockBuilder make(BlockItem pResult, Ingredient pIngredient, BlockItem block) {
        return new ToolOnBlockBuilder(pResult, block, pIngredient, ExtraDelightRecipes.TOOL_ON_BLOCK_SERIALIZER.get());
    }

    @Override
    public ToolOnBlockBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public ToolOnBlockBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.in;
    }

    @Override
    public void save(Consumer<FinishedRecipe> output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("toolonblock/");
        Advancement.Builder advancementBuilder = this.advancement
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);

        ensureValid(recipeId);
        output.accept(new Result(recipeId, group == null ? "" : group, tool, in, out, advancementBuilder,
                                 id.withPrefix("recipes/toolonblock/"), this.serializer));
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
        private final Ingredient tool;
        private final BlockItem in;
        private final BlockItem out;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final ToolOnBlockRecipe.Serializer serializer;

        public Result(ResourceLocation pId, String pGroup, Ingredient pIngredient, BlockItem in, BlockItem out,
                      Advancement.Builder pAdvancement, ResourceLocation pAdvancementId,
                      ToolOnBlockRecipe.Serializer pSerializer) {
            this.id = pId;
            this.group = pGroup;
            this.tool = pIngredient;
            this.in = in;
            this.out = out;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
            this.serializer = pSerializer;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.tool.toJson());

            pJson.addProperty("blockIn", ForgeRegistries.ITEMS
                    .getKey(in)
                    .toString());
            pJson.addProperty("blockOut", ForgeRegistries.ITEMS
                    .getKey(out)
                    .toString());
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
