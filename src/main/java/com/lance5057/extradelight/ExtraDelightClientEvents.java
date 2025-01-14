package com.lance5057.extradelight;

import java.util.Map;

import com.lance5057.extradelight.aesthetics.AestheticBlocks;
import com.lance5057.extradelight.aesthetics.block.cornhuskdoll.CornHuskDollRenderer;
import com.lance5057.extradelight.armor.models.CorncobPipeModel;
import com.lance5057.extradelight.blocks.chocolatebox.ChocolateBoxRenderer;
import com.lance5057.extradelight.blocks.countercabinet.CounterCabinetRenderer;
import com.lance5057.extradelight.blocks.countercabinet.CounterCabinetScreen;
import com.lance5057.extradelight.blocks.funnel.FunnelRenderer;
import com.lance5057.extradelight.blocks.jar.JarRenderer;
import com.lance5057.extradelight.blocks.keg.KegRenderer;
import com.lance5057.extradelight.blocks.sink.SinkCabinetScreen;
import com.lance5057.extradelight.blocks.sink.SinkRenderer;
import com.lance5057.extradelight.displays.candybowl.CandyBowlRenderer;
import com.lance5057.extradelight.displays.food.FoodDisplayRenderer;
import com.lance5057.extradelight.displays.food.FoodDisplayScreen;
import com.lance5057.extradelight.displays.knife.KnifeBlockRenderer;
import com.lance5057.extradelight.displays.knife.KnifeBlockScreen;
import com.lance5057.extradelight.displays.spice.SpiceRackRenderer;
import com.lance5057.extradelight.displays.spice.SpiceRackScreen;
import com.lance5057.extradelight.displays.wreath.WreathRenderer;
import com.lance5057.extradelight.displays.wreath.WreathScreen;
import com.lance5057.extradelight.gui.StyleableScreen;
import com.lance5057.extradelight.items.jar.JarItemModel;
import com.lance5057.extradelight.workstations.chiller.ChillerScreen;
import com.lance5057.extradelight.workstations.doughshaping.DoughShapingScreen;
import com.lance5057.extradelight.workstations.dryingrack.DryingRackRenderer;
import com.lance5057.extradelight.workstations.meltingpot.MeltingPotScreen;
import com.lance5057.extradelight.workstations.mixingbowl.MixingBowlRenderer;
import com.lance5057.extradelight.workstations.mixingbowl.MixingBowlScreen;
import com.lance5057.extradelight.workstations.mortar.MortarRenderer;
import com.lance5057.extradelight.workstations.oven.OvenScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent.RegisterAdditional;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ExtraDelight.MOD_ID, value = Dist.CLIENT)
public class ExtraDelightClientEvents {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(CorncobPipeModel.LAYER_LOCATION, () -> LayerDefinition
				.create(CorncobPipeModel.createLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 16, 16));
	}

	@SubscribeEvent
	public static void registerClient(RegisterMenuScreensEvent event) {
		event.register(ExtraDelightContainers.OVEN_MENU.get(), OvenScreen::new);
		event.register(ExtraDelightContainers.FOOD_DISPLAY_MENU.get(), FoodDisplayScreen::new);
		event.register(ExtraDelightContainers.KNIFE_BLOCK_MENU.get(), KnifeBlockScreen::new);
		event.register(ExtraDelightContainers.SPICE_RACK_MENU.get(), SpiceRackScreen::new);
		event.register(ExtraDelightContainers.DOUGH_SHAPING_MENU.get(), DoughShapingScreen::new);
		event.register(ExtraDelightContainers.WREATH_MENU.get(), WreathScreen::new);
		event.register(ExtraDelightContainers.SINK_MENU.get(), SinkCabinetScreen::new);
		event.register(ExtraDelightContainers.COUNTER_CABINET_MENU.get(), CounterCabinetScreen::new);
		event.register(ExtraDelightContainers.STYLE_MENU.get(), StyleableScreen::new);
		event.register(ExtraDelightContainers.MIXING_BOWL_MENU.get(), MixingBowlScreen::new);
		event.register(ExtraDelightContainers.MELTING_POT_MENU.get(), MeltingPotScreen::new);
		event.register(ExtraDelightContainers.CHILLER_MENU.get(), ChillerScreen::new);
	}

	public static void setTERenderers() {
		BlockEntityRenderers.register(ExtraDelightBlockEntities.FOOD_DISPLAY.get(), FoodDisplayRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.KNIFE_BLOCK.get(), KnifeBlockRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.SPICE_RACK.get(), SpiceRackRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.MORTAR.get(), MortarRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.MIXING_BOWL.get(), MixingBowlRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.DRYING_RACK.get(), DryingRackRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.CORN_HUSK_DOLL.get(), CornHuskDollRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.WREATH.get(), WreathRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.CANDY_BOWL.get(), CandyBowlRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.COUNTER_CABINET_BLOCK.get(),
				CounterCabinetRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.SINK_BLOCK.get(), SinkRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.KEG.get(), KegRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.FUNNEL.get(), FunnelRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.CHOCOLATE_BOX.get(), ChocolateBoxRenderer::new);
		BlockEntityRenderers.register(ExtraDelightBlockEntities.JAR.get(), JarRenderer::new);
	}

	@SubscribeEvent
	public static void RegisterExtraModels(RegisterAdditional event) {
		Map<ResourceLocation, Resource> rrs = Minecraft.getInstance().getResourceManager().listResources("models/extra",
				(p_215600_) -> {
					return p_215600_.getPath().endsWith(".json");
				});

		rrs.forEach((rl, r) -> {
			String s = rl.toString();

			s = s.substring(s.indexOf('/') + 1, s.indexOf('.'));

			ExtraDelight.logger.debug(s);

			ModelResourceLocation rl2 = ModelResourceLocation
					.standalone(ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), s));

			event.register(rl2);
		});
	}

	@SubscribeEvent
	public static void registerBlockColourHandlers(final RegisterColorHandlersEvent.Block event) {

		final BlockColor bc = (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null) {
				return BiomeColors.getAverageFoliageColor(blockAccess, pos);
			}

			return FoliageColor.getDefaultColor();
		};

		event.register((p_92636_, p_92637_, p_92638_, p_92639_) -> {
			return FoliageColor.getEvergreenColor();
		}, AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.spruce.ordinal()).get());

		event.register((p_92631_, p_92632_, p_92633_, p_92634_) -> {
			return FoliageColor.getBirchColor();
		}, AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.birch.ordinal()).get());
		event.register((p_92626_, p_92627_, p_92628_, p_92629_) -> {
			return p_92627_ != null && p_92628_ != null ? BiomeColors.getAverageFoliageColor(p_92627_, p_92628_)
					: FoliageColor.getDefaultColor();
		}, AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.oak.ordinal()).get(),
				AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.jungle.ordinal()).get(),
				AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.acacia.ordinal()).get(),
				AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.dark_oak.ordinal()).get(),
				AestheticBlocks.WREATHS.get(AestheticBlocks.WOOD.mangrove.ordinal()).get());
	}

	@SubscribeEvent
	public static void registerItemColourHandlers(final RegisterColorHandlersEvent.Item event) {
		final BlockColors blockColors = event.getBlockColors();

		// Use the Block's colour handler for an ItemBlock
		final ItemColor itemBlockColourHandler = (stack, tintIndex) -> {
			final BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return blockColors.getColor(state, null, null, tintIndex);
		};

//		for (RegistryObject<Item> b : AestheticBlocks.WREATH_ITEMS)
		event.register(itemBlockColourHandler, AestheticBlocks.getRegistryListAsItems(AestheticBlocks.WREATH_ITEMS));
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			@Override
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
					EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
				EntityModelSet models = Minecraft.getInstance().getEntityModels();
				ModelPart root = models.bakeLayer(CorncobPipeModel.LAYER_LOCATION);
				return new CorncobPipeModel(root);
			}

		}, ExtraDelightItems.CORN_COB_PIPE.asItem());

		event.registerItem(new IClientItemExtensions() {

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return JarItemModel.getInstance();
			}
		}, ExtraDelightItems.JAR.asItem());

	}
}
