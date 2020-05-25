package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderStatusBarsEvent extends RenderHudElementEvent {
    public RenderStatusBarsEvent(final InGameHud hud) {
        super(hud, Element.STATUS_BARS);
    }
}
