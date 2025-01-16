package com.lance5057.extradelight.items.dynamicfood.api;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.google.common.collect.Iterables;
import com.lance5057.extradelight.ExtraDelight;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

public class DynamicItemComponent implements TooltipProvider {
	public static final DynamicItemComponent EMPTY = new DynamicItemComponent(NonNullList.create());
	public static final Codec<DynamicItemComponent> CODEC = DynamicItemComponent.Slot.CODEC.sizeLimitedListOf(256)
			.xmap(DynamicItemComponent::fromSlots, DynamicItemComponent::asSlots);
	public static final StreamCodec<RegistryFriendlyByteBuf, DynamicItemComponent> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC
			.apply(ByteBufCodecs.list(256)).map(DynamicItemComponent::new, p_331691_ -> p_331691_.items);
	private NonNullList<ItemStack> items;

	public NonNullList<ItemStack> getItems() {
		return items;
	}

	public void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	public void addItem(ItemStack stack) {
		this.items.add(stack);
	}

	private final int hashCode;

	private DynamicItemComponent(NonNullList<ItemStack> items) {
		if (items.size() > 256) {
			throw new IllegalArgumentException("Got " + items.size() + " items, but maximum is 256");
		} else {
			this.items = items;
			this.hashCode = ItemStack.hashStackList(items);
		}
	}

	private DynamicItemComponent(int size) {
		this(NonNullList.withSize(size, ItemStack.EMPTY));
	}

	private DynamicItemComponent(List<ItemStack> items) {
		this(items.size());

		for (int i = 0; i < items.size(); i++) {
			this.items.set(i, items.get(i));
		}
	}

	private static DynamicItemComponent fromSlots(List<DynamicItemComponent.Slot> slots) {
		OptionalInt optionalint = slots.stream().mapToInt(DynamicItemComponent.Slot::index).max();
		if (optionalint.isEmpty()) {
			return EMPTY;
		} else {
			DynamicItemComponent DynamicItemComponent = new DynamicItemComponent(optionalint.getAsInt() + 1);

			for (DynamicItemComponent.Slot DynamicItemComponent$slot : slots) {
				DynamicItemComponent.items.set(DynamicItemComponent$slot.index(), DynamicItemComponent$slot.item());
			}

			return DynamicItemComponent;
		}
	}

	public static DynamicItemComponent fromItems(List<ItemStack> items) {
		int i = findLastNonEmptySlot(items);
		if (i == -1) {
			return EMPTY;
		} else {
			DynamicItemComponent DynamicItemComponent = new DynamicItemComponent(i + 1);

			for (int j = 0; j <= i; j++) {
				DynamicItemComponent.items.set(j, items.get(j).copy());
			}

			return DynamicItemComponent;
		}
	}

	private static int findLastNonEmptySlot(List<ItemStack> items) {
		for (int i = items.size() - 1; i >= 0; i--) {
			if (!items.get(i).isEmpty()) {
				return i;
			}
		}

		return -1;
	}

	private List<DynamicItemComponent.Slot> asSlots() {
		List<DynamicItemComponent.Slot> list = new ArrayList<>();

		for (int i = 0; i < this.items.size(); i++) {
			ItemStack itemstack = this.items.get(i);
			if (!itemstack.isEmpty()) {
				list.add(new DynamicItemComponent.Slot(i, itemstack));
			}
		}

		return list;
	}

	public void copyInto(NonNullList<ItemStack> list) {
		for (int i = 0; i < list.size(); i++) {
			ItemStack itemstack = i < this.items.size() ? this.items.get(i) : ItemStack.EMPTY;
			list.set(i, itemstack.copy());
		}
	}

	public ItemStack copyOne() {
		return this.items.isEmpty() ? ItemStack.EMPTY : this.items.get(0).copy();
	}

	public Stream<ItemStack> stream() {
		return this.items.stream().map(ItemStack::copy);
	}

	public Stream<ItemStack> nonEmptyStream() {
		return this.items.stream().filter(p_331322_ -> !p_331322_.isEmpty()).map(ItemStack::copy);
	}

	public Iterable<ItemStack> nonEmptyItems() {
		return Iterables.filter(this.items, p_331420_ -> !p_331420_.isEmpty());
	}

	public Iterable<ItemStack> nonEmptyItemsCopy() {
		return Iterables.transform(this.nonEmptyItems(), ItemStack::copy);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else {
			if (other instanceof DynamicItemComponent DynamicItemComponent
					&& ItemStack.listMatches(this.items, DynamicItemComponent.items)) {
				return true;
			}

			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.hashCode;
	}

	/**
	 * Neo: {@return the number of slots in this container}
	 */
	public int getSlots() {
		return this.items.size();
	}

	/**
	 * Neo: Gets a copy of the stack at a particular slot.
	 *
	 * @param slot The slot to check. Must be within [0, {@link #getSlots()}]
	 * @return A copy of the stack in that slot
	 * @throws UnsupportedOperationException if the provided slot index is
	 *                                       out-of-bounds.
	 */
	public ItemStack getStackInSlot(int slot) {
		validateSlotIndex(slot);
		return this.items.get(slot).copy();
	}

	/**
	 * Neo: Throws {@link UnsupportedOperationException} if the provided slot index
	 * is invalid.
	 */
	private void validateSlotIndex(int slot) {
		if (slot < 0 || slot >= getSlots()) {
			throw new UnsupportedOperationException("Slot " + slot + " not in valid range - [0," + getSlots() + ")");
		}
	}

	static record Slot(int index, ItemStack item) {
		public static final Codec<DynamicItemComponent.Slot> CODEC = RecordCodecBuilder.create(p_331695_ -> p_331695_
				.group(Codec.intRange(0, 255).fieldOf("slot").forGetter(DynamicItemComponent.Slot::index),
						ItemStack.CODEC.fieldOf("item").forGetter(DynamicItemComponent.Slot::item))
				.apply(p_331695_, DynamicItemComponent.Slot::new));
	}

	@Override
	public void addToTooltip(TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		if (this.getItems() != null && !this.getItems().isEmpty()) {
			NonNullList<ItemStack> i = this.getItems();
			List<Component> c = new ArrayList<Component>();
			tooltipAdder.accept(Component.translatable(ExtraDelight.MOD_ID + ".tooltip.dynamic"));
			for (ItemStack s : i) {
				tooltipAdder.accept(s.getDisplayName());
			}

		} else
			tooltipAdder.accept(Component.translatable(ExtraDelight.MOD_ID + ".tooltip.dynamic.empty"));
	}
}
