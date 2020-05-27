package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerDropInventoryEvent extends PlayerEvent {
    public PlayerDropInventoryEvent(final PlayerEntity player) {
        super(player);
    }
}
