package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;
import user11681.anvil.event.Event;

public abstract class EntityEvent extends Event {
    protected Entity entity;

    public EntityEvent(final Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
