package com.lance5057.extradelight;

import com.lance5057.extradelight.blocks.countercabinet.CounterCabinetMenu;
import com.lance5057.extradelight.blocks.countercabinet.CounterCabinetScreen;
import com.lance5057.extradelight.blocks.sink.SinkCabinetMenu;
import com.lance5057.extradelight.blocks.sink.SinkCabinetScreen;
import com.lance5057.extradelight.displays.food.FoodDisplayMenu;
import com.lance5057.extradelight.displays.food.FoodDisplayScreen;
import com.lance5057.extradelight.displays.knife.KnifeBlockMenu;
import com.lance5057.extradelight.displays.knife.KnifeBlockScreen;
import com.lance5057.extradelight.displays.spice.SpiceRackMenu;
import com.lance5057.extradelight.displays.spice.SpiceRackScreen;
import com.lance5057.extradelight.displays.wreath.WreathMenu;
import com.lance5057.extradelight.displays.wreath.WreathScreen;
import com.lance5057.extradelight.gui.StyleableMenu;
import com.lance5057.extradelight.gui.StyleableScreen;
import com.lance5057.extradelight.workstations.doughshaping.DoughShapingMenu;
import com.lance5057.extradelight.workstations.doughshaping.DoughShapingScreen;
import com.lance5057.extradelight.workstations.oven.OvenMenu;
import com.lance5057.extradelight.workstations.oven.OvenScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ExtraDelightContainers {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.Keys.MENU_TYPES,
			ExtraDelight.MOD_ID);

	public static final Supplier<MenuType<OvenMenu>> OVEN_MENU = MENU_TYPES.register("oven",
			() -> IForgeMenuType.create(OvenMenu::new));

	public static final Supplier<MenuType<FoodDisplayMenu>> FOOD_DISPLAY_MENU = MENU_TYPES.register("food_display",
			() -> IForgeMenuType.create(FoodDisplayMenu::new));
	public static final Supplier<MenuType<KnifeBlockMenu>> KNIFE_BLOCK_MENU = MENU_TYPES.register("knife_block",
			() -> IForgeMenuType.create(KnifeBlockMenu::new));
	public static final Supplier<MenuType<SpiceRackMenu>> SPICE_RACK_MENU = MENU_TYPES.register("spice_rack",
			() -> IForgeMenuType.create(SpiceRackMenu::new));

	public static final Supplier<MenuType<DoughShapingMenu>> DOUGH_SHAPING_MENU = MENU_TYPES.register("dough_shaping",
			() -> IForgeMenuType.create(DoughShapingMenu::new));
	public static final Supplier<MenuType<WreathMenu>> WREATH_MENU = MENU_TYPES.register("wreath",
			() -> IForgeMenuType.create(WreathMenu::new));
	public static final Supplier<MenuType<SinkCabinetMenu>> SINK_MENU = MENU_TYPES.register("sink",
			() -> IForgeMenuType.create(SinkCabinetMenu::new));
	public static final Supplier<MenuType<CounterCabinetMenu>> COUNTER_CABINET_MENU = MENU_TYPES
			.register("counter_cabinet", () -> IForgeMenuType.create(CounterCabinetMenu::new));
	public static final Supplier<MenuType<StyleableMenu>> STYLE_MENU = MENU_TYPES.register("style",
			() -> IForgeMenuType.create(StyleableMenu::new));

	public static void register(IEventBus modBus) {
		MENU_TYPES.register(modBus);
	}

	public static void registerClient(FMLClientSetupEvent event) {
		MenuScreens.register(OVEN_MENU.get(), OvenScreen::new);
		MenuScreens.register(FOOD_DISPLAY_MENU.get(), FoodDisplayScreen::new);
		MenuScreens.register(KNIFE_BLOCK_MENU.get(), KnifeBlockScreen::new);
		MenuScreens.register(SPICE_RACK_MENU.get(), SpiceRackScreen::new);
		MenuScreens.register(DOUGH_SHAPING_MENU.get(), DoughShapingScreen::new);
		MenuScreens.register(WREATH_MENU.get(), WreathScreen::new);
		MenuScreens.register(SINK_MENU.get(), SinkCabinetScreen::new);
		MenuScreens.register(COUNTER_CABINET_MENU.get(), CounterCabinetScreen::new);
		MenuScreens.register(STYLE_MENU.get(), StyleableScreen::new);
	}
}
