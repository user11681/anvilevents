package user11681.anvilevents.event.item;

import net.minecraft.item.ItemStack;
import user11681.anvil.event.AnvilEvent;

public abstract class ItemEvent extends AnvilEvent {
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
