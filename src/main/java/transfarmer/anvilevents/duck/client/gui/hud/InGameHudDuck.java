package transfarmer.anvilevents.duck.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreboardObjective;

@Environment(EnvType.CLIENT)
public interface InGameHudDuck {
    void renderVignetteOverlayDuck(Entity entity);

    void drawTextBackgroundDuck(TextRenderer renderer, int y, int width);

    void renderMountJumpBarDuck(int x);

    void renderExprienceBarDuck(int x);

    void renderScoreboardSidebarDuck(ScoreboardObjective objective);

    void renderPortalOverlayDuck(float nauseaStrength);

    void renderHotbarItemDuck(int x, int y, float tickDelta, PlayerEntity player, ItemStack itemStack);
}
