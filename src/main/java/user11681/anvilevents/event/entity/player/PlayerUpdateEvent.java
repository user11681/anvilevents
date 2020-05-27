package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public abstract class PlayerUpdateEvent extends PlayerEvent {
    protected final ServerWorld world;

    public PlayerUpdateEvent(final PlayerEntity player, final ServerWorld world) {
        super(player);

        this.world = world;
    }

    public ServerWorld getWorld() {
        return world;
    }
}
