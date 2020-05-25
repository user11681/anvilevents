package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class DrawTextBackgroundEvent extends RenderHUDElementEvent {
    protected TextRenderer renderer;
    protected int y;
    protected int width;

    public DrawTextBackgroundEvent(final InGameHud hud, final TextRenderer renderer, final int y, final int width) {
        super(hud, Element.TEXT_BACKGROUND);

        this.renderer = renderer;
        this.y = y;
        this.width = width;
    }

    public TextRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(final TextRenderer renderer) {
        this.renderer = renderer;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }
}
