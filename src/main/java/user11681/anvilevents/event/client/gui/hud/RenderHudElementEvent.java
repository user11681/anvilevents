package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import user11681.anvil.event.Event;

@Environment(EnvType.CLIENT)
public abstract class RenderHudElementEvent extends Event {
    protected final InGameHud hud;
    protected final Element element;

    public RenderHudElementEvent(final InGameHud hud, final Element element) {
        this.hud = hud;
        this.element = element;
    }

    public InGameHud getHud() {
        return hud;
    }

    public Element getElement() {
        return element;
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static class Element {
        public static final Element CROSSHAIR = new Element();
        public static final Element EXPERIENCE_BAR = new Element();
        public static final Element HELD_TOOLTIP = new Element();
        public static final Element HOTBAR = new Element();
        public static final Element HOTBAR_ITEM= new Element();
        public static final Element JUMP_BAR = new Element();
        public static final Element MOUNT_HEALTH = new Element();
        public static final Element PORTAL = new Element();
        public static final Element PUMPKIN = new Element();
        public static final Element SCOREBOARD_SIDEBAR = new Element();
        public static final Element STATUS_BARS = new Element();
        public static final Element STATUS_EFFECTS = new Element();
        public static final Element TEXT_BACKGROUND = new Element();
        public static final Element VIGNETTE = new Element();
    }
}
