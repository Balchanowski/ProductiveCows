package com.stanislaw.productivecows.item;

import com.stanislaw.productivecows.entity.ProductiveCowVariant;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.function.Supplier;

public class ProductiveCowSpawnEgg extends DeferredSpawnEggItem {
    public ProductiveCowSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, ProductiveCowVariant variant, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props.component(DataComponents.ENTITY_DATA, variantData(variant)));
        this.variant = variant;
    }

    private static CustomData variantData(ProductiveCowVariant variant) {
        CompoundTag tag = new CompoundTag();
        tag.putString("Variant", variant.getId());
        return CustomData.of(tag);
    }
    private final ProductiveCowVariant variant;
    public ProductiveCowVariant getVariant() {
        return this.variant;
    }
}
