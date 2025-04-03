package com.lance5057.extradelight.workstations.mortar.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

import com.lance5057.extradelight.ExtraDelightBlocks;
import com.lance5057.extradelight.ExtraDelightRecipes;

public class MortarRecipe extends SingleItemRecipe {
    protected final int grinds;

    public MortarRecipe(ResourceLocation id, String pGroup, Ingredient pIngredient, ItemStack pResult, int grinds) {
        super(ExtraDelightRecipes.MORTAR.get(), ExtraDelightRecipes.MORTAR_SERIALIZER.get(), id, pGroup, pIngredient, pResult);

        this.grinds = grinds;
    }

    public int getGrinds() {
        return grinds;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ExtraDelightBlocks.MORTAR_STONE.get());
    }

    public static class Serializer implements RecipeSerializer<MortarRecipe> {

        @Override
        public MortarRecipe fromJson(ResourceLocation id, JsonObject jsonObject) {
            String s = GsonHelper.getAsString(jsonObject, "group", "");
            JsonElement jsonelement = GsonHelper.isArrayNode(jsonObject, "ingredient") ? GsonHelper.getAsJsonArray(jsonObject, "ingredient") : GsonHelper.getAsJsonObject(jsonObject, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonelement);

            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            int stirs = jsonObject.getAsJsonPrimitive("grinds").getAsInt();

            return new MortarRecipe(id, s, ingredient, itemstack, stirs);
        }


        @Override
        public MortarRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            int g = pBuffer.readInt();
            return new MortarRecipe(id, s, ingredient, itemstack, g);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, MortarRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeInt(pRecipe.grinds);
        }

    }
}