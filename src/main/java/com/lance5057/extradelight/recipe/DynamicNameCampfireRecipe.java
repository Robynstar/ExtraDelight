package com.lance5057.extradelight.recipe;

import java.util.Optional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicNameCampfireRecipe extends CampfireCookingRecipe {

    public DynamicNameCampfireRecipe(ResourceLocation id, String p_250200_, CookingBookCategory p_251114_,
                                     Ingredient p_250340_, ItemStack p_250306_, float p_249577_, int p_250030_) {
        super(id, p_250200_, p_251114_, p_250340_, p_250306_, p_249577_, p_250030_);
    }

    @Override
    public ItemStack assemble(Container pInv, RegistryAccess p_267063_) {
        ItemStack stack = this.result.copy();
        ItemStack stackIn = pInv.getItem(0);

        if (stackIn.hasTag()) {
            CompoundTag tag = stackIn.getTag();

            stack.getOrCreateTag()
                    .put("ingredients", tag.get("ingredients"));
        }

        return stack;
    }

    public static class Serializer implements RecipeSerializer<DynamicNameCampfireRecipe> {
        @Override
        public DynamicNameCampfireRecipe fromJson(ResourceLocation id, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            JsonElement jsonelement = GsonHelper.isArrayNode(pJson, "ingredient")
                    ? GsonHelper.getAsJsonArray(pJson, "ingredient")
                    : GsonHelper.getAsJsonObject(pJson, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            if (!pJson.has("result"))
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (pJson
                    .get("result")
                    .isJsonObject())
                itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
            else {
                String s1 = GsonHelper.getAsString(pJson, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Optional
                    .ofNullable(ForgeRegistries.ITEMS.getValue(resourcelocation))
                    .orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
            }
            float f = GsonHelper.getAsFloat(pJson, "experience", 1f);
            int i = GsonHelper.getAsInt(pJson, "time", 1);
            return new DynamicNameCampfireRecipe(id, s, CookingBookCategory.MISC, ingredient, itemstack, f, i);
        }

        @Override
        public DynamicNameCampfireRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            float f = pBuffer.readFloat();
            int i = pBuffer.readVarInt();
            return new DynamicNameCampfireRecipe(id, s, CookingBookCategory.MISC, ingredient, itemstack, f, i);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, DynamicNameCampfireRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeFloat(pRecipe.experience);
            pBuffer.writeVarInt(pRecipe.cookingTime);
        }

    }
}
