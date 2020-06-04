package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import user11681.anvil.event.AnvilEvent;

@Environment(EnvType.CLIENT)
public class RenderHudEvent extends AnvilEvent {
    protected final InGameHud hud;
    protected final float tickDelta;

    public RenderHudEvent(final InGameHud hud, final float tickDelta) {
        this.hud = hud;
        this.tickDelta = tickDelta;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
