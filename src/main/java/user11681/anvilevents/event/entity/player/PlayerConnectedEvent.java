package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class PlayerConnectedEvent extends PlayerUpdateEvent {
    public PlayerConnectedEvent(final PlayerEntity player, final ServerWorld world) {
        super(player, world);
    }
}
