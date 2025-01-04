package com.lance5057.extradelight.food;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class EDFoods {

	public static final Supplier<MobEffectInstance> GOLDEN_APPLE_1 = () -> new MobEffectInstance(MobEffects.REGENERATION,100,1);
	public static final Supplier<MobEffectInstance> GOLDEN_APPLE_2 = () -> new MobEffectInstance(MobEffects.ABSORPTION,2400,0);
	public static final Supplier<MobEffectInstance> HUNGER = () -> new MobEffectInstance(MobEffects.HUNGER,600,0);
	public static final Supplier<MobEffectInstance> ROT = () -> new MobEffectInstance(MobEffects.CONFUSION,FoodValues.SHORT_DURATION,0);
	public static final Supplier<MobEffectInstance> JUMP_BRIEF = () -> new MobEffectInstance(MobEffects.JUMP,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> REGENERATION_BRIEF = () -> new MobEffectInstance(MobEffects.REGENERATION,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> DAMAGE_RESISTANCE_MEDIUM = () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,FoodValues.MEDIUM_DURATION,0);
	public static final Supplier<MobEffectInstance> ABSORPTION_SHORT = () -> new MobEffectInstance(MobEffects.ABSORPTION,FoodValues.SHORT_DURATION,0);
	public static final Supplier<MobEffectInstance> DOLPHIN_BRIEF = () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> COMFORT_BRIEF = () -> new MobEffectInstance(ModEffects.COMFORT,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> COMFORT_MEDIUM = () -> new MobEffectInstance(ModEffects.COMFORT,FoodValues.MEDIUM_DURATION,0);
	public static final Supplier<MobEffectInstance> COMFORT_LONG = () -> new MobEffectInstance(ModEffects.COMFORT,FoodValues.LONG_DURATION,0);
	public static final Supplier<MobEffectInstance> NOURISHMENT_BRIEF = () -> new MobEffectInstance(ModEffects.NOURISHMENT,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> NOURISHMENT_MEDIUM = () -> new MobEffectInstance(ModEffects.NOURISHMENT,FoodValues.MEDIUM_DURATION,0);
	public static final Supplier<MobEffectInstance> NOURISHMENT_LONG = () -> new MobEffectInstance(ModEffects.NOURISHMENT,FoodValues.LONG_DURATION,0);
	public static final Supplier<MobEffectInstance> GLOW_SHORT = () -> new MobEffectInstance(MobEffects.GLOWING,FoodValues.SHORT_DURATION,0);
	public static final Supplier<MobEffectInstance> PAIN = () -> new MobEffectInstance(MobEffects.HARM,1,0);
	public static final Supplier<MobEffectInstance> SPEED_BRIEF = () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> FIRE_RESISTANCE_BRIEF = () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,FoodValues.BRIEF_DURATION,0);
	public static final Supplier<MobEffectInstance> FIRE_RESISTANCE_MEDIUM = () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,FoodValues.MEDIUM_DURATION,0);
	public static final Supplier<MobEffectInstance> SPEED_MEDIUM = () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,FoodValues.MEDIUM_DURATION,0);

	public static final FoodProperties NOTFOOD = new FoodProperties.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().build();
	public static final FoodProperties BADFOOD = new FoodProperties.Builder().nutrition(2).saturationModifier(1f).effect(ROT, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CUSTARD = new FoodProperties.Builder().nutrition(7).saturationModifier(0.6f).usingConvertsTo(Items.GLASS_BOTTLE).alwaysEdible().build();
	public static final FoodProperties WHIPPED_CREAM = new FoodProperties.Builder().nutrition(0).saturationModifier(0.1f).usingConvertsTo(Items.BOWL).alwaysEdible().build();
	public static final FoodProperties EDIBLE_SEEDS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).alwaysEdible().build();
	public static final FoodProperties SUGAR = new FoodProperties.Builder().nutrition(0).saturationModifier(0.1f).alwaysEdible().build();
	public static final FoodProperties OIL = new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties VINEGAR = new FoodProperties.Builder().nutrition(1).saturationModifier(0f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties MILK = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
	public static final FoodProperties MUSHROOM = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4f).build();
	public static final FoodProperties FLOUR = new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build();
	public static final FoodProperties BAD_FOOD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).effect(ROT, 0.8F).build();
	public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
	public static final FoodProperties BUTTER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).effect(HUNGER, 0.3F).build();
	public static final FoodProperties COOKED_SAUSAGE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2f).build();
	public static final FoodProperties COOKED_BRAIN = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build();
	public static final FoodProperties COOKED_HEART = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
	public static final FoodProperties COOKED_LIVER = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
	public static final FoodProperties COOKED_LUNG = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
	public static final FoodProperties COOKED_STOMACH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build();
	public static final FoodProperties COOKED_TRIPE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2f).build();
	public static final FoodProperties CACTUS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).effect(PAIN, 1F).build();
	public static final FoodProperties COOKED_CORN = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties GRILLED_CORN = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build();
	public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).usingConvertsTo(Items.BOWL).alwaysEdible().build();
	public static final FoodProperties TOPPED_ICE_CREAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5f).usingConvertsTo(Items.BOWL).alwaysEdible().build();
	public static final FoodProperties COOKIE_DOUGH = new FoodProperties.Builder().nutrition(1).saturationModifier(0f).build();
	public static final FoodProperties SLICED_ONION = new FoodProperties.Builder().nutrition(0).saturationModifier(0.1f).build();
	public static final FoodProperties SLICED_TOMATO = new FoodProperties.Builder().nutrition(0).saturationModifier(0.07f).build();
	public static final FoodProperties COOKED_CARROT = new FoodProperties.Builder().nutrition(7).saturationModifier(0.6f).build();
	public static final FoodProperties COOKED_APPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.3f).build();
	public static final FoodProperties SLICED_BREAD = new FoodProperties.Builder().nutrition(1).saturationModifier(0.15f).build();
	public static final FoodProperties BREADING = new FoodProperties.Builder().nutrition(7).saturationModifier(0.23f).build();
	public static final FoodProperties MAYO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.08f).effect(HUNGER, 0.3F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties BBQ = new FoodProperties.Builder().nutrition(1).saturationModifier(0.06f).effect(HUNGER, 0.3F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties KETCHUP = new FoodProperties.Builder().nutrition(0).saturationModifier(0.05f).effect(HUNGER, 0.3F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties GRAVY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.11f).effect(REGENERATION_BRIEF, 1F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties JAM = new FoodProperties.Builder().nutrition(1).saturationModifier(0.02f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties GLOW_JAM = new FoodProperties.Builder().nutrition(1).saturationModifier(0.02f).effect(GLOW_SHORT, 1F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties GOLDEN_JAM = new FoodProperties.Builder().nutrition(3).saturationModifier(0.23f).effect(GOLDEN_APPLE_1, 1F).effect(GOLDEN_APPLE_2, 1F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties JAM_BREAD = new FoodProperties.Builder().nutrition(2).saturationModifier(0.08f).build();
	public static final FoodProperties GLOW_JAM_BREAD = new FoodProperties.Builder().nutrition(2).saturationModifier(0.08f).effect(GLOW_SHORT, 1F).build();
	public static final FoodProperties GOLDEN_JAM_BREAD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.19f).effect(GOLDEN_APPLE_1, 1F).effect(GOLDEN_APPLE_2, 1F).build();
	public static final FoodProperties SCRAMBLED_EGGS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).build();
	public static final FoodProperties OMELETTE = new FoodProperties.Builder().nutrition(12).saturationModifier(0.5f).build();
	public static final FoodProperties QUICHE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.13f).build();
	public static final FoodProperties EGG_BASKET = new FoodProperties.Builder().nutrition(5).saturationModifier(0.27f).build();
	public static final FoodProperties EGG_SALAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.07f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FRIED_POTATO = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
	public static final FoodProperties GLAZED_CARROT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.35f).build();
	public static final FoodProperties CARROT_SALAD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.13f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties APPLE_SAUCE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.08f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties POTATO_SOUP = new FoodProperties.Builder().nutrition(7).saturationModifier(0.18f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties TOMATO_SOUP = new FoodProperties.Builder().nutrition(7).saturationModifier(0.18f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FISH_SOUP = new FoodProperties.Builder().nutrition(8).saturationModifier(0.14f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CARROT_SOUP = new FoodProperties.Builder().nutrition(9).saturationModifier(0.24f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties SALISBURY_STEAK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.16f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MASHED_POTATO_GRAVY = new FoodProperties.Builder().nutrition(5).saturationModifier(0.1f).effect(COMFORT_MEDIUM, 1F).effect(REGENERATION_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PORK_STEW = new FoodProperties.Builder().nutrition(7).saturationModifier(0.13f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties LAMB_STEW = new FoodProperties.Builder().nutrition(7).saturationModifier(0.13f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CHICKEN_STEW = new FoodProperties.Builder().nutrition(7).saturationModifier(0.12f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CURRY = new FoodProperties.Builder().nutrition(7).saturationModifier(0.14f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties BEEF_STEW_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.6f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PORK_STEW_RICE = new FoodProperties.Builder().nutrition(13).saturationModifier(0.26f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties LAMB_STEW_RICE = new FoodProperties.Builder().nutrition(13).saturationModifier(0.26f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties RABBIT_STEW_RICE = new FoodProperties.Builder().nutrition(16).saturationModifier(0.5f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CHICKEN_STEW_RICE = new FoodProperties.Builder().nutrition(13).saturationModifier(0.26f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FISH_STEW_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.6f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CURRY_RICE = new FoodProperties.Builder().nutrition(13).saturationModifier(0.27f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties COOKED_PASTA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties BUTTERED_PASTA = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FRIED_FISH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.41f).build();
	public static final FoodProperties CHICKEN_FRIED_STEAK = new FoodProperties.Builder().nutrition(17).saturationModifier(0.38f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties PORK_TENDERLOIN = new FoodProperties.Builder().nutrition(10).saturationModifier(0.26f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties PORK_TENDERLOIN_SANDWICH = new FoodProperties.Builder().nutrition(15).saturationModifier(0.43f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationModifier(0.3f).build();
	public static final FoodProperties GRILLED_CHEESE = new FoodProperties.Builder().nutrition(12).saturationModifier(0.3f).build();
	public static final FoodProperties AGAR_AGAR_POWDER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
	public static final FoodProperties HASH = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).effect(COMFORT_BRIEF, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MEAT_LOAF = new FoodProperties.Builder().nutrition(5).saturationModifier(0.11f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MEAT_LOAF_SANDWICH = new FoodProperties.Builder().nutrition(10).saturationModifier(0f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_LONG, 1F).build();
	public static final FoodProperties MEAT_PIE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.12f).effect(COMFORT_MEDIUM, 1F).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties PULLED_PORK_SERVING = new FoodProperties.Builder().nutrition(10).saturationModifier(0.16f).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PULLED_PORK_SANDWICH = new FoodProperties.Builder().nutrition(15).saturationModifier(0.38f).effect(NOURISHMENT_MEDIUM, 1F).build();
	public static final FoodProperties STIRFRY = new FoodProperties.Builder().nutrition(7).saturationModifier(0.16f).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties STIRFRY_RICE = new FoodProperties.Builder().nutrition(13).saturationModifier(0.28f).effect(NOURISHMENT_MEDIUM, 1F).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties JELLY = new FoodProperties.Builder().nutrition(1).saturationModifier(0.03f).effect(JUMP_BRIEF, 0.5F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MACARONI_AND_CHEESE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.08f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties LASAGNA = new FoodProperties.Builder().nutrition(9).saturationModifier(0.12f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties JERKY = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();
	public static final FoodProperties SAUSAGE_ROLL = new FoodProperties.Builder().nutrition(11).saturationModifier(0.4f).build();
	public static final FoodProperties SOS = new FoodProperties.Builder().nutrition(12).saturationModifier(0.31f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties LIVERONION = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FRIED_CHICKEN = new FoodProperties.Builder().nutrition(13).saturationModifier(0.41f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties STUFFED_HEART = new FoodProperties.Builder().nutrition(16).saturationModifier(0.35f).build();
	public static final FoodProperties FRIED_BRAIN = new FoodProperties.Builder().nutrition(11).saturationModifier(0.21f).build();
	public static final FoodProperties OXTAIL_SOUP = new FoodProperties.Builder().nutrition(12).saturationModifier(0.3f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties POT_ROAST = new FoodProperties.Builder().nutrition(6).saturationModifier(0.11f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties BBQ_RIBS = new FoodProperties.Builder().nutrition(8).saturationModifier(0.16f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties RACK_LAMB = new FoodProperties.Builder().nutrition(7).saturationModifier(0.13f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties WELLINGTON = new FoodProperties.Builder().nutrition(7).saturationModifier(0.13f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties HAGGIS = new FoodProperties.Builder().nutrition(6).saturationModifier(0.09f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties HOTDISH = new FoodProperties.Builder().nutrition(10).saturationModifier(0.13f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FURIKAKE_RICE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).effect(DOLPHIN_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FISH_CAKES = new FoodProperties.Builder().nutrition(9).saturationModifier(0.22f).build();
	public static final FoodProperties FISH_CHIPS = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).build();
	public static final FoodProperties FRIED_MUSHROOMS = new FoodProperties.Builder().nutrition(5).saturationModifier(0.11f).build();
	public static final FoodProperties MUSHROOM_RISOTTO = new FoodProperties.Builder().nutrition(12).saturationModifier(0.23f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties STUFFED_MUSHROOMS = new FoodProperties.Builder().nutrition(5).saturationModifier(0.12f).build();
	public static final FoodProperties MUSHROOM_BURGER = new FoodProperties.Builder().nutrition(15).saturationModifier(0.43f).build();
	public static final FoodProperties BACON_EGG_SANDWICH = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();
	public static final FoodProperties BACON_EGG_CHEESE_SANDWICH = new FoodProperties.Builder().nutrition(16).saturationModifier(0.6f).build();
	public static final FoodProperties BUTTERED_TOAST = new FoodProperties.Builder().nutrition(3).saturationModifier(0.32f).build();
	public static final FoodProperties SALAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.08f).effect(NOURISHMENT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties ALFREDO_SAUCE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.33f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PASTA_TOMATO = new FoodProperties.Builder().nutrition(10).saturationModifier(0.35f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PASTA_ALFREDO = new FoodProperties.Builder().nutrition(14).saturationModifier(0.31f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CHICKEN_ALFREDO = new FoodProperties.Builder().nutrition(20).saturationModifier(0.45f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CHICKEN_PARM = new FoodProperties.Builder().nutrition(10).saturationModifier(0.2f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_BRIEF, 1F).build();
	public static final FoodProperties CHEESEBURGER = new FoodProperties.Builder().nutrition(15).saturationModifier(0.6f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties BACON_CHEESEBURGER = new FoodProperties.Builder().nutrition(19).saturationModifier(0.7f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties RICEBALL = new FoodProperties.Builder().nutrition(7).saturationModifier(0.35f).effect(DOLPHIN_BRIEF, 1F).build();
	public static final FoodProperties RICEBALL_FILLED = new FoodProperties.Builder().nutrition(11).saturationModifier(0.37f).effect(DOLPHIN_BRIEF, 1F).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties FISH_SALAD = new FoodProperties.Builder().nutrition(12).saturationModifier(0.42f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FISH_SALAD_SANDWICH = new FoodProperties.Builder().nutrition(17).saturationModifier(0.51f).build();
	public static final FoodProperties SEAWEED_CRISPS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f).effect(DOLPHIN_BRIEF, 1F).build();
	public static final FoodProperties SEAWEED_SALAD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).effect(DOLPHIN_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties COOKED_CACTUS = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2f).build();
	public static final FoodProperties CACTUS_EGGS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.2f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CACTUS_SOUP = new FoodProperties.Builder().nutrition(11).saturationModifier(0.18f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CACTUS_SALAD = new FoodProperties.Builder().nutrition(13).saturationModifier(0.32f).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties STUFFED_CACTUS = new FoodProperties.Builder().nutrition(17).saturationModifier(0.27f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_BRIEF, 1F).build();
	public static final FoodProperties CORN_CHOWDER = new FoodProperties.Builder().nutrition(11).saturationModifier(0.23f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CREAMED_CORN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.26f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CORN_FRITTERS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.25f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties STEWED_APPLES = new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties APPLE_FRITTERS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.21f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CARAMEL_SAUCE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.23f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties CARAMEL_CANDY = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CANDY = new FoodProperties.Builder().nutrition(0).saturationModifier(0.02f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CANDY_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.16f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CARAMEL_APPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.26f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CANDY_GOLDEN_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.46f).effect(GOLDEN_APPLE_1, 1F).effect(GOLDEN_APPLE_2, 1F).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CARAMEL_GOLDEN_APPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.71f).effect(GOLDEN_APPLE_1, 1F).effect(GOLDEN_APPLE_2, 1F).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CARAMEL_POPCORN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.21f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CORNBREAD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.14f).effect(COMFORT_BRIEF, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties APPLE_CRISP = new FoodProperties.Builder().nutrition(6).saturationModifier(0.13f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties STUFFING = new FoodProperties.Builder().nutrition(5).saturationModifier(0.14f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CORN_PUDDING = new FoodProperties.Builder().nutrition(5).saturationModifier(0.12f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties POTATOES_AU_GRATIN = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties FROSTING = new FoodProperties.Builder().nutrition(0).saturationModifier(0.05f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties CANDY_CANE = new FoodProperties.Builder().nutrition(0).saturationModifier(0.1f).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties FRENCH_TOAST = new FoodProperties.Builder().nutrition(2).saturationModifier(0.05f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CONGEE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.25f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties LUGAW = new FoodProperties.Builder().nutrition(13).saturationModifier(0.23f).effect(COMFORT_LONG, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MUFFIN = new FoodProperties.Builder().nutrition(1).saturationModifier(0.04f).build();
	public static final FoodProperties FILLED_BREAD = new FoodProperties.Builder().nutrition(9).saturationModifier(0.45f).build();
	public static final FoodProperties PICKLED_GINGER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).alwaysEdible().build();
	public static final FoodProperties BEET_MINT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.37f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CRACKER = new FoodProperties.Builder().nutrition(0).saturationModifier(0.08f).alwaysEdible().build();
	public static final FoodProperties CROQUE_MONSIEUR = new FoodProperties.Builder().nutrition(9).saturationModifier(0.17f).effect(COMFORT_BRIEF, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CROQUE_MADAME = new FoodProperties.Builder().nutrition(13).saturationModifier(0.28f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties ROLL = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
	public static final FoodProperties CINNAMON_ROLL = new FoodProperties.Builder().nutrition(3).saturationModifier(0.09f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties MONKEY_BREAD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.11f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MINT_LAMB = new FoodProperties.Builder().nutrition(15).saturationModifier(0.33f).effect(COMFORT_BRIEF, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties CHARCUTERIE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).effect(NOURISHMENT_BRIEF, 1F).usingConvertsTo(Items.BOWL).alwaysEdible().build();
	public static final FoodProperties CHRISTMAS_PUDDING = new FoodProperties.Builder().nutrition(3).saturationModifier(0.06f).effect(COMFORT_BRIEF, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MILK_TART = new FoodProperties.Builder().nutrition(2).saturationModifier(0.06f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties CHOCOLATE_BAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.55f).effect(ABSORPTION_SHORT, 1F).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties CHOCOLATE_TRUFFLE = new FoodProperties.Builder().nutrition(0).saturationModifier(0.09f).effect(ABSORPTION_SHORT, 1F).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties FILLED_CHOCOLATE_BAR = new FoodProperties.Builder().nutrition(6).saturationModifier(0.77f).effect(ABSORPTION_SHORT, 1F).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties CHOCOLATE_CHIPS = new FoodProperties.Builder().nutrition(0).saturationModifier(0.11f).effect(ABSORPTION_SHORT, 1F).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties MARSHMALLOW = new FoodProperties.Builder().nutrition(1).saturationModifier(0.05f).build();
	public static final FoodProperties CHILI = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).effect(FIRE_RESISTANCE_BRIEF, 1F).build();
	public static final FoodProperties TOFFEE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
	public static final FoodProperties GRAHAM_CRACKER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.05f).build();
	public static final FoodProperties SMORE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.21f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties PEPPERMINT_BARK = new FoodProperties.Builder().nutrition(2).saturationModifier(0.08f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties DIRT_CAKE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.24f).effect(COMFORT_MEDIUM, 1F).effect(SPEED_BRIEF, 1F).build();
	public static final FoodProperties TRAIL_MIX = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).effect(NOURISHMENT_BRIEF, 1F).build();
	public static final FoodProperties NOUGAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
	public static final FoodProperties GUMMIES = new FoodProperties.Builder().nutrition(1).saturationModifier(0.03f).effect(SPEED_BRIEF, 1F).build();
	public static final FoodProperties PEANUT_BRITTLE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
	public static final FoodProperties BUTTERSCOTCH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.15f).build();
	public static final FoodProperties FLUFFER_NUTTER = new FoodProperties.Builder().nutrition(8).saturationModifier(0.55f).build();
	public static final FoodProperties COFFEE_BEANS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).effect(SPEED_BRIEF, 1F).build();
	public static final FoodProperties ROASTED_COFFEE_BEANS = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).effect(SPEED_MEDIUM, 1F).build();
	public static final FoodProperties BROWNIE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.08f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties BLONDIE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.06f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties FUDGE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.06f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties STICKY_TOFFEE_PUDDING = new FoodProperties.Builder().nutrition(3).saturationModifier(0.06f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties CEREAL = new FoodProperties.Builder().nutrition(8).saturationModifier(0.25f).effect(REGENERATION_BRIEF, 1F).effect(ABSORPTION_SHORT, 1F).build();
	public static final FoodProperties PUFFED_RICE_TREAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.05f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties SCOTCHAROO = new FoodProperties.Builder().nutrition(3).saturationModifier(0.09f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties BLACK_FOREST_TRIFLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.05f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties DIPPED_SWEET_BERRY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).effect(COMFORT_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties DIPPED_GLOW_BERRY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).effect(COMFORT_BRIEF, 1F).effect(GLOW_SHORT, 1F).alwaysEdible().build();
	public static final FoodProperties DIPPED_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2f).effect(COMFORT_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties DIPPED_MARSHMALLOW = new FoodProperties.Builder().nutrition(1).saturationModifier(0.08f).effect(COMFORT_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties DIPPED_GRAHAM_CRACKER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.08f).effect(COMFORT_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties DIPPED_BACON = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).effect(COMFORT_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties NUTS = new FoodProperties.Builder().nutrition(2).saturationModifier(1f).build();
	public static final FoodProperties PBJ = new FoodProperties.Builder().nutrition(8).saturationModifier(0.54f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).build();
	public static final FoodProperties ICE_CREAM_SUNDAE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.37f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties HAZELNUT_SPREAD = new FoodProperties.Builder().nutrition(2).saturationModifier(0.55f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties ROASTED_NUTS = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();
	public static final FoodProperties DIPPED_COFFEE_BEAN = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).effect(COMFORT_BRIEF, 1F).effect(SPEED_BRIEF, 1F).alwaysEdible().build();
	public static final FoodProperties ROMBOSSE = new FoodProperties.Builder().nutrition(9).saturationModifier(0.45f).effect(COMFORT_MEDIUM, 1F).build();
	public static final FoodProperties APPLE_SLAW = new FoodProperties.Builder().nutrition(7).saturationModifier(0.19f).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties PORK_AND_APPLES = new FoodProperties.Builder().nutrition(9).saturationModifier(0.33f).effect(COMFORT_MEDIUM, 1F).effect(NOURISHMENT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties APPLE_CHIPS = new FoodProperties.Builder().nutrition(1).saturationModifier(0.03f).build();
	public static final FoodProperties STUFFED_APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties STUFFED_APPLE_ICE_CREAM = new FoodProperties.Builder().nutrition(12).saturationModifier(0.23f).effect(COMFORT_LONG, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MULLIGATAWNY_SOUP = new FoodProperties.Builder().nutrition(11).saturationModifier(0.21f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties TARTE_TATIN_SLICE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.07f).effect(COMFORT_BRIEF, 1F).build();
	public static final FoodProperties AEBLEFLAESK = new FoodProperties.Builder().nutrition(8).saturationModifier(0.27f).effect(NOURISHMENT_MEDIUM, 1F).build();
	public static final FoodProperties CANDY_BAR_SALAD = new FoodProperties.Builder().nutrition(8).saturationModifier(0.31f).effect(COMFORT_MEDIUM, 1F).usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties MARSHMALLOW_FLUFF = new FoodProperties.Builder().nutrition(2).saturationModifier(0.12f).usingConvertsTo(Items.GLASS_BOTTLE).build();
	public static final FoodProperties NUT_BUTTER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f).usingConvertsTo(Items.GLASS_BOTTLE).build();
}