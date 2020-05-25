package transfarmer.anvilevents.duck.client.mouse;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface MouseDuck {
    void onCursorPosDuck(long window, double x, double y);

    void onMouseButtonDuck(long window, int button, int action, int mods);

    void onMouseScrollDuck(long window, double dX, double dY);
}
