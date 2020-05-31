package user11681.anvilevents.mixin.client;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.client.gui.hud.RenderCrosshairEvent;
import user11681.anvilevents.event.client.gui.hud.RenderExperienceBarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHeldTooltipEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHotbarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHotbarItemEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHudEvent;
import user11681.anvilevents.event.client.gui.hud.RenderJumpBarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderMountHealthEvent;
import user11681.anvilevents.event.client.gui.hud.RenderPortalOverlayEvent;
import user11681.anvilevents.event.client.gui.hud.RenderPumpkinOverlayEvent;
import user11681.anvilevents.event.client.gui.hud.RenderScoreboardSidebarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderStatusBarsEvent;
import user11681.anvilevents.event.client.gui.hud.RenderStatusEffectsEvent;
import user11681.anvilevents.event.client.gui.hud.RenderTextBackgroundEvent;
import user11681.anvilevents.event.client.gui.hud.RenderVignetteEvent;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    protected abstract void renderVignetteOverlay(Entity entity);

    @Shadow
    protected abstract void drawTextBackground(TextRenderer textRenderer, int y, int width);

    @Shadow
    public abstract void renderMountJumpBar(int x);

    @Shadow
    public abstract void renderExperienceBar(int x);

    @Shadow
    protected abstract void renderScoreboardSidebar(ScoreboardObjective scoreboardObjective);

    @Shadow
    protected abstract void renderPortalOverlay(float nauseaStrength);

    @Shadow
    protected abstract void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity playerEntity, ItemStack itemStack);

    protected final InGameHud thiz = (InGameHud) (Object) this;
    protected final Map<String, Boolean> available = new HashMap<>();

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(final float tickDelta, final CallbackInfo info) {
        if (new RenderHudEvent(thiz, tickDelta).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void onRenderCrosshair(final CallbackInfo info) {
        if (new RenderCrosshairEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", at = @At(value = "HEAD"), cancellable = true)
    private void onRenderExperienceBar(final int x, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderExperienceBar";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderExperienceBarEvent event = new RenderExperienceBarEvent(thiz, x).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderExperienceBar(event.getX());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void onRenderHeldItemTooltip(final CallbackInfo info) {
        if (new RenderHeldTooltipEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbar(final float tickDelta, final CallbackInfo info) {
        if (new RenderHotbarEvent(thiz, tickDelta).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbarItem(final int x, final int y, final float tickDelta, final PlayerEntity player, final ItemStack itemStack, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderHotbarItem";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderHotbarItemEvent event = new RenderHotbarItemEvent(thiz, x, y, tickDelta, player, itemStack).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderHotbarItem(event.getX(), event.getY(), event.getTickDelta(), event.getPlayer(), event.getItemStack());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderMountHealth", at = @At("HEAD"), cancellable = true)
    private void onRenderMountHealth(final CallbackInfo info) {
        if (new RenderMountHealthEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderMountJumpBar", at = @At(value = "HEAD"), cancellable = true)
    private void onRenderMountJumpBar(final int x, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderMountJumpBar";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderJumpBarEvent event = new RenderJumpBarEvent(thiz, x).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderMountJumpBar(event.getX());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPortalOverlay(final float nauseaStrength, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderPortalOverlay";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderPortalOverlayEvent event = new RenderPortalOverlayEvent(thiz, nauseaStrength).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderPortalOverlay(event.getNauseaStrength());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderPumpkinOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPumpkinOverlay(final CallbackInfo info) {
        if (new RenderPumpkinOverlayEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void onRenderScoreboardSidebar(final ScoreboardObjective objective, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderScoreboardSidebar";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderScoreboardSidebarEvent event = new RenderScoreboardSidebarEvent(thiz, objective).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderScoreboardSidebar(event.getObjective());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void onRenderStatusBars(final CallbackInfo info) {
        if (new RenderStatusBarsEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderStatusEffectOverlay(final CallbackInfo info) {
        if (new RenderStatusEffectsEvent(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "drawTextBackground", at = @At(value = "HEAD"), cancellable = true)
    private void onDrawTextBackground(final TextRenderer renderer, final int y, final int width,
                                      final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "drawTextBackground";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderTextBackgroundEvent event = new RenderTextBackgroundEvent(thiz, renderer, y, width).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.drawTextBackground(event.getRenderer(), event.getY(), event.getWidth());
                available.put(name, true);
            }

            info.cancel();
        }
    }

    @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderVignetteOverlay(final Entity entity, final CallbackInfo info) {
        final Map<String, Boolean> available = this.available;
        final String name = "renderVignetteOverlay";

        if (!available.containsKey(name)) {
            available.put(name, true);
        }

        if (available.get(name)) {
            final RenderVignetteEvent event = new RenderVignetteEvent(thiz, entity).fire();

            if (!event.isFail()) {
                available.put(name, false);
                this.renderVignetteOverlay(event.getEntity());
                available.put(name, true);
            }

            info.cancel();
        }
    }
}
