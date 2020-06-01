package user11681.anvilevents.mixin.duck.entity;

import net.minecraft.entity.damage.DamageSource;

public interface LivingEntityDuck {
    boolean superDamage(DamageSource source, float damage);

    boolean superFall(float distance, float damageMultiplier);
}
