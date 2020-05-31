package user11681.anvilevents.event.block;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;

public abstract class BlockDropEvent extends BlockEvent {
    protected List<ItemStack> droppedStacks;

    public BlockDropEvent(final BlockState state, final List<ItemStack> droppedStacks) {
        super(state);

        this.droppedStacks = droppedStacks;
    }

    public List<ItemStack> getDroppedStacks() {
        return droppedStacks;
    }

    public void setDroppedStacks(final List<ItemStack> droppedStacks) {
        this.droppedStacks = droppedStacks;
        this.setAccepted();
    }
}
