package com.stanislaw.productivecows.entity;

import com.stanislaw.productivecows.ProductiveCows;
import com.stanislaw.productivecows.entity.custom.ProductiveCowEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ProductiveCows.MODID);

   public static final Supplier<EntityType<ProductiveCowEntity>> PRODUCTIVE_COW = ENTITY_TYPES.register("productive_cow", () -> EntityType.Builder.of(ProductiveCowEntity::new, MobCategory.CREATURE).build("productive_cow"));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
