package com.miskatonicmysteries.common.registry;

import com.miskatonicmysteries.api.registry.Affiliation;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.miskatonicmysteries.common.util.Constants.MOD_ID;

public class MMAffiliations {
    public static final Affiliation NONE = new Affiliation(new Identifier(MOD_ID, "none"), new float[]{1, 1, 1});
    public static final Affiliation HASTUR = new Affiliation(new Identifier(MOD_ID, "hastur"), new float[]{1, 1, 0}, MMBlessings.CHARMING_PERSONALITY, MMBlessings.MAGIC_BOOST, MMBlessings.ROYAL_ENTOURAGE);
    public static final Affiliation SHUB = new Affiliation(new Identifier(MOD_ID, "shub"), new float[]{0.5F, 0.456F, 0.357F});
    public static final Affiliation CTHULHU = new Affiliation(new Identifier(MOD_ID, "cthulhu"), new float[]{0.2F, 0.27F, 0.44F});

    public static void init() {
        register(NONE);
        register(HASTUR);
        register(SHUB);
        register(CTHULHU);
    }

    private static void register(Affiliation affiliation) {
        Registry.register(MMRegistries.AFFILIATIONS, affiliation.getId(), affiliation);
    }
}
