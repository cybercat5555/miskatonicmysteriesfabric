package com.miskatonicmysteries.common.feature.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;

public class ExoticCravingsStatusEffect extends StatusEffect {
    public ExoticCravingsStatusEffect() {
        super(StatusEffectType.HARMFUL, 0xAA0000);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.age % 20 == 0) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 2, true, false, false));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
