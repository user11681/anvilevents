package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderJumpBarEvent extends RenderHUDElementEvent {
    protected int x;

    public RenderJumpBarEvent(final InGameHud hud, final int x) {
        super(hud, Element.JUMP_BAR);

        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }
}
