package user11681.anvilevents.event.block;

import net.minecraft.block.BlockState;
import user11681.anvil.event.Event;

public abstract class BlockEvent extends Event {
    protected BlockState state;

    public BlockEvent(final BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(final BlockState state) {
        this.state = state;
    }
}
