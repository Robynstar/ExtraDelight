package com.lance5057.extradelight.workstations.mixingbowl;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.lance5057.extradelight.ExtraDelightBlockEntities;
import com.lance5057.extradelight.ExtraDelightRecipes;
import com.lance5057.extradelight.util.BlockEntityUtils;
import com.lance5057.extradelight.workstations.mixingbowl.recipes.MixingBowlRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class MixingBowlBlockEntity extends BlockEntity {
	public static final String INV_TAG = "inv";

	private final ItemStackHandler items = createHandler();
	private final Lazy<IItemHandlerModifiable> itemHandler = Lazy.of(() -> items);
	public static final int NUM_SLOTS = 33;
	
	public static final String FLUID_TAG = "tank";
	
	private final FluidTank fluids = createFluidHandler();

	private int stirs = 0;
	public boolean complete = false;
	public ItemStack containerItem = ItemStack.EMPTY;

	public MixingBowlBlockEntity(BlockPos pPos, BlockState pState) {
		super(ExtraDelightBlockEntities.MIXING_BOWL.get(), pPos, pState);
	}

	private FluidTank createFluidHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	public IItemHandlerModifiable getItemHandler() {
		return itemHandler.get();
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(NUM_SLOTS) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				if (slot != 32)
					return 1;
				else
					return 64;
			}

			@Override
			protected void onContentsChanged(int slot) {
				zeroProgress();
				updateInventory();
			}

		};
	}

	public int getLastFilledSlot(IItemHandlerModifiable inventory) {
		if (inventory.getStackInSlot(31) != ItemStack.EMPTY)
			return 31;
		for (int i = 0; i <= 31; i++) {
			if (inventory.getStackInSlot(i) == ItemStack.EMPTY)
				return i - 1;
		}
		return -1;
	}

	public void extractItem(Player playerEntity, IItemHandlerModifiable inventory) {
		int i = getLastFilledSlot(inventory);
		if (i != -1) {
			ItemStack itemStack = inventory.extractItem(i, 1, false);
			playerEntity.addItem(itemStack);
			updateInventory();
			return;
		}

		updateInventory();
	}

	public void insertItem(ItemStack stack) {
		BlockEntityUtils.Inventory.insertItem(items, stack, NUM_SLOTS);
		this.updateInventory();
	}

	public void extractItem(Player p) {
		BlockEntityUtils.Inventory.extractItem(p, items, NUM_SLOTS);
		this.updateInventory();
	}

	public void zeroProgress() {
		stirs = 0;
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
		this.setChanged();
	}

//	public ItemStack getInsertedItem() {
//		return handler.map(inventory -> inventory.getStackInSlot(0)).orElse(ItemStack.EMPTY);
//	}

	public int getStirs() {
		return stirs;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag nbt = super.getUpdateTag();

		writeNBT(nbt);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		readNBT(tag);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag tag = new CompoundTag();

		writeNBT(tag);

		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag);
	}

	void readNBT(CompoundTag nbt) {
		if (nbt.contains(INV_TAG)) {
			items.deserializeNBT(nbt.getCompound(INV_TAG));
		}

		this.stirs = nbt.getInt("stirs");
		containerItem = ItemStack.of(nbt.getCompound("usedItem"));
		this.complete = nbt.getBoolean("complete");
	}

	CompoundTag writeNBT(CompoundTag tag) {

		tag.put(INV_TAG, items.serializeNBT());

		tag.putInt("stirs", this.stirs);

		tag.put("usedItem", containerItem.save(new CompoundTag()));
		tag.putBoolean("complete", this.complete);

		return tag;
	}

	@Override
	public void load(@Nonnull CompoundTag nbt) {
		super.load(nbt);
		readNBT(nbt);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt) {
		super.saveAdditional(nbt);
		writeNBT(nbt);
	}

	public Optional<RecipeHolder<MixingBowlRecipe>> matchRecipe(ItemStack... itemstack) {
		if (this.level != null) {
			return level.getRecipeManager().getRecipeFor(ExtraDelightRecipes.MIXING_BOWL.get(),
					new SimpleContainer(itemstack), level);
		}
		return Optional.empty();

	}

	private ItemStack[] getItems() {
		int s = getLastFilledSlot(items);
		if (s != -1) {
			ItemStack[] stacks = new ItemStack[s + 1];
			for (int i = 0; i < s + 1; i++) {
				stacks[i] = items.getStackInSlot(i);
			}
			return stacks;
		}
		return new ItemStack[0];
	}

	private void clearItems() {
		for (int i = 0; i < 32; i++) {
			items.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	protected Optional<RecipeHolder<MixingBowlRecipe>> matchRecipe() {
		if (level != null) {

			Optional<RecipeHolder<MixingBowlRecipe>> recipe = level.getRecipeManager()
					.getRecipeFor(ExtraDelightRecipes.MIXING_BOWL.get(), new SimpleContainer(getItems()), level);

			// setRecipe(recipe);
			return recipe;
		}
		return Optional.empty();

	}

	public InteractionResult mix(Player player) {

		Optional<RecipeHolder<MixingBowlRecipe>> recipeOptional = matchRecipe(getItems());
		if (recipeOptional.isPresent()) {
			MixingBowlRecipe recipe = recipeOptional.get().value();

			if (this.stirs < recipe.getStirs()) {
				stirs++;

				ItemStack[] items = getItems();
				for (int i = 0; i < 1 + level.random.nextInt(4); i++)
					level.addParticle(
							new ItemParticleOption(ParticleTypes.ITEM,
									items[items.length > 1 ? level.random.nextInt(items.length - 1) : 0]),
							worldPosition.getX() + 0.25f + level.random.nextDouble() / 2,
							worldPosition.getY() - 0.5f - level.random.nextDouble(),
							worldPosition.getZ() + 0.25f + level.random.nextDouble() / 2, 0, 0, 0);

				level.playSound(player, worldPosition, SoundEvents.STONE_HIT, SoundSource.BLOCKS, 1, 1);
			} else {
				this.containerItem = recipe.getUsedItem().copy();

				ItemStack i = recipe.getResultItem(player.level().registryAccess()).copy();

				i.onCraftedBy(player.level(), player, 1);
//				NeoForgeEventFactory.firePlayerCraftingEvent(player, i, new RecipeWrapper(items));

				dropContainers(items, player);
				clearItems();
				items.setStackInSlot(32, i);
				complete = true;
			}
			updateInventory();
		}

		return InteractionResult.SUCCESS;
	}

	public boolean testContainerItem(ItemStack stack) {
		if (this.containerItem.isEmpty())
			return true;
		return this.containerItem.getItem() == stack.getItem();
	}

	public InteractionResult scoop(Player player, InteractionHand pHand) {
		ItemStack r = items.extractItem(32, 1, false);

		if (!player.addItem(r)) {
			level.addFreshEntity(new ItemEntity(level, getBlockPos().getX() + 0.5f, getBlockPos().getY() + 0.5f,
					getBlockPos().getZ() + 0.5f, r, 0, 0, 0));
		}

		if (!this.containerItem.isEmpty()) {
			ItemStack h = player.getItemInHand(pHand);
			h.setCount(h.getCount() - 1);
		}

		if (items.getStackInSlot(32).isEmpty()) {
			this.containerItem = ItemStack.EMPTY;

			complete = false;
		}

		return InteractionResult.SUCCESS;

	}

	private void dropContainers(@NotNull IItemHandlerModifiable inv, Player player) {
		for (int i = 0; i < 32; i++) {
			player.addItem(inv.getStackInSlot(i).getCraftingRemainingItem());

		}
	}
}