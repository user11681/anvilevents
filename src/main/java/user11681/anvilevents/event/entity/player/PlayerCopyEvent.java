package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyEvent extends PlayerEvent {
    protected ServerPlayerEntity oldPlayer;
    protected boolean alive;

    public PlayerCopyEvent(final PlayerEntity player, final ServerPlayerEntity oldPlayer, final boolean alive) {
        super(player);

        this.oldPlayer = oldPlayer;
        this.alive = alive;
    }

    public ServerPlayerEntity getOldPlayer() {
        return oldPlayer;
    }

    public void setOldPlayer(final ServerPlayerEntity oldPlayer) {
        this.oldPlayer = oldPlayer;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean alive) {
        this.alive = alive;
    }
}
