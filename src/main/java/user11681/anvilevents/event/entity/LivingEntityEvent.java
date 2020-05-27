package user11681.anvilevents.event.entity;

import net.minecraft.entity.LivingEntity;
import user11681.anvil.event.Event;

public abstract class LivingEntityEvent extends Event {
    protected LivingEntity entity;

    public LivingEntityEvent(final LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(final LivingEntity entity) {
        this.entity = entity;
    }
}
