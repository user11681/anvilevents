package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderTextBackgroundEvent extends RenderHudElementEvent {
    protected TextRenderer renderer;
    protected int y;
    protected int x;
    protected int color;

    public RenderTextBackgroundEvent(final InGameHud hud, final TextRenderer renderer, final int y, final int x, final int color) {
        super(hud, Element.TEXT_BACKGROUND);

        this.renderer = renderer;
        this.y = y;
        this.x = x;
        this.color = color;
    }

    public TextRenderer getRenderer() {
        return this.renderer;
    }

    public void setRenderer(final TextRenderer renderer) {
        this.renderer = renderer;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(final int color) {
        this.color = color;
    }
}
