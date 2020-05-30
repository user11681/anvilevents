package user11681.anvilevents.event.entity.living;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class LivingCollisionEvent extends LivingEntityEvent {
    private final List<Entity> entities;

    public LivingCollisionEvent(final LivingEntity entity, final List<Entity> entities) {
        super(entity);
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }
}
