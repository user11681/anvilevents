package user11681.anvilevents.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.duck.entity.EntityDuck;
import user11681.anvilevents.duck.entity.LivingEntityDuck;
import user11681.anvilevents.event.entity.EnderTeleportEvent;
import user11681.anvilevents.event.entity.EntityDamageEvent;
import user11681.anvilevents.event.entity.EntityTickEvent;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityDuck {
    @Shadow
    public World world;

    @Shadow
    public abstract Box getBoundingBox();

    @Shadow
    public boolean damage(final DamageSource source, final float amount) {
        return false;
    }

    protected final Entity thiz = (Entity) (Object) this;

    protected boolean damage = true;
    protected boolean teleport = true;

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    protected void preDamage(final DamageSource source, final float damage, final CallbackInfoReturnable<Boolean> info) {
        if (this.damage) {
            final Entity thiz = this.thiz;
            final EntityDamageEvent event = new EntityDamageEvent.Pre(thiz, source, damage).fire();

            if (event.isFail()) {
                info.setReturnValue(false);
            } else {
                final Entity entity = event.getEntity();
                this.damage = false;

                if (entity instanceof LivingEntity) {
                    info.setReturnValue(((LivingEntityDuck) entity).superDamage(event.getSource(), event.getDamage()) && thiz == entity || event.isAccepted());
                } else {
                    info.setReturnValue(entity.damage(event.getSource(), event.getDamage()) && thiz == entity || event.isAccepted());
                }

                this.damage = true;
            }
        }
    }

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("RETURN"))
    protected void postDamage(final DamageSource source, final float damage, final CallbackInfoReturnable<Boolean> info) {
        if (info.getReturnValue()) {
            new EntityDamageEvent.Post(thiz, source, damage).fire();
        }
    }

    @Inject(method = "requestTeleport(DDD)V", at = @At(value = "JUMP", opcode = Opcodes.INSTANCEOF, ordinal = 0), cancellable = true)
    protected void onRequestTeleport(final double x, final double y, final double z, final CallbackInfo info) {
        if (this.teleport) {
            final EnderTeleportEvent event = new EnderTeleportEvent(thiz, x, y, z).fire();

            if (!event.isFail()) {
                this.teleport = false;
                event.getEntity().requestTeleport(event.getX(), event.getY(), event.getZ());
                this.teleport = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    protected void preTick(final CallbackInfo info) {
        if (new EntityTickEvent.Pre(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "tick()V", at = @At("RETURN"))
    protected void postTick(final CallbackInfo info) {
        new EntityTickEvent.Post(thiz).fire();
    }
}
