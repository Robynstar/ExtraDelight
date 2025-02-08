package com.lance5057.extradelight.displays.knife;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

import com.lance5057.extradelight.ExtraDelightBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.tag.ModTags;

public class KnifeBlockEntity extends BlockEntity {

	private final LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createHandler);
	public static final String TAG = "inv";

	public static final int NUM_SLOTS = 4;

	public KnifeBlockEntity(BlockPos pPos, BlockState pBlockState) {

		super(ExtraDelightBlockEntities.KNIFE_BLOCK.get(), pPos, pBlockState);

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
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return 1;
			}

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				return stack.is(ModTags.KNIVES);
			}
		};
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

	public String getDisplayName() {
		return "screen.knife_block.name";
	}
}
