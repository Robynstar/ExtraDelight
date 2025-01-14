package com.lance5057.extradelight.workstations.oven;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.lance5057.extradelight.ExtraDelightBlockEntities;
import com.lance5057.extradelight.ExtraDelightItems;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.TranslatableKeys;
import com.lance5057.extradelight.workstations.oven.inventory.OvenItemHandler;
import com.lance5057.extradelight.workstations.oven.recipes.OvenRecipe;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class OvenBlockEntity extends SyncedBlockEntity
		implements MenuProvider, HeatableBlockEntity, Nameable, RecipeCraftingHolder {
	public static final int MEAL_DISPLAY_SLOT = 9;
	public static final int CONTAINER_SLOT = 10;
	public static final int OUTPUT_SLOT = 11;
	public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;
	protected final ContainerData OvenData;
	private final ItemStackHandler inventory;
	public final IItemHandler inputHandler;
	public final IItemHandler outputHandler;
	private final Object2IntOpenHashMap<ResourceLocation> usedRecipeTracker;
	private int cookTime;
	private int cookTimeTotal;
	private ItemStack mealContainerStack;
	private Component customName;
	private ResourceLocation lastRecipeID;
	private boolean checkNewRecipe;
	private final RecipeManager.CachedCheck<RecipeWrapper, OvenRecipe> quickCheck;

	public OvenBlockEntity(BlockPos pos, BlockState state) {
		super(ExtraDelightBlockEntities.OVEN.get(), pos, state);
		this.inventory = createHandler();
		this.inputHandler = new OvenItemHandler(inventory, Direction.UP);
		this.outputHandler = new OvenItemHandler(inventory, Direction.DOWN);
		this.mealContainerStack = ItemStack.EMPTY;
		this.OvenData = createIntArray();
		this.usedRecipeTracker = new Object2IntOpenHashMap<>();
		this.quickCheck = RecipeManager.createCheck(ExtraDelightRecipes.OVEN.get());
	}

	public static ItemStack getMealFromItem(ItemStack OvenStack) {
		if (!OvenStack.is(ExtraDelightItems.OVEN.get())) {
			return ItemStack.EMPTY;
		}

		return OvenStack.getOrDefault(ModDataComponents.MEAL, ItemStackWrapper.EMPTY).getStack();
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ExtraDelightBlockEntities.OVEN.get(),
				(be, context) -> {
					if (context == Direction.UP) {
						return be.inputHandler;
					}
					return be.outputHandler;
				});
	}

