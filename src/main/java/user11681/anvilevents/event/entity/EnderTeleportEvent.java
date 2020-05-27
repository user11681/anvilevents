package user11681.anvilevents.event.entity;

import net.minecraft.entity.Entity;

public class EnderTeleportEvent extends EntityEvent {
    private double x;
    private double y;
    private double z;

    public EnderTeleportEvent(final Entity entity, final double x, final double y, final double z) {
        super(entity);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }
}
