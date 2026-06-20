package com.stanislaw.productivecows.item;

import com.stanislaw.productivecows.ProductiveCows;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "productivecows" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ProductiveCows.MODID);

    public static final DeferredItem<Item> IRON_MILK_BUCKET = ITEMS.registerSimpleItem("iron_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> BLAZEROD_MILK_BUCKET = ITEMS.registerSimpleItem("blazerod_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> COPPER_MILK_BUCKET = ITEMS.registerSimpleItem("copper_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> DIAMOND_MILK_BUCKET = ITEMS.registerSimpleItem("diamond_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> EMERALD_MILK_BUCKET = ITEMS.registerSimpleItem("emerald_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> GOLD_MILK_BUCKET = ITEMS.registerSimpleItem("gold_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> NETHERITESCRAP_MILK_BUCKET = ITEMS.registerSimpleItem("netheritescrap_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> NETHERSTAR_MILK_BUCKET = ITEMS.registerSimpleItem("netherstar_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> NETHERWART_MILK_BUCKET = ITEMS.registerSimpleItem("netherwart_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> OBSIDIAN_MILK_BUCKET = ITEMS.registerSimpleItem("obsidian_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> QUARTZ_MILK_BUCKET = ITEMS.registerSimpleItem("quartz_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> REDSTONE_MILK_BUCKET = ITEMS.registerSimpleItem("redstone_milk_bucket", new Item.Properties());
    public static final DeferredItem<Item> SLIME_MILK_BUCKET = ITEMS.registerSimpleItem("slime_milk_bucket", new Item.Properties());


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
