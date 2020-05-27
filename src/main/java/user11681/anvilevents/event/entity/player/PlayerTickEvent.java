package user11681.anvilevents.event.entity.player;

import net.minecraft.entity.player.PlayerEntity;

public abstract class PlayerTickEvent extends PlayerEvent {
    public PlayerTickEvent(final PlayerEntity player) {
        super(player);
    }

    public static class Pre extends PlayerTickEvent {
        public Pre(final PlayerEntity player) {
            super(player);
        }
    }

    public static class Post extends PlayerTickEvent {
        public Post(final PlayerEntity player) {
            super(player);
        }
    }
}
