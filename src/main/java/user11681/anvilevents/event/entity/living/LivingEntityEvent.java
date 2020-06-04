package user11681.anvilevents.event.entity.living;

import net.minecraft.entity.LivingEntity;
import user11681.anvil.event.AnvilEvent;

public abstract class LivingEntityEvent extends AnvilEvent {
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
