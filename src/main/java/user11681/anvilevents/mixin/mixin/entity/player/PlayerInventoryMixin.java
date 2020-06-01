package user11681.anvilevents.mixin.mixin.entity.player;

import java.util.List;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import user11681.anvilevents.mixin.duck.entity.player.PlayerInventoryDuck;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements PlayerInventoryDuck {
    @Accessor
    public abstract List<DefaultedList<ItemStack>> getCombinedInventory();
}
