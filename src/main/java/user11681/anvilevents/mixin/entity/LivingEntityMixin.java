package user11681.anvilevents.mixin.entity;

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
import user11681.anvil.event.EventInvoker;
import user11681.anvilevents.duck.entity.LivingEntityDuck;
import user11681.anvilevents.event.entity.LivingCollisionEvent;
import user11681.anvilevents.event.entity.LivingDeathEvent;
import user11681.anvilevents.event.entity.LivingDropExperienceEvent;
import user11681.anvilevents.event.entity.LivingKnockbackEvent;
import user11681.anvilevents.event.entity.LivingTickEvent;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin implements LivingEntityDuck {
    @Shadow
    protected abstract void dropXp();

    protected boolean knockback = true;
    protected boolean death = true;
    protected boolean xp = true;

/*
    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    protected void preDamage(final DamageSource source, final float damage,
                             final CallbackInfoReturnable<Boolean> info) {
        if (this.damage) {
            final Entity thiz = thiz();
            final EntityDamageEvent event = EventInvoker.fire(new EntityDamageEvent.Pre(thiz, source, damage));

            if (event.isFail()) {
                info.setReturnValue(false);
            } else {
                final Entity entity = event.getEntity();

                this.damage = false;
                info.setReturnValue(entity.damage(event.getSource(), event.getDamage()) && thiz == entity || event.isAccepted());
                this.damage = true;
            }
        }
    }
*/

/*
    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("RETURN"), cancellable = true)
    protected void postDamage(final DamageSource source, final float damage, final CallbackInfoReturnable<Boolean> info) {
        super.postDamage(source, damage, info);
    }
*/

    @Override
    public boolean superDamage(final DamageSource source, final float damage) {
        return super.damage(source, damage);
    }

    @Inject(method = "takeKnockback(Lnet/minecraft/entity/Entity;FDD)V", at = @At(value = "JUMP", opcode = Opcodes.IFEQ, ordinal = 0), cancellable = true)
    private void onTakeKnockback(final Entity attacker, final float speed, final double x, final double z, final CallbackInfo info) {
        if (this.knockback) {
            final LivingKnockbackEvent event = EventInvoker.fire(new LivingKnockbackEvent(thiz(), attacker, speed, x, z));

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
            final LivingDeathEvent event = EventInvoker.fire(new LivingDeathEvent(thiz(), source));

            if (event.getResult() != ActionResult.FAIL) {
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
            final LivingDropExperienceEvent event = EventInvoker.fire(new LivingDropExperienceEvent(thiz()));

            if (!event.isFail()) {
                this.xp = false;
                //noinspection ConstantConditions
                ((LivingEntityMixin) (Object) event.getEntity()).dropXp();
                this.xp = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    protected void preTick(final CallbackInfo info) {
        EventInvoker.fire(new LivingTickEvent.Pre(thiz()));
    }

    @Inject(method = "tick()V", at = @At("RETURN"))
    protected void postTick(final CallbackInfo info) {
        EventInvoker.fire(new LivingTickEvent.Post(thiz()));
    }

    @Inject(method = "tickMovement()V", at = @At("RETURN"))
    protected void onTickMovement(final CallbackInfo info) {
        final List<Entity> nearbyEntities = thiz().world.getEntities(thiz(), thiz().getBoundingBox());

        if (!nearbyEntities.isEmpty()) {
            EventInvoker.fire(new LivingCollisionEvent(thiz(), nearbyEntities));
        }
    }

    private LivingEntity thiz() {
        return (LivingEntity) (Object) this;
    }
}
