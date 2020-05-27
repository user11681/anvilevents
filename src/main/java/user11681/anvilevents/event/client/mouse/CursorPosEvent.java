package user11681.anvilevents.event.client.mouse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;

/**
 * This event is called before {@link Mouse#onCursorPos(long, double, double)}.
 */
@Environment(EnvType.CLIENT)
public class CursorPosEvent extends MouseEvent {
    public CursorPosEvent(final Mouse mouse, final long window, final double x, final double y) {
        super(mouse, window, x, y);
    }
}
