package com.miskatonicmysteries.common.entity.util;

import com.miskatonicmysteries.common.feature.spell.Spell;

public interface CastingMob {
    void setCastTime(int castTime);

    int getCastTime();

    boolean isCasting();

    void setCurrentSpell(Spell spell);

    Spell getCurrentSpell();

    Spell selectSpell();
}
