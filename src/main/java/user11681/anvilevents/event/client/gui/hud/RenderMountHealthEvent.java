package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderMountHealthEvent extends RenderHudElementEvent {
    public RenderMountHealthEvent(final InGameHud hud) {
        super(hud, Element.MOUNT_HEALTH);
    }
}
