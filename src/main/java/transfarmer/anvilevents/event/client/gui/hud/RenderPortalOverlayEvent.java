package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;

@Environment(EnvType.CLIENT)
public class RenderPortalOverlayEvent extends RenderHUDElementEvent {
    protected float nauseaStrength;

    public RenderPortalOverlayEvent(final InGameHud hud, final float nauseaStrength) {
        super(hud, Element.PORTAL);

        this.nauseaStrength = nauseaStrength;
    }

    public float getNauseaStrength() {
        return nauseaStrength;
    }

    public void setNauseaStrength(final float nauseaStrength) {
        this.nauseaStrength = nauseaStrength;
    }
}
