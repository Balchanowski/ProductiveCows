package com.stanislaw.productivecows.entity.custom;

import com.stanislaw.productivecows.entity.ModEntities;
import com.stanislaw.productivecows.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ProductiveCowEntity extends Cow {
    public ProductiveCowEntity(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(Items.IRON_INGOT), false));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BUCKET) && !this.isBaby()){
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemStack1 = ItemUtils.createFilledResult(itemStack, player, ModItems.IRON_MILK_BUCKET.get().getDefaultInstance());
            player.setItemInHand(hand, itemStack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        else {
            return super.mobInteract(player, hand);
        }
    }


    @Override
    public boolean isFood(ItemStack stack) {return stack.is(Items.IRON_INGOT);}

    @Nullable
    @Override
    public ProductiveCowEntity getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.PRODUCTIVE_COW.get().create(level);
    }
}
