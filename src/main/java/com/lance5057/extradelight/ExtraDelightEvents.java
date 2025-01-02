package com.lance5057.extradelight;

import com.lance5057.extradelight.items.components.ChillComponent;
import com.lance5057.extradelight.items.dynamicfood.api.PieceTypes;

import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ExtraDelight.MOD_ID)
public class ExtraDelightEvents {

	@SubscribeEvent
	public static void modifyComponents(ModifyDefaultComponentsEvent event) {
		event.modify(Items.BLUE_ICE, builder -> builder.set(ExtraDelightComponents.CHILL.value(), new ChillComponent(2500)));
		event.modify(Items.PACKED_ICE, builder -> builder.set(ExtraDelightComponents.CHILL.value(), new ChillComponent(1000)));
		event.modify(Items.ICE, builder -> builder.set(ExtraDelightComponents.CHILL.value(), new ChillComponent(250)));
		event.modify(Items.SNOWBALL,
				builder -> builder.set(ExtraDelightComponents.CHILL.value(), new ChillComponent(100)));
	}

	@SubscribeEvent
	static void registerRegistries(NewRegistryEvent event) {
	    event.register(PieceTypes.PIECE_REGISTRY);
	}
}
