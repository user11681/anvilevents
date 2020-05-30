package user11681.anvilevents.event.entity.player;

import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public class PlayerDropInventoryEvent extends PlayerEvent {
    protected final List<DefaultedList<ItemStack>> drops;

    public PlayerDropInventoryEvent(final PlayerEntity player, final List<DefaultedList<ItemStack>> drops) {
        super(player);

        this.drops = drops;
    }

    public List<DefaultedList<ItemStack>> getDrops() {
        return drops;
    }
}
