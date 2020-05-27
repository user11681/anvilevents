package user11681.anvilevents.event.item;

import net.minecraft.item.ItemStack;
import user11681.anvil.event.Event;

public abstract class ItemEvent extends Event {
    protected ItemStack itemStack;

    public ItemEvent(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
