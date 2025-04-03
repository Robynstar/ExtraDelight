package com.lance5057.extradelight.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lance5057.extradelight.ExtraDelightRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class FeastRecipe extends SingleItemRecipe {

    protected final BlockItem feast;

    public FeastRecipe(ResourceLocation id, String pGroup, BlockItem feast, Ingredient pIngredient, ItemStack pResult) {
        super(ExtraDelightRecipes.FEAST.get(), ExtraDelightRecipes.FEAST_SERIALIZER.get(), id, pGroup, pIngredient, pResult);
        this.feast = feast;
    }

    public FeastRecipe(ResourceLocation id, String pGroup, ItemStack feast, Ingredient pIngredient, ItemStack pResult) {
        super(ExtraDelightRecipes.FEAST.get(), ExtraDelightRecipes.FEAST_SERIALIZER.get(), id, pGroup, pIngredient, pResult);
        this.feast = (BlockItem) feast.getItem();
    }

    public BlockItem getFeast() {
        return feast;
    }

    public ItemStack getFeastStack() {
        return new ItemStack(feast);
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return this.ingredient.test(pContainer.getItem(0)) && this.feast == pContainer.getItem(1).getItem();
    }

    public static class Serializer implements RecipeSerializer<FeastRecipe> {

        @Override
        public FeastRecipe fromJson(ResourceLocation resourceLocation, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            JsonElement jsonelement = GsonHelper.isArrayNode(pJson, "ingredient") ? GsonHelper.getAsJsonArray(pJson, "ingredient") : GsonHelper.getAsJsonObject(pJson, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            if (!pJson.has("result"))
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (pJson.get("result").isJsonObject())
                itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
            else {
                String s1 = GsonHelper.getAsString(pJson, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Optional.ofNullable(ForgeRegistries.ITEMS.getValue(resourcelocation)).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
            }
            BlockItem blockItem = Optional.of(GsonHelper.getAsJsonObject(pJson, "block")).map(ShapedRecipe::itemFromJson).map(item -> (BlockItem) item).orElseThrow(() -> new IllegalStateException("Item: " + GsonHelper.getAsString(pJson, "block") + " does not exist"));

            return new FeastRecipe(resourceLocation, s, blockItem, ingredient, itemstack);
        }

        @Override
        public FeastRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            String s = friendlyByteBuf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack itemstack = friendlyByteBuf.readItem();
            BlockItem g = (BlockItem) friendlyByteBuf.readItem().getItem();
            return new FeastRecipe(resourceLocation, s, g, ingredient, itemstack);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, FeastRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeItem(new ItemStack(pRecipe.getFeast()));
        }

    }
}
