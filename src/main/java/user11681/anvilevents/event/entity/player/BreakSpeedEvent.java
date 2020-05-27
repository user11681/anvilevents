package user11681.anvilevents.event.entity.player;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;

public class BreakSpeedEvent extends PlayerEvent {
    protected BlockState state;
    protected float speed;

    public BreakSpeedEvent(final PlayerEntity player, final BlockState state, final float speed) {
        super(player);

        this.state = state;
        this.speed = speed;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(final BlockState state) {
        this.state = state;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;

        this.setConsume();
    }
}
