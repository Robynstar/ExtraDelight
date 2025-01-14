package com.lance5057.extradelight;

import com.lance5057.extradelight.aesthetics.AestheticBlocks;
import com.lance5057.extradelight.blocks.*;
import com.lance5057.extradelight.blocks.chocolatebox.ChocolateBoxBlock;
import com.lance5057.extradelight.blocks.crops.ChiliCrop;
import com.lance5057.extradelight.blocks.crops.CoffeeBush;
import com.lance5057.extradelight.blocks.crops.GingerCrop;
import com.lance5057.extradelight.blocks.crops.MallowRootCrop;
import com.lance5057.extradelight.blocks.crops.MintCrop;
import com.lance5057.extradelight.blocks.crops.PeanutCrop;
import com.lance5057.extradelight.blocks.crops.corn.CornBottom;
import com.lance5057.extradelight.blocks.crops.corn.CornTop;
import com.lance5057.extradelight.blocks.fluids.GlowBerryFluidBlock;
import com.lance5057.extradelight.blocks.fluids.HotFluidBlock;
import com.lance5057.extradelight.blocks.fluids.VinegarFluidBlock;
import com.lance5057.extradelight.blocks.funnel.FunnelBlock;
import com.lance5057.extradelight.blocks.jar.JarBlock;
import com.lance5057.extradelight.blocks.keg.KegBlock;
import com.lance5057.extradelight.data.MiscLootTables;
import com.lance5057.extradelight.displays.candybowl.CandyBowlBlock;
import com.lance5057.extradelight.displays.food.FoodDisplayBlock;
import com.lance5057.extradelight.workstations.chiller.ChillerBlock;
import com.lance5057.extradelight.workstations.doughshaping.DoughShapingBlock;
import com.lance5057.extradelight.workstations.dryingrack.DryingRackBlock;
import com.lance5057.extradelight.workstations.meltingpot.MeltingPotBlock;
import com.lance5057.extradelight.workstations.mixingbowl.MixingBowlBlock;
import com.lance5057.extradelight.workstations.mortar.MortarBlock;
import com.lance5057.extradelight.workstations.oven.OvenBlock;
import com.lance5057.extradelight.worldgen.features.trees.ExtraDelightTreeGrowers;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.CabinetBlock;
//import vectorwing.farmersdelight.common.block.CabinetBlock;
//import vectorwing.farmersdelight.common.block.PieBlock;
//import vectorwing.farmersdelight.common.block.WildCropBlock;
//import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class ExtraDelightBlocks {

	final static VoxelShape bowl = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D);
	final static VoxelShape plate = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
	final static VoxelShape pan = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);
	final static VoxelShape pot = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
	final static VoxelShape stand = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 4.0D, 13.0D);
	final static VoxelShape fondue = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExtraDelight.MOD_ID);

	public static void register(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);

	}

	public static void setup() {
		FireBlock fire = (FireBlock) Blocks.FIRE;

		fire.setFlammable(ExtraDelightBlocks.CINNAMON_DOOR.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_FENCE.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_FENCE_GATE.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_LEAVES.get(), 30, 60);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_LOG.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_PLANKS.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_SLAB.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_STAIRS.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_STICK_BLOCK.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_TRAPDOOR.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.CINNAMON_WOOD.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.RAW_CINNAMON_BLOCK.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.STRIPPED_CINNAMON_LOG.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.STRIPPED_CINNAMON_WOOD.get(), 5, 5);

		for (int i = 0; i < AestheticBlocks.DRIED_CORN_FENCE.size(); i++)
			fire.setFlammable(AestheticBlocks.DRIED_CORN_FENCE.get(i).get(), 5, 5);

		for (int i = 0; i < AestheticBlocks.WREATHS.size(); i++)
			fire.setFlammable(AestheticBlocks.WREATHS.get(i).get(), 5, 5);

		fire.setFlammable(ExtraDelightBlocks.FRUIT_DOOR.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_FENCE.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_FENCE_GATE.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_LOG.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_PLANKS.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_SLAB.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_STAIRS.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_TRAPDOOR.get(), 5, 20);
		fire.setFlammable(ExtraDelightBlocks.FRUIT_WOOD.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.STRIPPED_FRUIT_LOG.get(), 5, 5);
		fire.setFlammable(ExtraDelightBlocks.STRIPPED_FRUIT_WOOD.get(), 5, 5);

		fire.setFlammable(ExtraDelightBlocks.HAZELNUT_LEAVES.get(), 30, 60);
		fire.setFlammable(ExtraDelightBlocks.CORN_COB_BUNDLE.get(), 30, 60);
		fire.setFlammable(ExtraDelightBlocks.CORN_HUSK_BUNDLE.get(), 30, 60);
		fire.setFlammable(ExtraDelightBlocks.DRIED_CORN_HUSK_BUNDLE.get(), 300, 600);

		fire.setFlammable(ExtraDelightBlocks.MINT_CROP.get(), 1000, 1000);

		fire.setFlammable(ExtraDelightBlocks.APPLE_LEAVES.get(), 30, 60);
	}

	public static final DeferredBlock<Block> OVEN = BLOCKS.register("oven", OvenBlock::new);
	public static final DeferredBlock<DryingRackBlock> DRYING_RACK = BLOCKS.register("drying_rack",
			DryingRackBlock::new);
	public static final DeferredBlock<Block> FOOD_DISPLAY = BLOCKS.register("food_display", FoodDisplayBlock::new);
	public static final DeferredBlock<Block> DOUGH_SHAPING = BLOCKS.register("dough_shaping", DoughShapingBlock::new);
	public static final DeferredBlock<Block> MIXING_BOWL = BLOCKS.register("mixing_bowl", MixingBowlBlock::new);

	public static final DeferredBlock<Block> YEAST_POT = BLOCKS.register("yeast_pot",
			() -> new YeastPotBlock(ExtraDelightItems.YEAST, 4));
	public static final DeferredBlock<Block> VINEGAR_POT = BLOCKS.register("vinegar_pot",
			() -> new YeastPotBlock(ExtraDelightItems.VINEGAR, 8));

	public static final DeferredBlock<Block> MORTAR_STONE = BLOCKS.register("mortar_stone", () -> new MortarBlock());

	public static final DeferredBlock<Block> FLOUR = BLOCKS.register("flour", () -> new CarpetBlock(
			Properties.ofFullCopy(Blocks.MOSS_BLOCK).strength(0.1F, 0.0F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> COOKING_OIL = BLOCKS.register("cooking_oil",
			() -> new CarpetBlock(Properties.ofFullCopy(Blocks.YELLOW_CARPET).strength(0.1F, 0.0F)
					.sound(SoundType.SLIME_BLOCK).noOcclusion().friction(0.98f)));
	public static final DeferredBlock<Block> BREADCRUMBS = BLOCKS.register("breadcrumbs", () -> new CarpetBlock(
			Properties.ofFullCopy(Blocks.MOSS_BLOCK).strength(0.1F, 0.0F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> CORNMEAL = BLOCKS.register("cornmeal", () -> new CarpetBlock(
			Properties.ofFullCopy(Blocks.MOSS_BLOCK).strength(0.1F, 0.0F).sound(SoundType.WOOL).noOcclusion()));

	public static final DeferredBlock<Block> SWEET_BERRY_PIE = ExtraDelightBlocks.BLOCKS.register("sweet_berry_pie",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.SWEET_BERRY_PIE_SLICE));
	public static final DeferredBlock<Block> GLOW_BERRY_PIE = ExtraDelightBlocks.BLOCKS.register("glow_berry_pie",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.GLOW_BERRY_PIE_SLICE));
	public static final DeferredBlock<Block> CHEESECAKE = ExtraDelightBlocks.BLOCKS.register("cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.CHEESECAKE_SLICE));
	public static final DeferredBlock<Block> HONEY_CHEESECAKE = ExtraDelightBlocks.BLOCKS.register("honey_cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.HONEY_CHEESECAKE_SLICE));
	public static final DeferredBlock<Block> CHOCOLATE_CHEESECAKE = ExtraDelightBlocks.BLOCKS.register(
			"chocolate_cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.CHOCOLATE_CHEESECAKE_SLICE));
	public static final DeferredBlock<Block> PUMPKIN_CHEESECAKE = ExtraDelightBlocks.BLOCKS.register(
			"pumpkin_cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.PUMPKIN_CHEESECAKE_SLICE));
	public static final DeferredBlock<Block> GLOW_BERRY_CHEESECAKE = ExtraDelightBlocks.BLOCKS
			.register("glow_berry_cheesecake", () -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE),
					ExtraDelightItems.GLOW_BERRY_CHEESECAKE_SLICE));

	public static final DeferredBlock<Block> QUICHE = ExtraDelightBlocks.BLOCKS.register("quiche",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.QUICHE_SLICE));

	// Feasts
	public static final DeferredBlock<RecipeFeastBlock> SALISBURY_STEAK_FEAST = BLOCKS.register("salisbury_steak_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, bowl));
	public static final DeferredBlock<RecipeFeastBlock> MASHED_POTATO_GRAVY = BLOCKS.register(
			"mashed_potato_gravy_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, bowl));

	public static final DeferredBlock<RecipeFeastBlock> HASH_FEAST = BLOCKS.register("hash_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, bowl));
	public static final DeferredBlock<RecipeFeastBlock> POT_ROAST_FEAST = BLOCKS.register("potroast_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(5.0D, 2.0D, 2.0D, 11.0D, 8.0D, 14.0D),
					Block.box(2.0D, 2.0D, 5.0D, 14.0D, 8.0D, 11.0D)));

	public static final DeferredBlock<RecipeFeastBlock> MEATLOAF_FEAST = BLOCKS.register("meatloaf_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(5.0D, 2.0D, 3.0D, 11.0D, 8.0D, 13.0D),
					Block.box(3.0D, 2.0D, 5.0D, 13.0D, 8.0D, 11.0D)));

	public static final DeferredBlock<RecipeFeastBlock> BBQ_RIBS_FEAST = BLOCKS.register("bbq_ribs_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 6.0D, 14.0D)));
	public static final DeferredBlock<RecipeFeastBlock> PULLED_PORK_FEAST = BLOCKS.register("pulled_pork_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, bowl));

	public static final DeferredBlock<RecipeFeastBlock> RACK_LAMB = BLOCKS.register("rack_lamb_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(4.0D, 2.0D, 4.0D, 12.0D, 8.0D, 12.0D)));
	public static final DeferredBlock<RecipeFeastBlock> STIRFRY = BLOCKS.register("stirfry_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, bowl));
	public static final DeferredBlock<RecipeFeastBlock> BEEF_WELLINGTON = BLOCKS.register("beef_wellington_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(4.5D, 2.0D, 2.0D, 11.5D, 9.0D, 14.0D),
					Block.box(2.0D, 2.0D, 4.5D, 14.0D, 9.0D, 11.5D)));
	public static final DeferredBlock<RecipeFeastBlock> HAGGIS = BLOCKS.register("haggis_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(4.0D, 2.0D, 4.0D, 12.0D, 10.0D, 12.0D)));

	public static final DeferredBlock<JellyBlock> JELLY_WHITE = BLOCKS.register("jelly_white_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_WHITE),
					ExtraDelightItems.JELLY_WHITE::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_ORANGE = BLOCKS.register("jelly_orange_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_ORANGE),
					ExtraDelightItems.JELLY_ORANGE::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_MAGENTA = BLOCKS.register("jelly_magenta_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_MAGENTA),
					ExtraDelightItems.JELLY_MAGENTA::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_LIGHT_BLUE = BLOCKS.register("jelly_light_blue_block",
			() -> new JellyBlock(
					Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE),
					ExtraDelightItems.JELLY_LIGHT_BLUE::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_YELLOW = BLOCKS.register("jelly_yellow_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_YELLOW),
					ExtraDelightItems.JELLY_YELLOW::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_LIME = BLOCKS.register("jelly_lime_block",
			() -> new JellyBlock(
					Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN),
					ExtraDelightItems.JELLY_LIME::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_PINK = BLOCKS.register("jelly_pink_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_PINK),
					ExtraDelightItems.JELLY_PINK::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_GREY = BLOCKS.register("jelly_grey_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_GRAY),
					ExtraDelightItems.JELLY_GREY::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_LIGHT_GREY = BLOCKS.register("jelly_light_grey_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_LIGHT_GRAY),
					ExtraDelightItems.JELLY_LIGHT_GREY::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_CYAN = BLOCKS.register("jelly_cyan_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_CYAN),
					ExtraDelightItems.JELLY_CYAN::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_PURPLE = BLOCKS.register("jelly_purple_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_PURPLE),
					ExtraDelightItems.JELLY_PURPLE::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_BLUE = BLOCKS.register("jelly_blue_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_BLUE),
					ExtraDelightItems.JELLY_BLUE::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_BROWN = BLOCKS.register("jelly_brown_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_BROWN),
					ExtraDelightItems.JELLY_BROWN::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_GREEN = BLOCKS.register("jelly_green_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_GREEN),
					ExtraDelightItems.JELLY_GREEN::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_RED = BLOCKS.register("jelly_red_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_RED),
					ExtraDelightItems.JELLY_RED::get, true));
	public static final DeferredBlock<JellyBlock> JELLY_BLACK = BLOCKS.register("jelly_black_block",
			() -> new JellyBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_BLACK),
					ExtraDelightItems.JELLY_BLACK::get, true));

	public static final DeferredBlock<PieBlock> MEAT_PIE_BLOCK = BLOCKS.register("meat_pie",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE).mapColor(MapColor.COLOR_BROWN),
					ExtraDelightItems.MEAT_PIE_SLICE));

	public static final DeferredBlock<Block> CHEESE_BLOCK = BLOCKS.register("cheese_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).sound(SoundType.SLIME_BLOCK)
					.mapColor(MapColor.COLOR_ORANGE)));
	public static final DeferredBlock<StairBlock> CHEESE_STAIRS_BLOCK = BLOCKS.register("cheese_stairs_block",
			() -> new StairBlock(CHEESE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.SPONGE).mapColor(MapColor.COLOR_ORANGE).sound(SoundType.SLIME_BLOCK)));
	public static final DeferredBlock<SlabBlock> CHEESE_SLAB_BLOCK = BLOCKS.register("cheese_slab_block",
			() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).mapColor(MapColor.COLOR_ORANGE)
					.sound(SoundType.SLIME_BLOCK)));

	public static final DeferredBlock<Block> BUTTER_BLOCK = BLOCKS.register("butter_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE).mapColor(MapColor.COLOR_YELLOW)
					.friction(0.98F).sound(SoundType.SLIME_BLOCK)));
	public static final DeferredBlock<StairBlock> BUTTER_STAIRS_BLOCK = BLOCKS.register("butter_stairs_block",
			() -> new StairBlock(CHEESE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.SPONGE).mapColor(MapColor.COLOR_YELLOW).sound(SoundType.SLIME_BLOCK)));
	public static final DeferredBlock<SlabBlock> BUTTER_SLAB_BLOCK = BLOCKS.register("butter_slab_block",
			() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).mapColor(MapColor.COLOR_YELLOW)
					.sound(SoundType.SLIME_BLOCK)));

	public static final DeferredBlock<RecipeFeastBlock> MACARONI_CHEESE = BLOCKS.register("macaroni_cheese_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_YELLOW), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> LASAGNA = BLOCKS.register("lasagna_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_YELLOW), true, pan));
	public static final DeferredBlock<RecipeFeastBlock> HOTDISH = BLOCKS.register("hotdish_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_YELLOW), true, pan));

	// Stew
	public static final DeferredBlock<RecipeFeastBlock> CURRY = BLOCKS.register("curry_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_ORANGE), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> BEEF_STEW = BLOCKS.register("beef_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> PORK_STEW = BLOCKS.register("pork_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> LAMB_STEW = BLOCKS.register("lamb_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> RABBIT_STEW = BLOCKS.register("rabbit_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> CHICKEN_STEW = BLOCKS.register("chicken_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));
	public static final DeferredBlock<RecipeFeastBlock> FISH_STEW = BLOCKS.register("fish_stew_block",
			() -> new RecipeFeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(0.8F)
					.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BROWN), true, pot));

	public static final DeferredBlock<RecipeFeastBlock> SALAD = BLOCKS.register("salad_block",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_GREEN),
					true, Block.box(3.5D, 0.0D, 2.0D, 14.5D, 4.0D, 13.0D)));

//Halloween Start
	public static final DeferredBlock<CornBottom> CORN_BOTTOM = BLOCKS.register("corn_bottom",
			() -> new CornBottom(Block.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.COLOR_GREEN)
					.offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<CornTop> CORN_TOP = BLOCKS.register("corn_top", () -> new CornTop(Block.Properties
			.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.COLOR_GREEN).offsetType(BlockBehaviour.OffsetType.XZ)));

	public static final DeferredBlock<RecipeFeastBlock> CORNBREAD = BLOCKS.register("cornbread",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_YELLOW),
					true, pan));

	public static final DeferredBlock<Block> CARAMEL_CHEESECAKE = ExtraDelightBlocks.BLOCKS.register(
			"caramel_cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.CARAMEL_CHEESECAKE_SLICE));

	public static final DeferredBlock<Block> PUMPKIN_PIE = ExtraDelightBlocks.BLOCKS.register("pumpkin_pie",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.PUMPKIN_PIE_SLICE));

	public static final DeferredBlock<RecipeFeastBlock> CORN_PUDDING = BLOCKS.register("corn_pudding",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_YELLOW),
					true, pan));

	public static final DeferredBlock<PieBlock> PUMPKIN_ROLL = BLOCKS.register("pumpkin_roll",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					ExtraDelightItems.PUMPKIN_ROLL));

	public static final DeferredBlock<RecipeFeastBlock> APPLE_CRISP = BLOCKS.register("apple_crisp",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));

	public static final DeferredBlock<RecipeFeastBlock> STUFFING = BLOCKS.register("stuffing",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pot));

	public static final DeferredBlock<RecipeFeastBlock> POTATO_AU_GRATIN = BLOCKS.register("potato_au_gratin",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));

	public static final DeferredBlock<Block> FLOUR_SACK = BLOCKS.register("flour_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> CORNMEAL_SACK = BLOCKS.register("cornmeal_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> SUGAR_SACK = BLOCKS.register("sugar_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> CORN_SACK = BLOCKS.register("corn_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> CORN_SILK_SACK = BLOCKS.register("corn_silk_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> CORN_CRATE = BLOCKS.register("corn_crate", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_YELLOW)));
	public static final DeferredBlock<HayBlock> CORN_HUSK_BUNDLE = BLOCKS.register("corn_husk_bundle",
			() -> new HayBlock(
					Block.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)));
	public static final DeferredBlock<HayBlock> DRIED_CORN_HUSK_BUNDLE = BLOCKS.register("dried_corn_husk_bundle",
			() -> new HayBlock(Block.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_YELLOW)));
	public static final DeferredBlock<HayBlock> CORN_COB_BUNDLE = BLOCKS.register("corn_cob_bundle",
			() -> new HayBlock(Block.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.TERRACOTTA_YELLOW)));
	public static final DeferredBlock<Block> BREADCRUMB_SACK = BLOCKS.register("breadcrumb_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> EGG_CRATE = BLOCKS.register("egg_crate", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_YELLOW)));
	public static final DeferredBlock<Block> APPLE_CRATE = BLOCKS.register("apple_crate",
			() -> new Block(Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_RED)));

	// Winter start!
	public static final DeferredBlock<SaplingBlock> CINNAMON_SAPLING = BLOCKS.register("cinnamon_sapling",
			() -> new SaplingBlock(ExtraDelightTreeGrowers.CINNAMON,
					Block.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CINNAMON_LOG = BLOCKS.register(
			"stripped_cinnamon_log", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CINNAMON_WOOD = BLOCKS.register(
			"stripped_cinnamon_wood", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> CINNAMON_WOOD = BLOCKS.register("cinnamon_wood",
			() -> new StrippableLog(STRIPPED_CINNAMON_WOOD.get(), MiscLootTables.CINNAMON_LOG,
					Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<StrippableLog> CINNAMON_LOG = BLOCKS.register("cinnamon_log",
			() -> new StrippableLog(STRIPPED_CINNAMON_LOG.get(), MiscLootTables.CINNAMON_LOG,
					Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<Block> CINNAMON_PLANKS = BLOCKS.register("cinnamon_planks",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<FenceBlock> CINNAMON_FENCE = BLOCKS.register("cinnamon_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> CINNAMON_FENCE_GATE = BLOCKS.register("cinnamon_fence_gate",
			() -> new FenceGateBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> CINNAMON_STAIRS = BLOCKS.register("cinnamon_stairs",
			() -> new StairBlock(CINNAMON_PLANKS.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> CINNAMON_DOOR = BLOCKS.register("cinnamon_door",
			() -> new DoorBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> CINNAMON_TRAPDOOR = BLOCKS.register("cinnamon_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<LeavesBlock> CINNAMON_LEAVES = BLOCKS.register("cinnamon_leaves",
			() -> new LeavesBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LEAVES)));
	public static final DeferredBlock<Block> CINNAMON_CABINET = BLOCKS.register("cinnamon_cabinet",
			() -> new CabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
	public static final DeferredBlock<SlabBlock> CINNAMON_SLAB = BLOCKS.register("cinnamon_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
	public static final DeferredBlock<PressurePlateBlock> CINNAMON_PRESSURE_PLATE = BLOCKS.register(
			"cinnamon_pressure_plate",
			() -> new PressurePlateBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<ButtonBlock> CINNAMON_BUTTON = BLOCKS.register("cinnamon_button",
			() -> new ButtonBlock(BlockSetType.OAK, 30, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<RotatedPillarBlock> CINNAMON_STICK_BLOCK = BLOCKS.register("cinnamon_stick_block",
			() -> new RotatedPillarBlock(
					Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> GROUND_CINNAMON_SACK = BLOCKS.register("ground_cinnamon_sack",
			() -> new Block(
					Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> RAW_CINNAMON_BLOCK = BLOCKS.register("raw_cinnamon_block", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_BROWN)));

	public static final DeferredBlock<Block> APPLE_CHEESECAKE = ExtraDelightBlocks.BLOCKS.register("apple_cheesecake",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.APPLE_CHEESECAKE_SLICE));

	public static final DeferredBlock<Block> SUGAR_COOKIE_BLOCK = BLOCKS.register("sugar_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> CHOCOLATE_CHIP_COOKIE_BLOCK = BLOCKS.register(
			"chocolate_chip_cookie_block", () -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> APPLE_COOKIE_BLOCK = BLOCKS.register("apple_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> HONEY_COOKIE_BLOCK = BLOCKS.register("honey_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> SWEET_BERRY_COOKIE_BLOCK = BLOCKS.register("sweet_berry_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> GLOW_BERRY_COOKIE_BLOCK = BLOCKS.register("glow_berry_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> PUMPKIN_COOKIE_BLOCK = BLOCKS.register("pumpkin_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<Block> GINGERBREAD_COOKIE_BLOCK = BLOCKS.register("gingerbread_cookie_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<GingerCrop> GINGER_CROP = BLOCKS.register("ginger_crop",
			() -> new GingerCrop(Block.Properties.ofFullCopy(Blocks.WHEAT)));
	public static final DeferredBlock<Block> GINGER_CRATE = BLOCKS.register("ginger_crate", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_BROWN)));
	public static final DeferredBlock<Block> WILD_GINGER = BLOCKS.register("wild_ginger",
			() -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 6, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));
	public static final DeferredBlock<MintCrop> MINT_CROP = BLOCKS.register("mint_crop", MintCrop::new);
	public static final DeferredBlock<Block> MINT_SACK = BLOCKS.register("mint_sack",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> GOLDEN_APPLE_CRATE = BLOCKS.register("golden_apple_crate", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_BROWN)));
	public static final DeferredBlock<Block> BROWN_MUSHROOM_CRATE = BLOCKS.register("brown_mushroom_crate",
			() -> new Block(
					Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_BROWN)));
	public static final DeferredBlock<Block> RED_MUSHROOM_CRATE = BLOCKS.register("red_mushroom_crate",
			() -> new Block(Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_RED)));
	public static final DeferredBlock<Block> SWEET_BERRY_CRATE = BLOCKS.register("sweet_berry_crate",
			() -> new Block(Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_RED)));
	public static final DeferredBlock<Block> GLOW_BERRY_CRATE = BLOCKS.register("glow_berry_crate", () -> new Block(
			Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.COLOR_YELLOW)));

	public static final DeferredBlock<CandyBowlBlock> CANDY_BOWL = BLOCKS.register("candy_bowl",
			() -> new CandyBowlBlock(Block.Properties.ofFullCopy(Blocks.GLASS)));

	public static DeferredBlock<FrostableBlock> WHITE_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"white_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> LIGHT_GRAY_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("light_gray_frosted_gingerbread_block",
					() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> GRAY_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"gray_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> BLACK_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"black_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> BROWN_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"brown_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> RED_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"red_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> ORANGE_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"orange_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> YELLOW_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"yellow_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> LIME_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"lime_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> GREEN_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"green_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> CYAN_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"cyan_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> LIGHT_BLUE_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS
			.register("light_blue_frosted_gingerbread_block",
					() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> BLUE_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"blue_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> PURPLE_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"purple_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> MAGENTA_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"magenta_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static DeferredBlock<FrostableBlock> PINK_FROSTED_GINGERBREAD_BLOCK = ExtraDelightBlocks.BLOCKS.register(
			"pink_frosted_gingerbread_block",
			() -> new FrostableBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<Block> CANDY_CANE_RED_BLOCK = BLOCKS.register("candy_cane_red_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> CANDY_CANE_GREEN_BLOCK = BLOCKS.register("candy_cane_green_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> CANDY_CANE_BLUE_BLOCK = BLOCKS.register("candy_cane_blue_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.STONE)));

	public static final DeferredBlock<RecipeFeastBlock> CINNAMON_ROLLS = BLOCKS.register("cinnamon_rolls",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));
	public static final DeferredBlock<RecipeFeastBlock> MONKEY_BREAD = BLOCKS.register("monkey_bread",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D),
					Block.box(3.0D, 1.0D, 3.0D, 13.0D, 5.0D, 13.0D)));
	public static final DeferredBlock<Block> COFFEE_CAKE = ExtraDelightBlocks.BLOCKS.register("coffee_cake",
			() -> new CakeBlock(Block.Properties.ofFullCopy(Blocks.CAKE)));
	public static final DeferredBlock<RecipeFeastBlock> CHRISTMAS_PUDDING = BLOCKS.register("christmas_pudding",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, stand));
	public static final DeferredBlock<RecipeFeastBlock> PUNCH = BLOCKS.register("punch",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.GLASS).mapColor(MapColor.COLOR_PINK), true,
					Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D)));
	public static final DeferredBlock<Block> MILK_TART = ExtraDelightBlocks.BLOCKS.register("milk_tart",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.MILK_TART_SLICE));
	public static final DeferredBlock<RecipeFeastBlock> MINT_LAMB = BLOCKS.register("mint_lamb",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, Block.box(0.0D, 0.0D, 4.0D, 16.0D, 1.0D, 12.0D),
					Block.box(0.0D, 1.0D, 4.0D, 16.0D, 4.0D, 12.0D)));
	public static final DeferredBlock<RecipeFeastBlock> CHARCUTERIE_BOARD = BLOCKS.register("charcuterie_board",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(1.0D, 1.0D, 1.0D, 15.0D, 5.0D, 15.0D)));

	public static final DeferredBlock<TapBlock> TAP = BLOCKS.register("tap",
			() -> new TapBlock(Block.Properties.ofFullCopy(Blocks.STONE)));

	public static final DeferredBlock<HorizontalPanBlock> SHEET_BLOCK = BLOCKS.register("sheet",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(1.0D, 0.0D, 0.0D, 15.0D, 1.0D, 16.0D), Block.box(0.0D, 0.0D, 1.0D, 16.0D, 1.0D, 15.0D)));
	public static final DeferredBlock<HorizontalPanBlock> TRAY_BLOCK = BLOCKS.register("tray",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(1.0D, 0.0D, 0.0D, 15.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 1.0D, 16.0D, 2.0D, 15.0D)));
	public static final DeferredBlock<HorizontalPanBlock> LOAF_PAN_BLOCK = BLOCKS.register("loaf_pan",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(4.0D, 0.0D, 2.0D, 12.0D, 4.0D, 14.0D), Block.box(2.0D, 0.0D, 4.0D, 14.0D, 4.0D, 12.0D)));
	public static final DeferredBlock<HorizontalPanBlock> PIE_DISH_BLOCK = BLOCKS.register("pie_dish",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D), Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D)));
	public static final DeferredBlock<HorizontalPanBlock> SQUARE_PAN_BLOCK = BLOCKS.register("square_pan",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D), Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D)));
	public static final DeferredBlock<BakingStoneBlock> BAKING_STONE_BLOCK = BLOCKS.register("baking_stone",
			() -> new BakingStoneBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
	public static final DeferredBlock<HorizontalPanBlock> MUFFIN_TIN_BLOCK = BLOCKS.register("muffin_tray",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.STONE),
					Block.box(3.5D, 0.0D, 1.5D, 12.5D, 2.0D, 14.5D), Block.box(1.5D, 0.0D, 3.5D, 14.5D, 2.0D, 12.5D)));
	public static final DeferredBlock<HorizontalPanBlock> SERVING_POT_BLOCK = BLOCKS.register("serving_pot",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
					Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D)));
	public static final DeferredBlock<HorizontalPanBlock> BAR_MOLD = BLOCKS.register("bar_mold",
			() -> new HorizontalPanBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
					Block.box(1.0D, 0.0D, 0.0D, 15.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 1.0D, 16.0D, 2.0D, 15.0D)));

	public static final DeferredBlock<CoffeeBush> COFFEE_BUSH = ExtraDelightBlocks.BLOCKS.register("coffee_bush",
			() -> new CoffeeBush(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission()
					.sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));

	public static final DeferredBlock<PeanutCrop> PEANUT_CROP = ExtraDelightBlocks.BLOCKS.register("peanut_crop",
			() -> new PeanutCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission()
					.sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> WILD_PEANUT = BLOCKS.register("wild_peanut",
			() -> new WildCropBlock(MobEffects.ABSORPTION, 6, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	public static final DeferredBlock<ChiliCrop> CHILI_CROP = BLOCKS.register("chili_crop",
			() -> new ChiliCrop(Block.Properties.ofFullCopy(Blocks.WHEAT)));
	public static final DeferredBlock<Block> WILD_CHILI = BLOCKS.register("wild_chili",
			() -> new WildCropBlock(MobEffects.DAMAGE_BOOST, 6, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	public static final DeferredBlock<MallowRootCrop> MALLOW_ROOT_CROP = BLOCKS.register("mallow_root_crop",
			() -> new MallowRootCrop(Block.Properties.ofFullCopy(Blocks.WHEAT)));
	public static final DeferredBlock<Block> WILD_MALLOW_ROOT = BLOCKS.register("wild_mallow_root",
			() -> new WildCropBlock(MobEffects.SLOW_FALLING, 6, Block.Properties.ofFullCopy(Blocks.TALL_GRASS)));

	// Fluids
	public static final DeferredBlock<LiquidBlock> COOKING_OIL_FLUID_BLOCK = BLOCKS.register("cooking_oil_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.OIL.FLUID.get(),
					BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));

	public static final DeferredBlock<VinegarFluidBlock> VINEGAR_FLUID_BLOCK = BLOCKS.register("vinegar_fluid_block",
			() -> new VinegarFluidBlock(ExtraDelightFluids.VINEGAR.FLUID.get(),
					BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable()));

	public static final DeferredBlock<LiquidBlock> GRAVY_FLUID_BLOCK = BLOCKS.register("gravy_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.GRAVY.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> GLOW_BERRY_JUICE_FLUID_BLOCK = BLOCKS.register(
			"glow_berry_fluid_block",
			() -> new GlowBerryFluidBlock(ExtraDelightFluids.GLOW_BERRY_JUICE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> SWEET_BERRY_JUICE_FLUID_BLOCK = BLOCKS.register(
			"sweet_berry_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.SWEET_BERRY_JUICE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> TOMATO_JUICE_FLUID_BLOCK = BLOCKS.register(
			"tomato_juice_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.TOMATO_JUICE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> CACTUS_JUICE_FLUID_BLOCK = BLOCKS.register(
			"cactus_juice_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.CACTUS_JUICE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> APPLE_CIDER_FLUID_BLOCK = BLOCKS.register("apple_cider_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.APPLE_CIDER.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> HOT_COCOA_FLUID_BLOCK = BLOCKS.register("hot_cocoa_fluid_block",
			() -> new HotFluidBlock(ExtraDelightFluids.HOT_COCOA.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> MELON_JUICE_FLUID_BLOCK = BLOCKS.register("melon_juice_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.MELON_JUICE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> EGG_MIX_FLUID_BLOCK = BLOCKS.register("egg_mix_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.EGG_MIX.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> BBQ_FLUID_BLOCK = BLOCKS.register("bbq_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.BBQ.FLUID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
					.noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> KETCHUP_FLUID_BLOCK = BLOCKS.register("ketchup_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.KETCHUP.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> MAYO_FLUID_BLOCK = BLOCKS.register("mayo_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.MAYO.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> BROTH_FLUID_BLOCK = BLOCKS.register("broth_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.BROTH.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> CARAMEL_SAUCE_FLUID_BLOCK = BLOCKS.register(
			"caramel_sauce_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.CARAMEL_SAUCE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> MILKSHAKE_FLUID_BLOCK = BLOCKS.register("milkshake_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.MILKSHAKE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> WHIPPED_CREAM_FLUID_BLOCK = BLOCKS.register(
			"whipped_cream_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.WHIPPED_CREAM.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> JAM_FLUID_BLOCK = BLOCKS.register("jam_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.JAM.FLUID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
					.noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> GOLDEN_JAM_FLUID_BLOCK = BLOCKS.register("golden_jam_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.GOLDEN_JAM.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> GLOW_JAM_FLUID_BLOCK = BLOCKS.register("glow_jam_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.GLOW_JAM.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> TEA_FLUID_BLOCK = BLOCKS.register("tea_fluid_block",
			() -> new HotFluidBlock(ExtraDelightFluids.TEA.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<KegBlock> KEG = BLOCKS.register("keg_block",
			() -> new KegBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));
	public static final DeferredBlock<JarBlock> JAR = BLOCKS.register("jar",
			() -> new JarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));

	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FRUIT_LOG = BLOCKS.register("stripped_fruit_log",
			() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> FRUIT_WOOD = BLOCKS.register("fruit_wood",
			() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)) {
				@Override
				public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility,
						boolean simulate) {
					if (itemAbility == ItemAbilities.AXE_STRIP)
						return ExtraDelightBlocks.STRIPPED_FRUIT_WOOD.get().defaultBlockState()
								.setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
					return null;
				}
			});
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FRUIT_WOOD = BLOCKS.register("stripped_fruit_wood",
			() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> FRUIT_LOG = BLOCKS.register("fruit_log",
			() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)) {
				@Override
				public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility,
						boolean simulate) {
					if (itemAbility == ItemAbilities.AXE_STRIP)
						return ExtraDelightBlocks.STRIPPED_FRUIT_LOG.get().defaultBlockState()
								.setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
					return null;
				}
			});
	public static final DeferredBlock<Block> FRUIT_PLANKS = BLOCKS.register("fruit_planks",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<FenceBlock> FRUIT_FENCE = BLOCKS.register("fruit_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> FRUIT_FENCE_GATE = BLOCKS.register("fruit_fence_gate",
			() -> new FenceGateBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> FRUIT_STAIRS = BLOCKS.register("fruit_stairs",
			() -> new StairBlock(FRUIT_PLANKS.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> FRUIT_DOOR = BLOCKS.register("fruit_door",
			() -> new DoorBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> FRUIT_TRAPDOOR = BLOCKS.register("fruit_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<Block> FRUIT_CABINET = BLOCKS.register("fruit_cabinet",
			() -> new CabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
	public static final DeferredBlock<SlabBlock> FRUIT_SLAB = BLOCKS.register("fruit_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB)));
	public static final DeferredBlock<PressurePlateBlock> FRUIT_PRESSURE_PLATE = BLOCKS.register("fruit_pressure_plate",
			() -> new PressurePlateBlock(BlockSetType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<ButtonBlock> FRUIT_BUTTON = BLOCKS.register("fruit_button",
			() -> new ButtonBlock(BlockSetType.OAK, 30, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

//	public static final DeferredBlock<CeilingHangingSignBlock> FRUIT_CEILING_HANGING_SIGN = BLOCKS.register(
//			"fruit_ceiling_hanging_sign",
//			() -> new CeilingHangingSignBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
//	public static final DeferredBlock<WallHangingSignBlock> FRUIT_WALL_HANGING_SIGN = BLOCKS.register(
//			"fruit_wall_hanging_sign",
//			() -> new WallHangingSignBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<StandingSignBlock> FRUIT_STANDING_SIGN = BLOCKS.register("fruit_standing_sign",
			() -> new StandingSignBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
	public static final DeferredBlock<WallSignBlock> FRUIT_WALL_SIGN = BLOCKS.register("fruit_wall_sign",
			() -> new WallSignBlock(WoodType.OAK, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

	public static final DeferredBlock<FruitLeafBlock> HAZELNUT_LEAVES = BLOCKS.register("hazelnut_leaves",
			() -> new FruitLeafBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LEAVES),
					ExtraDelightItems.HAZELNUTS_IN_SHELL));
	public static final DeferredBlock<SaplingBlock> HAZELNUT_SAPLING = BLOCKS.register("hazelnut_sapling",
			() -> new SaplingBlock(ExtraDelightTreeGrowers.HAZELNUT,
					Block.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));

	public static final DeferredBlock<Block> MILK_CHOCOLATE_BLOCK = BLOCKS.register("milk_chocolate_block",
			() -> new ChocolateStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_WOOD)));
	public static final DeferredBlock<FenceBlock> MILK_CHOCOLATE_FENCE = BLOCKS.register("milk_chocolate_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> MILK_CHOCOLATE_FENCE_GATE = BLOCKS.register(
			"milk_chocolate_fence_gate",
			() -> new FenceGateBlock(WoodType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> MILK_CHOCOLATE_STAIRS = BLOCKS.register("milk_chocolate_stairs",
			() -> new StairBlock(MILK_CHOCOLATE_BLOCK.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> MILK_CHOCOLATE_DOOR = BLOCKS.register("milk_chocolate_door",
			() -> new DoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> MILK_CHOCOLATE_TRAPDOOR = BLOCKS.register(
			"milk_chocolate_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<SlabBlock> MILK_CHOCOLATE_SLAB = BLOCKS.register("milk_chocolate_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
	public static final DeferredBlock<RotatedPillarBlock> MILK_CHOCOLATE_PILLAR = BLOCKS.register(
			"milk_chocolate_pillar", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));

	public static final DeferredBlock<Block> DARK_CHOCOLATE_BLOCK = BLOCKS.register("dark_chocolate_block",
			() -> new ChocolateStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_WOOD)));
	public static final DeferredBlock<FenceBlock> DARK_CHOCOLATE_FENCE = BLOCKS.register("dark_chocolate_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> DARK_CHOCOLATE_FENCE_GATE = BLOCKS.register(
			"dark_chocolate_fence_gate",
			() -> new FenceGateBlock(WoodType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> DARK_CHOCOLATE_STAIRS = BLOCKS.register("dark_chocolate_stairs",
			() -> new StairBlock(DARK_CHOCOLATE_BLOCK.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> DARK_CHOCOLATE_DOOR = BLOCKS.register("dark_chocolate_door",
			() -> new DoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> DARK_CHOCOLATE_TRAPDOOR = BLOCKS.register(
			"dark_chocolate_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<SlabBlock> DARK_CHOCOLATE_SLAB = BLOCKS.register("dark_chocolate_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
	public static final DeferredBlock<RotatedPillarBlock> DARK_CHOCOLATE_PILLAR = BLOCKS.register(
			"dark_chocolate_pillar", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));

	public static final DeferredBlock<Block> WHITE_CHOCOLATE_BLOCK = BLOCKS.register("white_chocolate_block",
			() -> new ChocolateStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_WOOD)));
	public static final DeferredBlock<FenceBlock> WHITE_CHOCOLATE_FENCE = BLOCKS.register("white_chocolate_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> WHITE_CHOCOLATE_FENCE_GATE = BLOCKS.register(
			"white_chocolate_fence_gate",
			() -> new FenceGateBlock(WoodType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> WHITE_CHOCOLATE_STAIRS = BLOCKS.register("white_chocolate_stairs",
			() -> new StairBlock(WHITE_CHOCOLATE_BLOCK.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> WHITE_CHOCOLATE_DOOR = BLOCKS.register("white_chocolate_door",
			() -> new DoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> WHITE_CHOCOLATE_TRAPDOOR = BLOCKS.register(
			"white_chocolate_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<SlabBlock> WHITE_CHOCOLATE_SLAB = BLOCKS.register("white_chocolate_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
	public static final DeferredBlock<RotatedPillarBlock> WHITE_CHOCOLATE_PILLAR = BLOCKS.register(
			"white_chocolate_pillar", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));

	public static final DeferredBlock<Block> BLOOD_CHOCOLATE_BLOCK = BLOCKS.register("blood_chocolate_block",
			() -> new ChocolateStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_WOOD)));
	public static final DeferredBlock<FenceBlock> BLOOD_CHOCOLATE_FENCE = BLOCKS.register("blood_chocolate_fence",
			() -> new FenceBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE)));
	public static final DeferredBlock<FenceGateBlock> BLOOD_CHOCOLATE_FENCE_GATE = BLOCKS.register(
			"blood_chocolate_fence_gate",
			() -> new FenceGateBlock(WoodType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE)));
	public static final DeferredBlock<StairBlock> BLOOD_CHOCOLATE_STAIRS = BLOCKS.register("blood_chocolate_stairs",
			() -> new StairBlock(BLOOD_CHOCOLATE_BLOCK.get().defaultBlockState(),
					Block.Properties.ofFullCopy(Blocks.ACACIA_STAIRS)));
	public static final DeferredBlock<DoorBlock> BLOOD_CHOCOLATE_DOOR = BLOCKS.register("blood_chocolate_door",
			() -> new DoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_DOOR)));
	public static final DeferredBlock<TrapDoorBlock> BLOOD_CHOCOLATE_TRAPDOOR = BLOCKS.register(
			"blood_chocolate_trapdoor",
			() -> new TrapDoorBlock(BlockSetType.ACACIA, Block.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR)));
	public static final DeferredBlock<SlabBlock> BLOOD_CHOCOLATE_SLAB = BLOCKS.register("blood_chocolate_slab",
			() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
	public static final DeferredBlock<RotatedPillarBlock> BLOOD_CHOCOLATE_PILLAR = BLOCKS.register(
			"blood_chocolate_pillar", () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));

	public static final DeferredBlock<Block> MELTING_POT = BLOCKS.register("melting_pot",
			() -> new MeltingPotBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
	public static final DeferredBlock<Block> CHILLER = BLOCKS.register("chiller", () -> new ChillerBlock());
	public static final DeferredBlock<Block> FUNNEL = BLOCKS.register("funnel",
			() -> new FunnelBlock(Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));

	public static final DeferredBlock<ChocolateBoxBlock> WHITE_CHOCOLATE_BOX = BLOCKS.register("white_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.WHITE, Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> ORANGE_CHOCOLATE_BOX = BLOCKS.register("orange_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.ORANGE, Block.Properties.ofFullCopy(Blocks.ORANGE_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> MAGENTA_CHOCOLATE_BOX = BLOCKS.register(
			"magenta_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.MAGENTA, Block.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> LIGHT_BLUE_CHOCOLATE_BOX = BLOCKS.register(
			"light_blue_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.LIGHT_BLUE, Block.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> YELLOW_CHOCOLATE_BOX = BLOCKS.register("yellow_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.YELLOW, Block.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> LIME_CHOCOLATE_BOX = BLOCKS.register("lime_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.LIME, Block.Properties.ofFullCopy(Blocks.LIME_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> PINK_CHOCOLATE_BOX = BLOCKS.register("pink_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.PINK, Block.Properties.ofFullCopy(Blocks.PINK_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> GRAY_CHOCOLATE_BOX = BLOCKS.register("gray_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.GRAY, Block.Properties.ofFullCopy(Blocks.GRAY_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> LIGHT_GRAY_CHOCOLATE_BOX = BLOCKS.register(
			"light_gray_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.LIGHT_GRAY, Block.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> CYAN_CHOCOLATE_BOX = BLOCKS.register("cyan_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.CYAN, Block.Properties.ofFullCopy(Blocks.CYAN_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> BLUE_CHOCOLATE_BOX = BLOCKS.register("blue_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.BLUE, Block.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> BROWN_CHOCOLATE_BOX = BLOCKS.register("brown_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.BROWN, Block.Properties.ofFullCopy(Blocks.BROWN_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> GREEN_CHOCOLATE_BOX = BLOCKS.register("green_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.GREEN, Block.Properties.ofFullCopy(Blocks.GREEN_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> RED_CHOCOLATE_BOX = BLOCKS.register("red_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.RED, Block.Properties.ofFullCopy(Blocks.RED_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> BLACK_CHOCOLATE_BOX = BLOCKS.register("black_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.BLACK, Block.Properties.ofFullCopy(Blocks.BLACK_WOOL)));
	public static final DeferredBlock<ChocolateBoxBlock> PURPLE_CHOCOLATE_BOX = BLOCKS.register("purple_chocolate_box",
			() -> new ChocolateBoxBlock(DyeColor.PURPLE, Block.Properties.ofFullCopy(Blocks.PURPLE_WOOL)));

	public static final DeferredBlock<RecipeFeastBlock> BROWNIES = BLOCKS.register("brownies",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));
	public static final DeferredBlock<RecipeFeastBlock> BLONDIES = BLOCKS.register("blondies",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.YELLOW_WOOL).mapColor(MapColor.COLOR_YELLOW),
					true, pan));
	public static final DeferredBlock<Block> CHOCOLATE_CAKE = ExtraDelightBlocks.BLOCKS.register("chocolate_cake",
			() -> new CakeBlock(Block.Properties.ofFullCopy(Blocks.CAKE)));
	public static final DeferredBlock<RecipeFeastBlock> FUDGE = BLOCKS.register("fudge",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, Block.box(1.0D, 0.0D, 0.0D, 15.0D, 2.0D, 16.0D)));
	public static final DeferredBlock<RecipeFeastBlock> STICKY_TOFFEE_PUDDING = BLOCKS.register("sticky_toffee_pudding",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, stand));
	public static final DeferredBlock<RecipeFeastBlock> CRISP_RICE_TREATS = BLOCKS.register("crisp_rice_treats",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.YELLOW_WOOL).mapColor(MapColor.COLOR_YELLOW),
					true, pan));
	public static final DeferredBlock<RecipeFeastBlock> SCOTCHAROOS = BLOCKS.register("scotcharoos",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));
	public static final DeferredBlock<RecipeFeastBlock> BLACK_FOREST_TRIFLE = BLOCKS.register("black_forest_trifle",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 7.0D, 14.0D)));

	public static final DeferredBlock<RecipeFondueFeastBlock> MILK_CHOCOLATE_FONDUE = BLOCKS
			.register("milk_chocolate_fondue", () -> new RecipeFondueFeastBlock(
					Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN), true, fondue));
	public static final DeferredBlock<RecipeFondueFeastBlock> DARK_CHOCOLATE_FONDUE = BLOCKS
			.register("dark_chocolate_fondue", () -> new RecipeFondueFeastBlock(
					Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN), true, fondue));
	public static final DeferredBlock<RecipeFondueFeastBlock> WHITE_CHOCOLATE_FONDUE = BLOCKS
			.register("white_chocolate_fondue", () -> new RecipeFondueFeastBlock(
					Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN), true, fondue));
	public static final DeferredBlock<RecipeFondueFeastBlock> BLOOD_CHOCOLATE_FONDUE = BLOCKS
			.register("blood_chocolate_fondue", () -> new RecipeFondueFeastBlock(
					Block.Properties.ofFullCopy(Blocks.BROWN_WOOL).mapColor(MapColor.COLOR_BROWN), true, fondue));

	public static final DeferredBlock<LiquidBlock> COCOA_BUTTER_FLUID_BLOCK = BLOCKS.register(
			"cocoa_butter_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.COCOA_BUTTER.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> WHITE_CHOCOLATE_SYRUP_FLUID_BLOCK = BLOCKS.register(
			"white_chocolate_syrup_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.WHITE_CHOCOLATE_SYRUP.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> DARK_CHOCOLATE_SYRUP_FLUID_BLOCK = BLOCKS.register(
			"dark_chocolate_syrup_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.DARK_CHOCOLATE_SYRUP.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> MILK_CHOCOLATE_SYRUP_FLUID_BLOCK = BLOCKS.register(
			"milk_chocolate_syrup_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.MILK_CHOCOLATE_SYRUP.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> BLOOD_CHOCOLATE_SYRUP_FLUID_BLOCK = BLOCKS.register(
			"blood_chocolate_syrup_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.BLOOD_CHOCOLATE_SYRUP.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<LiquidBlock> COFFEE_FLUID_BLOCK = BLOCKS.register("coffee_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.COFFEE.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> NUT_BUTTER_FLUID_BLOCK = BLOCKS.register("peanut_butter_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.NUT_BUTTER.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> MARSHMALLOW_FLUFF_FLUID_BLOCK = BLOCKS.register(
			"marshmallow_fluff_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.MARSHMALLOW_FLUFF.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
	public static final DeferredBlock<LiquidBlock> COCOA_NUT_BUTTER_SPREAD_FLUID_BLOCK = BLOCKS.register(
			"cocoa_nut_butter_spread_fluid_block",
			() -> new LiquidBlock(ExtraDelightFluids.COCOA_NUT_BUTTER_SPREAD.FLUID.get(), BlockBehaviour.Properties
					.ofFullCopy(Blocks.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

	public static final DeferredBlock<Block> MARSHMALLOW_BLOCK = BLOCKS.register("marshmallow_block",
			() -> new Block(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
	public static final DeferredBlock<Block> GOLDEN_CARROT_CRATE = BLOCKS.register("golden_carrot_crate",
			() -> new Block(
					Block.Properties.ofFullCopy(ModBlocks.BEETROOT_CRATE.get()).mapColor(MapColor.TERRACOTTA_YELLOW)));

	public static final DeferredBlock<VanillaFruitLeafBlock> APPLE_LEAVES = BLOCKS.register("apple_leaves",
			() -> new VanillaFruitLeafBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LEAVES),
					Items.APPLE));
	public static final DeferredBlock<SaplingBlock> APPLE_SAPLING = BLOCKS.register("apple_sapling",
			() -> new SaplingBlock(ExtraDelightTreeGrowers.APPLE,
					Block.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));
	public static final DeferredBlock<RecipeFeastBlock> PORK_AND_APPLES_FEAST = BLOCKS.register("pork_apple_roast",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, plate, Block.box(5.0D, 2.0D, 2.0D, 11.0D, 8.0D, 14.0D),
					Block.box(2.0D, 2.0D, 5.0D, 14.0D, 8.0D, 11.0D)));
	public static final DeferredBlock<RecipeFeastBlock> STUFFED_APPLES_FEAST = BLOCKS.register("stuffed_apples",
			() -> new RecipeFeastBlock(Block.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.COLOR_BROWN),
					true, pan));
	public static final DeferredBlock<Block> TARTE_TATIN = ExtraDelightBlocks.BLOCKS.register("tarte_tatin",
			() -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ExtraDelightItems.TARTE_TATIN_SLICE));
}
