package com.lance5057.extradelight.displays.candybowl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.lance5057.extradelight.ExtraDelightBlockEntities;
import com.lance5057.extradelight.util.BlockEntityUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class CandyBowlEntity extends BlockEntity {
	public static final String TAG = "inv";

	private final LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createHandler);
	public final static int NUM_SLOTS = 9;

	public CandyBowlEntity(BlockPos pPos, BlockState pBlockState) {
		super(ExtraDelightBlockEntities.CANDY_BOWL.get(), pPos, pBlockState);
	}

	public int getNumSlots() {
		return NUM_SLOTS;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (side != Direction.DOWN)
			if (cap == ForgeCapabilities.ITEM_HANDLER) {
				return handler.cast();
			}
		return super.getCapability(cap, side);
	}

	private IItemHandlerModifiable createHandler() {
		return new ItemStackHandler(NUM_SLOTS) {

			@Override
			public int getSlotLimit(int slot) {
				return 1;
			}
		};
	}

	public void insertItem(ItemStack stack) {
		handler.ifPresent(i -> {
			BlockEntityUtils.Inventory.insertItem(i, stack, NUM_SLOTS);
			this.updateInventory();
		});
	}

	public void extractItem(Player p, InteractionHand h) {
		handler.ifPresent(i -> {
			BlockEntityUtils.Inventory.extractItem(p, i, NUM_SLOTS);
			this.updateInventory();
		});
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
		this.setChanged();
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
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag);
	}

	void readNBT(CompoundTag nbt) {
		final IItemHandler itemInteractionHandler = getCapability(ForgeCapabilities.ITEM_HANDLER)
				.orElseGet(this::createHandler);
		((ItemStackHandler) itemInteractionHandler).deserializeNBT(nbt.getCompound("inventory"));
	}

	CompoundTag writeNBT(CompoundTag tag) {
		IItemHandler itemInteractionHandler = getCapability(ForgeCapabilities.ITEM_HANDLER)
				.orElseGet(this::createHandler);
		tag.put("inventory", ((ItemStackHandler) itemInteractionHandler).serializeNBT());
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

}
