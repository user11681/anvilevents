package user11681.anvilevents.duck.entity;

import net.minecraft.entity.damage.DamageSource;

public interface LivingEntityDuck {
    boolean superDamage(DamageSource source, float damage);

    void dropXp();
}
