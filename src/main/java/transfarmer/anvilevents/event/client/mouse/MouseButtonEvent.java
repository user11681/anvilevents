package transfarmer.anvilevents.event.client.mouse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;

/**
 * This event is called before {@link Mouse#onMouseButton(long, int, int, int)}.
 */
@Environment(EnvType.CLIENT)
public class MouseButtonEvent extends MouseEvent {
    protected int button;
    protected int action;
    protected int mods;

    public MouseButtonEvent(final long window, final double x, final double y, final int button, final int action,
                            final int mods) {
        super(window, x, y);

        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    public int getButton() {
        return this.button;
    }

    public int getAction() {
        return this.action;
    }

    public int getMods() {
        return this.mods;
    }

    public void setButton(final int button) {
        this.button = button;
    }

    public void setAction(final int action) {
        this.action = action;
    }

    public void setMods(final int mods) {
        this.mods = mods;
    }
}
