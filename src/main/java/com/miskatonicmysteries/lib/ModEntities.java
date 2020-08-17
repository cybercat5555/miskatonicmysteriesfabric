package com.miskatonicmysteries.lib;

import com.miskatonicmysteries.common.entity.EntityHasturCultist;
import com.miskatonicmysteries.common.entity.EntityProtagonist;
import com.miskatonicmysteries.lib.util.Constants;
import com.miskatonicmysteries.lib.util.MiscUtil;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;

public class ModEntities {
    public static final EntityType<EntityProtagonist> PROTAGONIST = FabricEntityTypeBuilder.create(SpawnGroup.MISC, EntityProtagonist::new).spawnableFarFromPlayer().dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackable(48, 6).build();
    public static final EntityType<EntityHasturCultist> HASTUR_CULTIST = FabricEntityTypeBuilder.create(SpawnGroup.MISC, EntityHasturCultist::new).spawnableFarFromPlayer().dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackable(16, 10).build();

    public static final VillagerProfession PSYCHONAUT = VillagerProfessionBuilder.create().id(new Identifier(Constants.MOD_ID, "psychonaut")).workstation(ModWorld.PSYCHONAUT_POI).workSound(SoundEvents.BLOCK_BREWING_STAND_BREW).build();

    public static void init() {
        MiscUtil.register(Registry.ENTITY_TYPE, "protagonist", PROTAGONIST);
        FabricDefaultAttributeRegistry.register(PROTAGONIST, MobEntityWithAi.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 25).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.5F).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40));

        MiscUtil.register(Registry.ENTITY_TYPE, "hastur_cultist", HASTUR_CULTIST);
        FabricDefaultAttributeRegistry.register(HASTUR_CULTIST, MobEntityWithAi.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5F).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10).add(EntityAttributes.GENERIC_ARMOR, 7));

        MiscUtil.register(Registry.VILLAGER_PROFESSION, "psychonaut", PSYCHONAUT);
    }
}