package user11681.anvilevents.event.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UseItemEvent extends ItemEvent {
    private World world;
    private PlayerEntity user;
    private Hand hand;

    public UseItemEvent(final ItemStack itemStack, final World world, final PlayerEntity user, final Hand hand) {
        super(itemStack);

        this.world = world;
        this.user = user;
        this.hand = hand;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    public PlayerEntity getUser() {
        return this.user;
    }

    public void setUser(final PlayerEntity user) {
        this.user = user;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(final Hand hand) {
        this.hand = hand;
    }
}
