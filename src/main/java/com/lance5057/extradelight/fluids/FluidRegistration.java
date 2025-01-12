package com.lance5057.extradelight.fluids;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FluidRegistration {
	private ForgeFlowingFluid.Properties properties(Supplier<? extends LiquidBlock> block,
			Supplier<? extends Item> bucket) {
		return new ForgeFlowingFluid.Properties(TYPE, FLUID, FLUID_FLOWING).block(block).bucket(bucket);
	}

	public final RegistryObject<FluidType> TYPE;

	public final RegistryObject<ForgeFlowingFluid.Source> FLUID;
	public final RegistryObject<ForgeFlowingFluid.Flowing> FLUID_FLOWING;

	public FluidRegistration(String name, Supplier<? extends FluidType> fluid, Supplier<? extends LiquidBlock> block,
			Supplier<? extends Item> bucket, DeferredRegister<FluidType> FLUID_TYPES, DeferredRegister<Fluid> FLUIDS) {
		TYPE = FLUID_TYPES.register(name + "_fluid", fluid);

//		ForgeFlowingFluid.Properties p = properties(block, bucket);

		FLUID = FLUIDS.register(name + "_fluid", () -> new ForgeFlowingFluid.Source(properties(block, bucket)));
		FLUID_FLOWING = FLUIDS.register(name + "_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(properties(block, bucket)));
	}
}
