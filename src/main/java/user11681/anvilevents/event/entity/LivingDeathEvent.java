package user11681.anvilevents.event.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class LivingDeathEvent extends LivingEntityEvent {
    protected final DamageSource source;

    public LivingDeathEvent(final LivingEntity entity, final DamageSource source) {
        super(entity);
        this.source = source;
    }

    public DamageSource getSource() {
        return source;
    }
}