//	public static void takeServingFromItem(ItemStack OvenStack) {
//		if (!OvenStack.is(ExtraDelightItems.OVEN.get())) {
//			return;
//		}
//
//		CompoundTag compound = OvenStack.getTagElement("BlockEntityTag");
//		if (compound != null) {
//			CompoundTag inventoryTag = compound.getCompound("Inventory");
//			if (inventoryTag.contains("Items", 9)) {
//				ItemStackHandler handler = new ItemStackHandler();
//				handler.deserializeNBT(inventoryTag);
//				ItemStack newMealStack = handler.getStackInSlot(6);
//				newMealStack.shrink(1);
//				compound.remove("Inventory");
//				compound.put("Inventory", handler.serializeNBT());
//			}
//		}
//	}

	public static ItemStack getContainerFromItem(ItemStack OvenStack) {
		if (!OvenStack.is(ExtraDelightItems.OVEN.get())) {
			return ItemStack.EMPTY;
		}
		return OvenStack.getOrDefault(ModDataComponents.CONTAINER.get(), ItemStackWrapper.EMPTY).getStack();
	}

	public static void cookingTick(Level level, BlockPos pos, BlockState state, OvenBlockEntity Oven) {
		boolean isHeated = Oven.isHeated(level, pos);
		boolean didInventoryChange = false;
		Optional<RecipeHolder<OvenRecipe>> recipe = Oven.getMatchingRecipe(new RecipeWrapper(Oven.inventory));

		if (isHeated && Oven.hasInput()) {

			if (recipe.isPresent() && Oven.canCook(recipe.get().value())) {
				didInventoryChange = Oven.processCooking(recipe.get(), Oven);
			} else {
				Oven.cookTime = 0;
			}
		} else if (Oven.cookTime > 0) {
			Oven.cookTime = Mth.clamp(Oven.cookTime - 2, 0, Oven.cookTimeTotal);
		}

		ItemStack mealStack = Oven.getMeal();
		if (!mealStack.isEmpty()) {
			if (!Oven.doesMealHaveContainer(mealStack)) {
				Oven.moveMealToOutput();
				didInventoryChange = true;
			} else if (!Oven.inventory.getStackInSlot(CONTAINER_SLOT).isEmpty()) {
//				Oven.useStoredContainersOnMeal(recipe.get().value().shouldConsumeContainer());
				didInventoryChange = true;
			}
		}

		if (didInventoryChange) {
			Oven.inventoryChanged();
		}
	}

	public static void animationTick(Level level, BlockPos pos, BlockState state, OvenBlockEntity Oven) {
		if (Oven.isHeated(level, pos)) {
			RandomSource random = level.random;
			if (random.nextFloat() < 0.2F) {
				double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				double y = (double) pos.getY() + 0.1D;
				double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
				level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
			}
			if (random.nextFloat() < 0.05F) {
				double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
				double y = (double) pos.getY() + 0.5D;
				double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
				double motionY = random.nextBoolean() ? 0.015D : 0.005D;
				level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0D, motionY, 0.0D);
			}
		}

	}

	private static void splitAndSpawnExperience(ServerLevel level, Vec3 pos, int craftedAmount, float experience) {
		int expTotal = Mth.floor((float) craftedAmount * experience);
		float expFraction = Mth.frac((float) craftedAmount * experience);
		if (expFraction != 0.0F && Math.random() < (double) expFraction) {
			++expTotal;
		}

		ExperienceOrb.award(level, pos, expTotal);
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
		super.loadAdditional(compound, registries);
		inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
		cookTime = compound.getInt("CookTime");
		cookTimeTotal = compound.getInt("CookTimeTotal");
		mealContainerStack = ItemStack.parseOptional(registries, compound.getCompound("Container"));
		if (compound.contains("CustomName", 8)) {
			customName = Component.Serializer.fromJson(compound.getString("CustomName"), registries);
		}
		CompoundTag compoundRecipes = compound.getCompound("RecipesUsed");
		for (String key : compoundRecipes.getAllKeys()) {
			usedRecipeTracker.put(ResourceLocation.parse(key), compoundRecipes.getInt(key));
		}
	}

	@Override
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
		super.saveAdditional(compound, registries);
		compound.putInt("CookTime", cookTime);
		compound.putInt("CookTimeTotal", cookTimeTotal);
		compound.put("Container", mealContainerStack.saveOptional(registries));
		if (customName != null) {
			compound.putString("CustomName", Component.Serializer.toJson(customName, registries));
		}
		compound.put("Inventory", inventory.serializeNBT(registries));
		CompoundTag compoundRecipes = new CompoundTag();
		usedRecipeTracker
				.forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
		compound.put("RecipesUsed", compoundRecipes);
	}

	private CompoundTag writeItems(CompoundTag compound, HolderLookup.Provider registries) {
		super.saveAdditional(compound, registries);
		compound.put("Container", mealContainerStack.saveOptional(registries));
		compound.put("Inventory", inventory.serializeNBT(registries));
		return compound;
	}

	public CompoundTag writeMeal(CompoundTag compound, HolderLookup.Provider registries) {
		if (getMeal().isEmpty())
			return compound;

		ItemStackHandler drops = new ItemStackHandler(INVENTORY_SIZE);
		for (int i = 0; i < INVENTORY_SIZE; ++i) {
			drops.setStackInSlot(i, i == MEAL_DISPLAY_SLOT ? inventory.getStackInSlot(i) : ItemStack.EMPTY);
		}
		if (customName != null) {
			compound.putString("CustomName", Component.Serializer.toJson(customName, registries));
		}
		compound.put("Container", mealContainerStack.save(registries));
		compound.put("Inventory", drops.serializeNBT(registries));
		return compound;
	}

	private Optional<RecipeHolder<OvenRecipe>> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
		if (level == null)
			return Optional.empty();
		return hasInput() ? quickCheck.getRecipeFor(inventoryWrapper, this.level) : Optional.empty();
	}

	public ItemStack getContainer() {
		if (!mealContainerStack.isEmpty()) {
			return mealContainerStack;
		} else {
			return getMeal().getCraftingRemainingItem();
		}
	}

	private boolean hasInput() {
		for (int i = 0; i < MEAL_DISPLAY_SLOT; ++i) {
			if (!inventory.getStackInSlot(i).isEmpty())
				return true;
		}
		return false;
	}

	protected boolean canCook(OvenRecipe recipe) {
		if (hasInput()) {
			ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());

			// Vessel Required
			if (inventory.getStackInSlot(CONTAINER_SLOT).getItem() != recipe.getOutputContainer().getItem())
				return false;

			if (resultStack.isEmpty()) {
				return false;
			} else {
				ItemStack storedMealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
				if (storedMealStack.isEmpty()) {
					return true;
				} else if (!ItemStack.isSameItem(storedMealStack, resultStack)) {
					return false;
				} else if (storedMealStack.getCount() + resultStack.getCount() <= inventory
						.getSlotLimit(MEAL_DISPLAY_SLOT)) {
					return true;
				} else {
					return storedMealStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private boolean processCooking(RecipeHolder<OvenRecipe> recipe, OvenBlockEntity oven) {
		if (level == null)
			return false;

		++cookTime;
		cookTimeTotal = recipe.value().getCookTime();
		if (cookTime < cookTimeTotal) {
			return false;
		}

		cookTime = 0;
		mealContainerStack = recipe.value().getOutputContainer();
		ItemStack containerInputStack = inventory.getStackInSlot(CONTAINER_SLOT);
		ItemStack resultStack = recipe.value().getResultItem(this.level.registryAccess());
		ItemStack storedMealStack = inventory.getStackInSlot(OUTPUT_SLOT);
		if (storedMealStack.isEmpty()) {
			inventory.setStackInSlot(OUTPUT_SLOT, resultStack.copy());
		} else if (ItemStack.isSameItem(resultStack, storedMealStack)) {
			storedMealStack.grow(resultStack.getCount());
		}
		oven.setRecipeUsed(recipe);

		for (int i = 0; i < MEAL_DISPLAY_SLOT; ++i) {
			ItemStack slotStack = inventory.getStackInSlot(i);
			if (slotStack.hasCraftingRemainingItem()) {
				Direction direction = getBlockState().getValue(OvenBlock.FACING).getCounterClockWise();
				double x = worldPosition.getX() + 0.5 + (direction.getStepX() * 0.25);
				double y = worldPosition.getY() + 0.7;
				double z = worldPosition.getZ() + 0.5 + (direction.getStepZ() * 0.25);
				ItemUtils.spawnItemEntity(level, inventory.getStackInSlot(i).getCraftingRemainingItem(), x, y, z,
						direction.getStepX() * 0.08F, 0.25F, direction.getStepZ() * 0.08F);
			}
			if (!slotStack.isEmpty())
				slotStack.shrink(1);
			if(recipe.value().shouldConsumeContainer())
				containerInputStack.shrink(1);
		}
		return true;
	}

	@Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceLocation recipeID = recipe.id();
			usedRecipeTracker.addTo(recipeID, 1);
		}
	}

	@Override
	public void awardUsedRecipes(Player player, List<ItemStack> items) {
		List<RecipeHolder<?>> usedRecipes = getUsedRecipesAndPopExperience(player.level(), player.position());
		player.awardRecipes(usedRecipes);
		usedRecipeTracker.clear();
	}

	public List<RecipeHolder<?>> getUsedRecipesAndPopExperience(Level level, Vec3 pos) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Object2IntMap.Entry<ResourceLocation> entry : usedRecipeTracker.object2IntEntrySet()) {
			level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
				list.add(recipe);
				splitAndSpawnExperience((ServerLevel) level, pos, entry.getIntValue(),
						((OvenRecipe) recipe.value()).getExperience());
			});
		}

		return list;
	}

	public boolean isHeated() {
		if (level == null)
			return false;
		return this.isHeated(level, worldPosition);
	}

	public ItemStackHandler getInventory() {
		return inventory;
	}

	public ItemStack getMeal() {
		return inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
	}

	public NonNullList<ItemStack> getDroppableInventory() {
		NonNullList<ItemStack> drops = NonNullList.create();
		for (int i = 0; i < INVENTORY_SIZE; ++i) {
			if (i != MEAL_DISPLAY_SLOT) {
				drops.add(inventory.getStackInSlot(i));
			}
		}
		return drops;
	}

	private void moveMealToOutput() {
		ItemStack mealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
		ItemStack outputStack = inventory.getStackInSlot(OUTPUT_SLOT);
		int mealCount = Math.min(mealStack.getCount(), mealStack.getMaxStackSize() - outputStack.getCount());
		if (outputStack.isEmpty()) {
			inventory.setStackInSlot(OUTPUT_SLOT, mealStack.split(mealCount));
		} else if (outputStack.getItem() == mealStack.getItem()) {
			mealStack.shrink(mealCount);
			outputStack.grow(mealCount);
		}
	}

	private void useStoredContainersOnMeal(boolean b) {
		ItemStack mealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
		ItemStack containerInputStack = inventory.getStackInSlot(CONTAINER_SLOT);
		ItemStack outputStack = inventory.getStackInSlot(OUTPUT_SLOT);

		if (isContainerValid(containerInputStack) && outputStack.getCount() < outputStack.getMaxStackSize()) {
			int smallerStackCount = Math.min(mealStack.getCount(), containerInputStack.getCount());
			int mealCount = Math.min(smallerStackCount, mealStack.getMaxStackSize() - outputStack.getCount());
			if (outputStack.isEmpty()) {
				if (!this.level.isClientSide && b)
					containerInputStack.shrink(1);
				inventory.setStackInSlot(OUTPUT_SLOT, mealStack.split(mealCount));
			} else if (outputStack.getItem() == mealStack.getItem()) {
				mealStack.shrink(mealCount);

				outputStack.grow(mealCount);
			}
		}
	}

