<img src="https://raw.githubusercontent.com/user11681/anvilevents/1.15.2/src/main/resources/assets/anvilevents/logo.png" width="20%"></img>

# anvil events
[![](https://jitpack.io/v/user11681/anvilevents.svg)](https://jitpack.io/#user11681/anvilevents)

an API that implements events for [anvil](https://github.com/user11681/anvil).

## usage
### including anvil events with Gradle
Click the JitPack banner above; replace `implementation` with `modImplementation`.<br>
If you want to include this mod as a jar-in-jar dependency, then also add this below `modImplementation`:
```groovy
include "com.github.user11681:anvilevents:${VERSION}"
```
, where `${VERSION}` is your chosen version from above. Use `1.15.2-SNAPSHOT` for the latest commit.

Follow the same procedure for anvil (described on its GitHub page).

### registering event listeners
Follow the guide [here](https://github.com/user11681/anvil#listening-to-events).

### a list of included events
can be found in the `user11681.anvilevents.event` package:

<details>
<summary><code>BlockEvent</code>s;</summary>

- `BlockDropEvent`;
</details>

- `ClientTickEvent`, called at the head of `MinecraftClient#tick()`

<details>
<summary><code>EntityEvent</code>s;</summary>

- `EnderTeleportEvent`, called in `Entity#requestTeleport(double, double, double)`;
- `EntityDamageEvent.Pre` called at the head of `Entity#damage(DamageSource, float)`;
- `EntityDamageEvent.Post`, called before returning from `Entity#damage(DamageSource, float)`; and
- `EntityLandEvent`, called at the head of `Block#onLandedUpon(World, BlockPos, Entity, float)`;
</details>

<details>
<summary><code>LivingEvent</code>s</summary>

- `LivingCollisionEvent`, called before returning from `LivingEntity#tickMovement()`;
- `LivingDeathEvent`, called at the head of `LivingEntity#onDeath(DamageSource)`;
- `LivingDropExperienceEvent`, called at the head of `LivingEntity#dropXp()`;
- `LivingKnockbackEvent`, called in `LivingEntity#takeKnockback(Entity, float, double, double)`;
- `LivingTickEvent.Pre`, called at the head of `LivingEntity#tick()`;
- `LivingTickEvent.Post`, called before returning from `LivingEntity#tick()`;
</details>

<details>
<summary><code>PlayerEvent</code>s</summary>

- `BreakSpeedEvent`, called before returning from `PlayerEntity#getBlockBreakingSpeed(BlockState)`;
- `ItemPickupEvent`, called in `ItemEntity#onPlayerCollision(PlayerEntity)`;
- `PlayerChangedDimensionEvent`, called at the head of `ServerWorld#onPlayerChangeDimension(ServerPlayerEntity)`;
- `PlayerConnectedEvent`, called at the head of `ServerWorld#onPlayerConnected(ServerPlayerEntity)`;
- `PlayerRespawnedEvent`, called at the head of `ServerWorld#onPlayerRespawned(ServerPlayerEntity)`;
- `PlayerCopyEvent`, called at the head of `ServerPlayerEntity#copyFrom(ServerPlayerEntity, boolean)`;
- `PlayerDropInventoryEvent`, called at the head of `PlayerEntity#dropInventory()`;
- `PlayerTickEvent.Pre`, called at the head of `PlayerEntity#tick()`;
- `PlayerTickEvent.Post`, called before returning from `PlayerEntity#tick()`; and
- `UseBlockEvent`, called at the head of `BlockState#onUse(World, PlayerEntity, Hand, BlockHitResult)`;
</details>

<details>
<summary><code>ItemEvent</code>s</summary>

- `ItemTooltipEvent`, called before returning from `ItemStack#getTooltip(PlayerEntity, TooltipContext)`; and
- `UseItemEvent`, called at the head of `Item#use(World, PlayerEntity, Hand)`;
</details>

<details>
<summary><code>MouseEvent</code>s;</summary>

- `CursorPosEvent`, called in `Mouse#onCursorPos(long, double, double)` on change in mouse position;
- `MouseButtonEvent`, called in `Mouse#onMouseButton(long, int, int, int)` on mouse click; and
- `MouseScrollEvent`, called in `Mouse#onMouseScroll(long, double, double)` on mouse scroll;
</details>

<details>
<summary><code>RenderHudElementEvent</code>s: one for each element, called at the head of its method;</summary>

- `RenderCrosshairEvent` in `InGameHud#renderCrosshair()`;
- `RenderExperienceBarEvent` in `InGameHud#renderExperienceBar(int)`;
- `RenderHeldTooltipEvent` in `InGameHud#renderHeldItemTooltip()`;
- `RenderHotbarEvent` in `InGameHud#renderHotbar(float)`;
- `RenderHotbarItemEvent` in `InGameHud#renderHotbarItem(int, int, float)`;
- `RenderJumpBarEvent` in `InGameHud#renderMountJumpBar(int)`;
- `RenderMountHealthEvent` in `InGameHud#renderMountHealth()`;
- `RenderPortalOverlayEvent` in `InGameHud#renderPortalOverlay(float)`;
- `RenderPumpkinOverlayEvent` in `InGameHud#renderPumpkinOverlay()`;
- `RenderScoreboardSidebarEvent` in `InGameHud#renderScoreboardSidebar(ScoreboardObjective)`;
- `RenderStatusBarsEvent` in `InGameHud#renderStatusBars()`;
- `RenderStatusEffectsEvent` in `InGameHud#renderStatusEffectOverlay()`;
- `RenderTextBackgroundEvent` in `InGameHud#drawTextBackground(TextRenderer, int, int)`; and
- `RenderVignetteEvent` in `InGameHud#renderVignetteOverlay(Entity)`;
</details>

- `RenderHudEvent`, called at the head of `InGameHud#render(float)`;
- `RenderTooltipEvent.Pre` and `RenderTooltipEvent.Post`,
called at the head of and before returning from `Screen#renderTooltip(List<String>, x, y)` respectively;
- 
- `TranslationEvent`, called in `I18n#translate(String, Object...)` and `Language#translate(String)` before returning.
