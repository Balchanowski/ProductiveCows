package com.stanislaw.productivecows.entity.custom;

import com.stanislaw.productivecows.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ProductiveCow extends Cow {
    ProductiveCow(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(itemStack.is(Items.BUCKET) && !this.isBaby()) {            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            if (!this.level().isClientSide()) {
                ItemStack itemStack1 = ItemUtils.createFilledResult(itemStack, player, ModItems.EMERALD_MILK_BUCKET.get().getDefaultInstance());
                player.setItemInHand(hand, itemStack1);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

}
