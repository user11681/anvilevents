package user11681.anvilevents.event.client.mouse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;
import user11681.anvil.event.Event;

@Environment(EnvType.CLIENT)
public abstract class MouseEvent extends Event {
    protected final Mouse mouse;

    protected long window;
    protected double x;
    protected double y;

    public MouseEvent(final Mouse mouse, final long window, final double x, final double y) {
        this.mouse = mouse;
        this.window = window;
        this.x = x;
        this.y = y;
    }

    public long getWindow() {
        return this.window;
    }

    public void setWindow(final long window) {
        this.window = window;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }
}
