package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderHeldTooltipEvent extends RenderHudElementEvent {
    public RenderHeldTooltipEvent(final InGameHud hud) {
        super(hud, Element.HELD_TOOLTIP);
    }
}
