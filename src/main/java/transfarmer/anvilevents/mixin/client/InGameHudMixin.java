package transfarmer.anvilevents.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import transfarmer.anvil.event.EventInvoker;
import transfarmer.anvilevents.duck.client.gui.hud.InGameHudDuck;
import transfarmer.anvilevents.event.client.gui.hud.DrawTextBackgroundEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderCrosshairEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderExperienceBarEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderHUDEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderHeldItemTooltipEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderHotbarEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderHotbarItemEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderJumpBarEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderMountHealthEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderPortalOverlayEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderPumpkinOverlayEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderScoreboardSidebarEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderStatusBarsEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderStatusEffectOverlayEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderVignetteEvent;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin implements InGameHudDuck {
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
    protected abstract void renderHotbarItem(int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack);

    @Override
    public void renderVignetteOverlayDuck(final Entity entity) {
        this.renderVignetteOverlay(entity);
    }

    @Override
    public void drawTextBackgroundDuck(final TextRenderer renderer, final int y, final int width) {
        this.drawTextBackground(renderer, y, width);
    }

    @Override
    public void renderMountJumpBarDuck(final int x) {
        this.renderMountJumpBar(x);
    }

    @Override
    public void renderExprienceBarDuck(final int x) {
        this.renderExperienceBar(x);
    }

    @Override
    public void renderScoreboardSidebarDuck(final ScoreboardObjective objective) {
        this.renderScoreboardSidebar(objective);
    }

    @Override
    public void renderPortalOverlayDuck(final float nauseaStrength) {
        this.renderPortalOverlay(nauseaStrength);
    }

    @Override
    public void renderHotbarItemDuck(final int x, final int y, final float tickDelta, final PlayerEntity player,
                                     final ItemStack itemStack) {
        this.renderHotbarItem(x, y, tickDelta, player, itemStack);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(final float tickDelta, final CallbackInfo info) {
        if (EventInvoker.fire(new RenderHUDEvent(thiz(), tickDelta)).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTextBackground(Lnet/minecraft/client/font/TextRenderer;II)V"))
    private void onDrawTextBackground(final InGameHud hud, final TextRenderer renderer, final int y, final int width) {
        final DrawTextBackgroundEvent event = EventInvoker.fire(new DrawTextBackgroundEvent(hud, renderer, y, width));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).drawTextBackgroundDuck(event.getRenderer(), event.getY(), event.getWidth());
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void onRenderCrosshair(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderCrosshairEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderStatusEffectOverlay(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderStatusEffectOverlayEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbar(final float tickDelta, final CallbackInfo info) {
        if (EventInvoker.fire(new RenderHotbarEvent(thiz(), tickDelta)).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(I)V"))
    private void onRenderMountJumpBar(final InGameHud hud, final int x) {
        final RenderJumpBarEvent event = EventInvoker.fire(new RenderJumpBarEvent(hud, x));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderMountJumpBarDuck(event.getX());
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(I)V"))
    private void onRenderExperienceBar(final InGameHud hud, final int x) {
        final RenderExperienceBarEvent event = EventInvoker.fire(new RenderExperienceBarEvent(hud, x));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderExprienceBarDuck(event.getX());
        }
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void onRenderHeldItemTooltip(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderHeldItemTooltipEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
    private void onRenderScoreboardSidebar(final InGameHud hud, final ScoreboardObjective objective) {
        final RenderScoreboardSidebarEvent event = EventInvoker.fire(new RenderScoreboardSidebarEvent(hud, objective));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderScoreboardSidebarDuck(event.getObjective());
        }
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void onRenderStatusBars(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderStatusBarsEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Inject(method = "renderMountHealth", at = @At("HEAD"), cancellable = true)
    private void onRenderMountHealth(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderMountHealthEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Inject(method = "renderPumpkinOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPumpkinOverlay(final CallbackInfo info) {
        if (EventInvoker.fire(new RenderPumpkinOverlayEvent(thiz())).getResult() == ActionResult.FAIL) {
            info.cancel();
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderVignetteOverlay(Lnet/minecraft/entity/Entity;)V"))
    private void onRenderVignetteOverlay(final InGameHud hud, final Entity entity) {
        final RenderVignetteEvent event = EventInvoker.fire(new RenderVignetteEvent(hud, entity));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderVignetteOverlayDuck(event.getEntity());
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderPortalOverlay(F)V"))
    private void onRenderPortalOverlay(final InGameHud hud, final float nauseaStrength) {
        final RenderPortalOverlayEvent event = EventInvoker.fire(new RenderPortalOverlayEvent(hud, nauseaStrength));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderPortalOverlayDuck(event.getNauseaStrength());
        }
    }

    @Redirect(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V"))
    private void onRenderHotbarItem(final InGameHud hud, final int x, final int y, final float tickDelta,
                                    final PlayerEntity player, final ItemStack itemStack) {
        final RenderHotbarItemEvent event = EventInvoker.fire(new RenderHotbarItemEvent(hud, x, y, tickDelta, player, itemStack));

        if (event.getResult() != ActionResult.FAIL) {
            ((InGameHudDuck) hud).renderHotbarItemDuck(event.getX(), event.getY(), event.getTickDelta(), event.getPlayer(), event.getItemStack());
        }
    }

    protected InGameHud thiz() {
        return (InGameHud) (Object) this;
    }
}
