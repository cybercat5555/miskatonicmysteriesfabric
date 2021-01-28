package com.miskatonicmysteries.common.item.consumable;

import com.miskatonicmysteries.common.feature.interfaces.Sanity;
import com.miskatonicmysteries.common.feature.interfaces.SpellCaster;
import com.miskatonicmysteries.common.feature.spell.SpellMedium;
import com.miskatonicmysteries.common.lib.Constants;
import com.miskatonicmysteries.common.lib.MMMiscRegistries;
import com.miskatonicmysteries.common.lib.util.CapabilityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class TheOrbItem extends Item {
    public static final FoodComponent ORB_FOOD = new FoodComponent.Builder().hunger(-20).saturationModifier(0)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(MMMiscRegistries.StatusEffects.MANIA, 1200, 1), 0.9F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1200, 1), 0.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2400, 1), 0.4F)
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 600, 0), 0.25F)
            .meat() //because the Orb was not cursed enough yet
            .build();

    public TheOrbItem() {
        super(new Item.Settings().group(Constants.MM_GROUP).food(ORB_FOOD).maxCount(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Sanity.of(user).ifPresent(sanity -> {
            sanity.addSanityCapExpansion("orb", -50);
            sanity.setSanity(sanity.getSanity() - 25, true);
        });
        SpellCaster.of(user).ifPresent(caster -> {
            caster.learnMedium(SpellMedium.PROJECTILE);
            CapabilityUtil.guaranteePower(2, caster);
        });
        user.eatFood(world, stack);
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 60;
    }
}