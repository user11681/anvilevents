package user11681.anvilevents.event.block;

import net.minecraft.block.Block;
import user11681.anvil.event.Event;

public abstract class BlockEvent extends Event {
    protected Block block;

    public BlockEvent(final Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(final Block block) {
        this.block = block;
    }
}
