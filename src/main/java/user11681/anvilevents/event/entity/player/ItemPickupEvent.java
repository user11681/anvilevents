package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ItemPickupEvent extends PlayerEvent {
    protected final ItemEntity itemEntity;

    public ItemPickupEvent(final PlayerEntity player, final ItemEntity itemEntity) {
        super(player);

        this.itemEntity = itemEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }
}
