package user11681.anvilevents.event.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import user11681.anvil.event.AnvilEvent;

public abstract class ScreenRenderEvent extends AnvilEvent {
    protected Screen screen;
    protected int x;
    protected int y;

    public ScreenRenderEvent(final Screen screen, final int x, final int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public void setScreen(final Screen screen) {
        this.screen = screen;
    }

    public int getX() {
        return this.x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int y) {
        this.y = y;
    }
}
