package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.scoreboard.ScoreboardObjective;

@Environment(EnvType.CLIENT)
public class RenderScoreboardSidebarEvent extends RenderHUDElementEvent {
    private ScoreboardObjective objective;

    public RenderScoreboardSidebarEvent(final InGameHud hud, final ScoreboardObjective objective) {
        super(hud, Element.SCOREBOARD_SIDEBAR);

        this.objective = objective;
    }

    public ScoreboardObjective getObjective() {
        return objective;
    }

    public void setObjective(final ScoreboardObjective objective) {
        this.objective = objective;
    }
}
