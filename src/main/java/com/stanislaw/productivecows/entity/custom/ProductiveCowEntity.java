package com.stanislaw.productivecows.entity.custom;

import com.stanislaw.productivecows.entity.ModEntities;
import com.stanislaw.productivecows.entity.ProductiveCowVariant;
import com.stanislaw.productivecows.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ProductiveCowEntity extends Cow {
    public ProductiveCowEntity(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(ProductiveCowEntity.class, EntityDataSerializers.STRING);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(this.getVariant().getCowFood()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BUCKET) && !this.isBaby()){
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack bucket_milk = this.getVariant().getBucketId().getDefaultInstance();
            ItemStack itemStack1 = ItemUtils.createFilledResult(itemStack, player, bucket_milk);
            player.setItemInHand(hand, itemStack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        else {
            return super.mobInteract(player, hand);
        }
    }


    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(this.getVariant().getCowFood());
    }

    @Nullable
    @Override
    public ProductiveCowEntity getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        ProductiveCowEntity baby = ModEntities.PRODUCTIVE_COW.get().create(level);

        if (baby != null && otherParent instanceof ProductiveCowEntity otherCow) {
            ProductiveCowVariant variant = this.random.nextBoolean() ? this.getVariant() : otherCow.getVariant();
            baby.setVariant(variant);
        }

        return baby;
    }

    /* VARIANT */
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, "");
    }

    private String getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public ProductiveCowVariant getVariant() {
        return ProductiveCowVariant.byId(this.getTypeVariant());
    }

    private void setVariant(ProductiveCowVariant variant) {
        this.entityData.set(VARIANT, variant.getId());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getString("Variant"));
    }

    @Override
    public @NotNull SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @org.jetbrains.annotations.Nullable SpawnGroupData spawnGroupData) {
        List<ProductiveCowVariant> spawnable = new ArrayList<>();

        //foreach
        for (ProductiveCowVariant variant : ProductiveCowVariant.values()) {
            if(variant.isOnWorldSpawn()) {
                spawnable.add(variant);
            }
        }

        if (!spawnable.isEmpty()) {
            this.setVariant(spawnable.get(this.random.nextInt(spawnable.size())));
        }

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
