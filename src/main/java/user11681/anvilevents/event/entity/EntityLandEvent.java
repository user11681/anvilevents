package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;

public class EntityLandEvent extends EntityEvent {
    protected float distance;
    protected float damageMultiplier;

    public EntityLandEvent(final Entity entity, final float distance, final float damageMultiplier) {
        super(entity);

        this.distance = distance;
        this.damageMultiplier = damageMultiplier;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(final float distance) {
        this.distance = distance;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(final float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
}
