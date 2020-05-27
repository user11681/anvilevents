package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import user11681.anvil.event.Event;

public abstract class PlayerEvent extends Event {
    protected PlayerEntity player;

    public PlayerEvent(final PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }
}
