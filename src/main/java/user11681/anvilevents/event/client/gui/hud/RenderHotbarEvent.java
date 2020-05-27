package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderHotbarEvent extends RenderHudElementEvent {
    protected final float tickDelta;

    public RenderHotbarEvent(final InGameHud hud,  final float tickDelta) {
        super(hud, Element.HOTBAR);

        this.tickDelta = tickDelta;
    }

    public float getTickDelta() {
        return tickDelta;
    }
}
