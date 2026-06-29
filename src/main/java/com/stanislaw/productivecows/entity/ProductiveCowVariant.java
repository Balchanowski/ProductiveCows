package com.stanislaw.productivecows.entity;

import com.stanislaw.productivecows.ProductiveCows;
import com.stanislaw.productivecows.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public enum ProductiveCowVariant {
    IRON("iron", ModItems.IRON_MILK_BUCKET, () -> Items.IRON_BLOCK, false, ModItems.IRON_COW_SPAWN_EGG),
    OBSIDIAN("obsidian", ModItems.OBSIDIAN_MILK_BUCKET, () -> Items.OBSIDIAN, false, ModItems.OBSIDIAN_COW_SPAWN_EGG),
    EMERALD("emerald", ModItems.EMERALD_MILK_BUCKET, () -> Items.EMERALD_BLOCK, false, ModItems.EMERALD_COW_SPAWN_EGG),
    GOLD("gold", ModItems.GOLD_MILK_BUCKET, () -> Items.GOLD_BLOCK, false, ModItems.GOLD_COW_SPAWN_EGG),
    DIAMOND("diamond", ModItems.DIAMOND_MILK_BUCKET, () -> Items.DIAMOND_BLOCK, false, ModItems.DIAMOND_COW_SPAWN_EGG);


    private final String id;
    private final Supplier<? extends Item> bucket_id;
    private final boolean isOnWorldSpawn;
    private final Supplier<? extends Item> cowFood;
    private final Supplier<? extends Item> cow_spawn_egg;


    ProductiveCowVariant(String id,Supplier<? extends Item> bucket_id, Supplier<? extends Item> cowFood, boolean isOnWorldSpawn, Supplier<? extends Item> cow_spawn_egg) {
        this.id = id;
        this.bucket_id = bucket_id;
        this.isOnWorldSpawn = isOnWorldSpawn;
        this.cowFood = cowFood;
        this.cow_spawn_egg = cow_spawn_egg;
    }

    public static ProductiveCowVariant byId(String id) {
        for (ProductiveCowVariant variant : values()) {
            if (variant.id.equals(id)) {
                return variant;
            }
        }
        return IRON;   // bezpieczny fallback — żadna krowa nie zostaje bez wariantu
    }

    public String getId() {
        return id;
    }
    public Item getBucketId() {
        return bucket_id.get();
    }
    public Item getCowFood() {
        return cowFood.get();
    }
    public boolean isOnWorldSpawn() {
        return isOnWorldSpawn;
    }
    public Item getCowSpawnEgg() {
        return cow_spawn_egg.get();
    }
    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(ProductiveCows.MODID, "textures/entity/productive_cow/" + id + "_productive_cow.png");
    }

}
