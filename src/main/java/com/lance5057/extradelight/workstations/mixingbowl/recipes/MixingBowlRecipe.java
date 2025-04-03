package com.lance5057.extradelight.workstations.mixingbowl.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.extradelight.ExtraDelightRecipes;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;

public class MixingBowlRecipe implements Recipe<SimpleContainer> {
    protected final int stirs;
    final ResourceLocation id;
    final ItemStack usedItem;

    final String group;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    public MixingBowlRecipe(ResourceLocation id, String pGroup, NonNullList<Ingredient> pIngredients, ItemStack pResult, int stirs, ItemStack usedItem) {
        this.id = id;
        this.stirs = stirs;
        this.usedItem = usedItem;
        this.group = pGroup;
        this.result = pResult;
        this.ingredients = pIngredients;
        this.isSimple = pIngredients.stream().allMatch(Ingredient::isSimple);
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(SimpleContainer pInv, Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < pInv.getContainerSize(); ++j) {
            ItemStack itemstack = pInv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple) stackedcontents.accountStack(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList) null) : RecipeMatcher.findMatches(inputs, this.ingredients) != null);
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return this.result;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack assemble(SimpleContainer pInv, RegistryAccess p_267165_) {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.ingredients.size();
    }

    public RecipeSerializer<?> getSerializer() {
        return ExtraDelightRecipes.MIXING_BOWL_SERIALIZER.get();
    }

    public int getStirs() {
        return stirs;
    }

    public ItemStack getUsedItem() {
        return this.usedItem;
    }

    @Override
    public RecipeType<?> getType() {
        // TODO Auto-generated method stub
        return ExtraDelightRecipes.MIXING_BOWL.get();
    }

    public static class Serializer implements RecipeSerializer<MixingBowlRecipe> {

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < pIngredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i), true);
                nonnulllist.add(ingredient);
            }

            return nonnulllist;
        }

        @Override
        public MixingBowlRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 32) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is 32");
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                int stirs = pJson.getAsJsonPrimitive("stirs").getAsInt();
                ItemStack usedItem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(pJson, "usedItem"), true, false);//ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "usedItem"));
                return new MixingBowlRecipe(pRecipeId, s, nonnulllist, itemstack, stirs, usedItem);
            }
        }

        @Override
        public MixingBowlRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack itemstack = pBuffer.readItem();
            int stirs = pBuffer.readVarInt();
            ItemStack usedItem = pBuffer.readItem();
            return new MixingBowlRecipe(resourceLocation, s, nonnulllist, itemstack, stirs, usedItem);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, MixingBowlRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pBuffer.writeVarInt(pRecipe.getIngredients().size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeVarInt(pRecipe.getStirs());
            pBuffer.writeItem(pRecipe.getUsedItem());

        }

    }
}