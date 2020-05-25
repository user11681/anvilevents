package transfarmer.anvilevents.event.client.mouse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;

/**
 * This event is called before {@link Mouse#onMouseScroll(long, double, double)}.
 */
@Environment(EnvType.CLIENT)
public class MouseScrollEvent extends MouseEvent {
    protected double dX;
    protected double dY;

    public MouseScrollEvent(final long window, final double x, final double y, final double dX, final double dY) {
        super(window, x, y);

        this.dY = dY;
        this.dX = dX;
    }

    public double getDX() {
        return this.dX;
    }

    public double getDY() {
        return this.dY;
    }

    public void setDX(final double dX) {
        this.dX = dX;
    }

    public void setDY(final double dY) {
        this.dY = dY;
    }
}
