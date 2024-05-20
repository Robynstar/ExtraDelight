package com.lance5057.extradelight;

import com.lance5057.extradelight.aesthetics.AestheticBlocks;
import com.lance5057.extradelight.aesthetics.block.cornhuskdoll.CornHuskDollBlockEntity;
import com.lance5057.extradelight.blocks.entities.TapBlockEntity;
import com.lance5057.extradelight.displays.candybowl.CandyBowlEntity;
import com.lance5057.extradelight.displays.food.FoodDisplayEntity;
import com.lance5057.extradelight.displays.knife.KnifeBlockEntity;
import com.lance5057.extradelight.displays.spice.SpiceRackEntity;
import com.lance5057.extradelight.displays.wreath.WreathEntity;
import com.lance5057.extradelight.workstations.dryingrack.DryingRackBlockEntity;
import com.lance5057.extradelight.workstations.mixingbowl.MixingBowlBlockEntity;
import com.lance5057.extradelight.workstations.mortar.MortarBlockEntity;
import com.lance5057.extradelight.workstations.oven.OvenBlockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExtraDelightBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister
			.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExtraDelight.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OvenBlockEntity>> OVEN = TILES.register(
			"oven", () -> BlockEntityType.Builder.of(OvenBlockEntity::new, ExtraDelightBlocks.OVEN.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FoodDisplayEntity>> FOOD_DISPLAY = TILES
			.register("food_display", () -> BlockEntityType.Builder
					.of(FoodDisplayEntity::new, ExtraDelightBlocks.FOOD_DISPLAY.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KnifeBlockEntity>> KNIFE_BLOCK = TILES
			.register("knife_block", () -> BlockEntityType.Builder
					.of(KnifeBlockEntity::new, AestheticBlocks.getRegistryListAsBlocks(AestheticBlocks.KNIFE_BLOCKS))
					.build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiceRackEntity>> SPICE_RACK = TILES
			.register("spice_rack", () -> BlockEntityType.Builder
					.of(SpiceRackEntity::new, AestheticBlocks.getRegistryListAsBlocks(AestheticBlocks.SPICE_RACKS))
					.build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiceRackEntity>> SPICE_RACK_FULL = TILES.register(
			"spice_rack_full",
			() -> BlockEntityType.Builder
					.of(SpiceRackEntity::new, AestheticBlocks.getRegistryListAsBlocks(AestheticBlocks.SPICE_RACKS_FULL))
					.build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WreathEntity>> WREATH = TILES.register("wreath",
			() -> BlockEntityType.Builder
					.of(WreathEntity::new, AestheticBlocks.getRegistryListAsBlocks(AestheticBlocks.WREATHS))
					.build(null));

//	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HalfCabinetEntity>> HALF_CABINET = TILES.register("half_cabinet",
//			() -> BlockEntityType.Builder
//					.of(HalfCabinetEntity::new, AestheticBlocks.getRegistryListAsBlocks(AestheticBlocks.CABINETS))
//					.build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MortarBlockEntity>> MORTAR = TILES.register("mortar",
			() -> BlockEntityType.Builder.of(MortarBlockEntity::new, ExtraDelightBlocks.MORTAR_AMETHYST.get(),
					ExtraDelightBlocks.MORTAR_ANDESITE.get(), ExtraDelightBlocks.MORTAR_BASALT.get(),
					ExtraDelightBlocks.MORTAR_BLACKSTONE.get(), ExtraDelightBlocks.MORTAR_DEEPSLATE.get(),
					ExtraDelightBlocks.MORTAR_DIORITE.get(), ExtraDelightBlocks.MORTAR_ENDSTONE.get(),
					ExtraDelightBlocks.MORTAR_GILDED_BLACKSTONE.get(), ExtraDelightBlocks.MORTAR_GRANITE.get(),
					ExtraDelightBlocks.MORTAR_STONE.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MixingBowlBlockEntity>> MIXING_BOWL = TILES
			.register("mixing_bowl", () -> BlockEntityType.Builder
					.of(MixingBowlBlockEntity::new, ExtraDelightBlocks.MIXING_BOWL.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DryingRackBlockEntity>> DRYING_RACK = TILES
			.register("drying_rack", () -> BlockEntityType.Builder
					.of(DryingRackBlockEntity::new, ExtraDelightBlocks.DRYING_RACK.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CornHuskDollBlockEntity>> CORN_HUSK_DOLL = TILES
			.register("corn_husk_doll", () -> BlockEntityType.Builder
					.of(CornHuskDollBlockEntity::new, AestheticBlocks.CORN_HUSK_DOLL.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CandyBowlEntity>> CANDY_BOWL = TILES.register("candy_bowl",
			() -> BlockEntityType.Builder.of(CandyBowlEntity::new, ExtraDelightBlocks.CANDY_BOWL.get()).build(null));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TapBlockEntity>> WELL = TILES.register("well",
			() -> BlockEntityType.Builder.of(TapBlockEntity::new, ExtraDelightBlocks.TAP.get()).build(null));
}
