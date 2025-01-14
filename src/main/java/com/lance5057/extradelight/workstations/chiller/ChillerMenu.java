package com.lance5057.extradelight.workstations.chiller;

import java.util.Objects;

import com.lance5057.extradelight.ExtraDelightBlocks;
import com.lance5057.extradelight.ExtraDelightContainers;
import com.lance5057.extradelight.ExtraDelightTags;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import vectorwing.farmersdelight.FarmersDelight;

public class ChillerMenu extends RecipeBookMenu<ChillerRecipeWrapper, ChillerRecipe> {
	public static final ResourceLocation EMPTY_CONTAINER_SLOT_BOWL = ResourceLocation
			.fromNamespaceAndPath(FarmersDelight.MODID, "item/empty_container_slot_bowl");

	public final ChillerBlockEntity tileEntity;
	public final ItemStackHandler inventory;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;

	public ChillerMenu(final int windowId, final Inventory playerInventory, final ChillerBlockEntity tileEntity) {
		super(ExtraDelightContainers.CHILLER_MENU.get(), windowId);
		this.tileEntity = tileEntity;
		this.inventory = tileEntity.getInventory();
		this.level = playerInventory.player.level();
		this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		// Ingredient Slots - 2 Rows x 3 Columns
		int startX = 8;
		int startY = 18;
		int inputStartX = 64;
		int inputStartY = 32;
		int borderSlotSize = 18;
		for (int row = 0; row < 2; ++row) {
			for (int column = 0; column < 2; ++column) {
				this.addSlot(new SlotItemHandler(inventory, (row * 2) + column, inputStartX + (column * borderSlotSize),
						inputStartY + (row * borderSlotSize)));
			}
		}

		// Meal Display
		// this.addSlot(new ChillerMealSlot(inventory, 9, 124, 26+10));

		// Bowl Input
		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.CONTAINER_SLOT, 73, 61 + 7));

		// Bowl Output
		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.OUTPUT_SLOT, 126, 42) {

//			@Override
//			public ItemStack remove(int amount) {
//				return ItemStack.EMPTY.copy();
//			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});

		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.FLUID_IN, 19, 13));
		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.FLUID_OUT, 19, 68));

		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.DRIP_TRAY_OUT, 150, 68));

		this.addSlot(new SlotItemHandler(inventory, ChillerBlockEntity.ICE, 126, 10));

		// Main Player Inventory
		int startPlayerInvY = startY * 4 + 30;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * borderSlotSize),
						startPlayerInvY + (row * borderSlotSize)));
			}
		}

		// Hotbar
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startX + (column * borderSlotSize), 160));
		}
	}

	public FluidTank getFluidTank() {
		return this.tileEntity.getFluidTank();
	}

	public FluidTank getDripTray() {
		return this.tileEntity.getDripTray();
	}

	private static ChillerBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof ChillerBlockEntity) {
			return (ChillerBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	public ChillerMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return stillValid(canInteractWithCallable, playerIn, ExtraDelightBlocks.CHILLER.get());
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
//		int indexContainerInput = 4;
//		int indexOutput = 10;
		int startPlayerInv = ChillerBlockEntity.INVENTORY_SIZE - 1;
		int endPlayerInv = startPlayerInv + 36;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < startPlayerInv) {
				if (!this.moveItemStackTo(itemstack1, startPlayerInv, endPlayerInv, true)) {
					return ItemStack.EMPTY;
				}
			}
			if (index > ChillerBlockEntity.CONTAINER_SLOT) {
				if (itemstack1.is(ExtraDelightTags.TRAYS)) {
					if (!this.moveItemStackTo(itemstack1, ChillerBlockEntity.CONTAINER_SLOT - 1,
							ChillerBlockEntity.CONTAINER_SLOT, false)) {
						return ItemStack.EMPTY;
					}

				} else if (itemstack1.getCapability(Capabilities.FluidHandler.ITEM) != null) {
					IFluidHandlerItem f = itemstack1.getCapability(Capabilities.FluidHandler.ITEM);
					if (f.getFluidInTank(0).isEmpty()) {
						if (!this.moveItemStackTo(itemstack1, ChillerBlockEntity.DRIP_TRAY_OUT - 1,
								ChillerBlockEntity.DRIP_TRAY_OUT, false)) {
							if (!this.moveItemStackTo(itemstack1, ChillerBlockEntity.FLUID_OUT - 1,
									ChillerBlockEntity.FLUID_OUT, false)) {
								return ItemStack.EMPTY;
							}
							return ItemStack.EMPTY;
						}
					} else if (!this.moveItemStackTo(itemstack1, ChillerBlockEntity.FLUID_IN - 1,
							ChillerBlockEntity.FLUID_IN, false)) {
						return ItemStack.EMPTY;
					}

				}

				else if (!this.moveItemStackTo(itemstack1, 0, startPlayerInv, false)) {
					return ItemStack.EMPTY;
				}
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedContents helper) {
		for (int i = 0; i < inventory.getSlots(); i++) {
			helper.accountSimpleStack(inventory.getStackInSlot(i));
		}
	}

	@Override
	public void clearCraftingContent() {
		for (int i = 0; i < 12; i++) {
			this.inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	@Override
	public int getResultSlotIndex() {
		return 11;
	}

	@Override
	public int getGridWidth() {
		return 3;
	}

	@Override
	public int getGridHeight() {
		return 3;
	}

	@Override
	public int getSize() {
		return 10;
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.valueOf("EXTRADELIGHT_COOKING");
	}

	@Override
	public boolean shouldMoveToInventory(int slot) {
		return slot < (getGridWidth() * getGridHeight());
	}

	@Override
	public boolean recipeMatches(RecipeHolder<ChillerRecipe> recipe) {
		return recipe.value().matches(new ChillerRecipeWrapper(inventory, this.getFluidTank().getFluid()), level);
	}
}
