package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class LivingKnockbackEvent extends LivingEntityEvent {
    protected Entity attacker;
    protected float speed;
    protected double x;
    protected double z;

    public LivingKnockbackEvent(final LivingEntity entity, final Entity attacker, final float speed, final double x,
                                final double z) {
        super(entity);

        this.attacker = attacker;
        this.speed = speed;
        this.x = x;
        this.z = z;
    }

    public Entity getAttacker() {
        return attacker;
    }

    public void setAttacker(final Entity attacker) {
        this.attacker = attacker;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }
}
