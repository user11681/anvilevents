<img src="https://raw.githubusercontent.com/transfarmer/anvilevents/1.15.2/src/main/resources/assets/anvilevents/logo.png" width="20%"></img>

# anvil events
[![](https://jitpack.io/v/transfarmer/anvilevents.svg)](https://jitpack.io/#transfarmer/anvilevents)

an API that implements events for [anvil](https://github.com/transfarmer/anvil).

## usage
### including anvil events with Gradle
Click the JitPack banner above; replace `implementation` with `modImplementation`.<br>
If you want to include this mod as a jar-in-jar dependency, then also add this below `modImplementation`:
```groovy
include "com.github.transfarmer:anvilevents:${VERSION}"
```
, where `${VERSION}` is your chosen version from above. Use `1.15.2-SNAPSHOT` for the latest commit.

Follow the same procedure for anvil (described on its GitHub page).

### registering event listeners
Follow the guide [here](https://github.com/transfarmer/anvil#listening-to-events).

### a list of included events
can be found in the `transfarmer.anvilevents.event` package:
- ClientTickEvent, called at the head of `MinecraftClient#tick()`
<details>
<summary>
MouseEvent derivatives;
</summary>

- CursorPosEvent, called before `Mouse#onCursorPos(long, double, double)` on change in mouse position;
- MouseButtonEvent, called before `Mouse#onMouseButton(long, int, int, int)` on mouse click;
- MouseScrollEvent, called before `Mouse#onMouseScroll(long, double, double)` on mouse scroll; and
</details>
<details>
<summary>
RenderHUDElementEvent derivatives: one for each element; called at the head of its method;
</summary>
<ul>
<li>RenderCrosshairEvent in <code>InGameHud#renderCrosshair()</code>;</li>
<li>RenderExperienceBarEvent in <code>InGameHud#renderExperienceBar(int)</code>;</li>
<li>RenderHeldTooltipEvent in <code>InGameHud#renderHeldItemTooltip()</code>;</li>
<li>RenderHotbarEvent in <code>InGameHud#renderHotbar(float)</code>;</li>
<li>RenderHotbarItemEvent in <code>InGameHud#renderHotbarItem(int, int, float)</code>;</li>
<li>RenderJumpBarEvent in <code>InGameHud#renderMountJumpBar(int)</code>;</li>
<li>RenderMountHealthEvent in <code>InGameHud#renderMountHealth()</code>;</li>
<li>RenderPortalOverlayEvent in <code>InGameHud#renderPortalOverlay(float)</code>;</li>
<li>RenderPumpkinOverlayEvent in <code>InGameHud#renderPumpkinOverlay()</code>;</li>
<li>RenderScoreboardSidebarEvent in <code>InGameHud#renderScoreboardSidebar(ScoreboardObjective)</code>;</li>
<li>RenderStatusBarsEvent in <code>InGameHud#renderStatusBars()</code>;</li>
<li>RenderStatusEffectsEvent in <code>InGameHud#renderStatusEffectOverlay()</code>;</li>
<li>RenderTextBackgroundEvent in <code>InGameHud#drawTextBackground(TextRenderer, int, int)</code>; and</li>
<li>RenderVignetteEvent in <code>InGameHud#renderVignetteOverlay(Entity)</code>;</li>
</ul>
</details>

- RenderHUDEvent, called at the head of `InGameHud#render(float)`;
- TranslationEvent, called in `I18n#translate(String, Object...)` and `Language#translate(String)`.
