package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;

public abstract class EntityTickEvent extends EntityEvent {
    public EntityTickEvent(final Entity entity) {
        super(entity);
    }

    public static class Pre extends EntityTickEvent {
        public Pre(final Entity entity) {
            super(entity);
        }
    }

    public static class Post extends EntityTickEvent {
        public Post(final Entity entity) {
            super(entity);
        }
    }
}
