package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

public abstract class EntityDamageEvent extends EntityEvent {
    protected DamageSource source;
    protected float damage;

    public EntityDamageEvent(final Entity entity, final DamageSource source, final float damage) {
        super(entity);

        this.source = source;
        this.damage = damage;
    }

    public DamageSource getSource() {
        return source;
    }

    public float getDamage() {
        return damage;
    }

    public static class Pre extends EntityDamageEvent {
        public Pre(final Entity entity, final DamageSource source, final float damage) {
            super(entity, source, damage);
        }

        public void setSource(final DamageSource source) {
            this.source = source;
        }

        public void setDamage(final float damage) {
            this.damage = damage;
        }
    }

    public static class Post extends EntityDamageEvent {
        public Post(final Entity entity, final DamageSource source, final float damage) {
            super(entity, source, damage);
        }
    }
}
