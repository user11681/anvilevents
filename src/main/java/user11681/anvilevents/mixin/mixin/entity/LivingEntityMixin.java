package user11681.anvilevents.mixin.mixin.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.mixin.duck.entity.LivingEntityDuck;
import user11681.anvilevents.event.entity.living.LivingCollisionEvent;
import user11681.anvilevents.event.entity.living.LivingDeathEvent;
import user11681.anvilevents.event.entity.living.LivingDropExperienceEvent;
import user11681.anvilevents.event.entity.living.LivingKnockbackEvent;
import user11681.anvilevents.event.entity.living.LivingTickEvent;
import user11681.anvilevents.mixin.Store;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin implements LivingEntityDuck {
    @Shadow
    protected abstract void dropXp();

    protected final LivingEntity thiz = (LivingEntity) (Object) this;

    protected boolean knockback = true;
    protected boolean death = true;
    protected boolean xp = true;

    @Override
    public boolean superDamage(final DamageSource source, final float damage) {
        return super.damage(source, damage);
    }

    @Override
    public boolean superFall(final float distance, final float damageMultiplier) {
        return super.thiz.handleFallDamage(distance, damageMultiplier);
    }

    @Inject(method = "takeKnockback(Lnet/minecraft/entity/Entity;FDD)V", at = @At(value = "JUMP", opcode = Opcodes.IFEQ, ordinal = 0), cancellable = true)
    private void onTakeKnockback(final Entity attacker, final float speed, final double x, final double z, final CallbackInfo info) {
        if (this.knockback) {
            final LivingKnockbackEvent event = new LivingKnockbackEvent(thiz, attacker, speed, x, z).fire();

            if (event.getResult() != ActionResult.FAIL) {
                this.knockback = false;
                event.getEntity().takeKnockback(event.getAttacker(), event.getSpeed(), event.getX(), event.getZ());
                this.knockback = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "onDeath(Lnet/minecraft/entity/damage/DamageSource;)V", at = @At("HEAD"), cancellable = true)
    private void onOnDeath(final DamageSource source, final CallbackInfo info) {
        if (this.death) {
            final LivingDeathEvent event = new LivingDeathEvent(thiz, source).fire();

            if (!event.isFail()) {
                this.death = false;
                event.getEntity().onDeath(event.getSource());
                this.death = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "dropXp", at = @At("HEAD"), cancellable = true)
    protected void onDropXp(final CallbackInfo info) {
        if (this.xp) {
            final LivingDropExperienceEvent event = new LivingDropExperienceEvent(thiz).fire();

            if (!event.isFail()) {
                this.xp = false;
                //noinspection ConstantConditions
                ((LivingEntityMixin) (Object) event.getEntity()).dropXp();
                this.xp = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "handleFallDamage(FF)Z", at = @At("HEAD"), cancellable = true)
    protected void onHandleFallDamage(final float distance, final float damageMultiplier, final CallbackInfoReturnable<Boolean> info) {
        if (Store.fall) {
            super.onHandleFallDamage(distance, damageMultiplier, info);
        }
    }

    @Inject(method = "tickMovement()V", at = @At("RETURN"))
    protected void onTickMovement(final CallbackInfo info) {
        final List<Entity> nearbyEntities = thiz.world.getEntities(thiz, thiz.getBoundingBox());

        if (!nearbyEntities.isEmpty()) {
            new LivingCollisionEvent(thiz, nearbyEntities).fire();
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    protected void preTick(final CallbackInfo info) {
        if (new LivingTickEvent.Pre(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "tick()V", at = @At("RETURN"))
    protected void postTick(final CallbackInfo info) {
        new LivingTickEvent.Post(thiz).fire();
    }
}
