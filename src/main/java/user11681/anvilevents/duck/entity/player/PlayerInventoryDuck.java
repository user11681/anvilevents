package user11681.anvilevents.duck.entity.player;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public interface PlayerInventoryDuck {
    List<DefaultedList<ItemStack>> getCombinedInventory();
}