//	public ItemStack useHeldItemOnMeal(ItemStack container) {
//		if (isContainerValid(container) && !getMeal().isEmpty()) {
//			container.shrink(1);
//			return getMeal().split(1);
//		}
//		return ItemStack.EMPTY;
//	}

	private boolean doesMealHaveContainer(ItemStack meal) {
		return !mealContainerStack.isEmpty() || meal.hasCraftingRemainingItem();
	}

	public boolean isContainerValid(ItemStack containerItem) {
		if (containerItem.isEmpty())
			return false;
		if (!mealContainerStack.isEmpty()) {
			return ItemStack.isSameItem(containerItem, this.mealContainerStack);
		} else {
			return ItemStack.isSameItem(getMeal().getCraftingRemainingItem(), containerItem);
		}
	}

	@Override
	public Component getName() {
		return customName != null ? customName : Component.translatable(TranslatableKeys.OVEN_CONTAINER);
	}

	@Override
	public Component getDisplayName() {
		return getName();
	}

	@Override
	@Nullable
	public Component getCustomName() {
		return customName;
	}

	public void setCustomName(Component name) {
		customName = name;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
		return new OvenMenu(id, player, this, OvenData);
	}

//	@Override
//	@Nonnull
//	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
//		if (cap.equals(ForgeCapabilities.ITEM_HANDLER)) {
//			if (side == null || side.equals(Direction.UP)) {
//				return inputHandler.cast();
//			} else {
//				return outputHandler.cast();
//			}
//		}
//		return super.getCapability(cap, side);
//	}

	@Override
	public void setRemoved() {
		super.setRemoved();
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return writeItems(new CompoundTag(), registries);
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(INVENTORY_SIZE) {
			@Override
			protected void onContentsChanged(int slot) {
				if (slot >= 0 && slot < MEAL_DISPLAY_SLOT || slot == CONTAINER_SLOT) {
					checkNewRecipe = true;
				}

				inventoryChanged();
			}
		};
	}

	private ContainerData createIntArray() {
		return new ContainerData() {
			@Override
			public int get(int index) {
				return switch (index) {
				case 0 -> OvenBlockEntity.this.cookTime;
				case 1 -> OvenBlockEntity.this.cookTimeTotal;
				default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0 -> OvenBlockEntity.this.cookTime = value;
				case 1 -> OvenBlockEntity.this.cookTimeTotal = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}
}
