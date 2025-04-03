package com.lance5057.extradelight.data.recipebuilders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lance5057.extradelight.ExtraDelight;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.workstations.oven.recipes.OvenRecipe;
import com.lance5057.extradelight.workstations.oven.recipetab.OvenRecipeBookTab;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class OvenRecipeBuilder implements RecipeBuilder {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Item result;
    private final int count;
    private final ItemStack resultStack;
    private final int cookingTime;
    private final float experience;
    private final ItemStack container;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final Map<String, CriterionTriggerInstance> criteria = new LinkedHashMap<>();
    private final OvenRecipe.Serializer serializer;
    private OvenRecipeBookTab tab;

    private OvenRecipeBuilder(ItemStack resultIn, int cookingTime, float experience, ItemStack container,
                              OvenRecipe.Serializer serializer) {
        this.result = resultIn.getItem();
        this.resultStack = resultIn;
        this.count = resultIn.getCount();
        this.cookingTime = cookingTime;
        this.experience = experience;
        this.container = container;
        this.tab = null;
        this.serializer = serializer;
    }

    public static OvenRecipeBuilder OvenRecipe(ItemStack mainResult, int cookingTime, float experience,
                                               ItemStack container) {
        return new OvenRecipeBuilder(mainResult, cookingTime, experience, container,
                                     ExtraDelightRecipes.OVEN_SERIALIZER.get());
    }

    public OvenRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return addIngredient(Ingredient.of(tagIn));
    }

    public OvenRecipeBuilder addIngredient(TagKey<Item> itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            addIngredient(Ingredient.of(itemIn));
        }
        return this;
    }

    public OvenRecipeBuilder addIngredient(ItemLike itemIn) {
        return addIngredient(itemIn, 1);
    }

    public OvenRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            addIngredient(Ingredient.of(itemIn));
        }
        return this;
    }

    public OvenRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return addIngredient(ingredientIn, 1);
    }

    public OvenRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            ingredients.add(ingredientIn);
        }
        return this;
    }

    public OvenRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    public OvenRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    public OvenRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
        this.criteria.put("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder
                                                                                                        .item()
                                                                                                        .of(items)
                                                                                                        .build()));
        return this;
    }

    public OvenRecipeBuilder setRecipeBookTab(OvenRecipeBookTab tab) {
        this.tab = tab;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(result);
        build(consumer, ExtraDelight.MOD_ID + ":" + location.getPath());
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        save(consumerIn, new ResourceLocation(save));
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("cooking/");
        Advancement.Builder advancementBuilder = this.advancement
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);


        output.accept(new Result(id, result, count, ingredients, cookingTime, experience, container.getItem(), tab,
                                 advancementBuilder, id.withPrefix("recipes/cooking/"), this.serializer));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final OvenRecipeBookTab tab;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int cookingTime;
        private final float experience;
        private final Item container;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final OvenRecipe.Serializer serializer;

        public Result(ResourceLocation idIn, Item resultIn, int countIn, List<Ingredient> ingredientsIn,
                      int cookingTimeIn, float experienceIn, @Nullable Item containerIn,
                      @Nullable OvenRecipeBookTab tabIn, @Nullable Advancement.Builder advancement,
                      @Nullable ResourceLocation advancementId, OvenRecipe.Serializer serializer) {
            this.id = idIn;
            this.tab = tabIn;
            this.ingredients = ingredientsIn;
            this.result = resultIn;
            this.count = countIn;
            this.cookingTime = cookingTimeIn;
            this.experience = experienceIn;
            this.container = containerIn;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.serializer = serializer;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (tab != null) {
                json.addProperty("recipe_book_tab", tab.toString());
            }

            JsonArray arrayIngredients = new JsonArray();

            for (Ingredient ingredient : ingredients) {
                arrayIngredients.add(ingredient.toJson());
            }
            json.add("ingredients", arrayIngredients);

            JsonObject objectResult = new JsonObject();
            objectResult.addProperty("item", ForgeRegistries.ITEMS
                    .getKey(result)
                    .toString());
            if (count > 1) {
                objectResult.addProperty("count", count);
            }
            json.add("result", objectResult);

            if (container != null) {
                JsonObject objectContainer = new JsonObject();
                objectContainer.addProperty("item", ForgeRegistries.ITEMS
                        .getKey(container)
                        .toString());
                json.add("container", objectContainer);
            }
            if (experience > 0) {
                json.addProperty("experience", experience);
            }
            json.addProperty("cookingtime", cookingTime);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return advancement != null ? advancement.serializeToJson() : null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }

}
