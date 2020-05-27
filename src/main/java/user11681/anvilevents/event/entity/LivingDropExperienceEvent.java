package user11681.anvilevents.event.entity;

import net.minecraft.entity.LivingEntity;

public class LivingDropExperienceEvent extends LivingEntityEvent {
    public LivingDropExperienceEvent(final LivingEntity entity) {
        super(entity);
    }
}
