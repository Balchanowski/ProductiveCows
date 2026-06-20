package com.stanislaw.productivecows;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import static com.stanislaw.productivecows.ModItems.*;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ProductiveCows.MODID)
public class ProductiveCows {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "productivecows";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "productivecows" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "productivecows" namespace

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "productivecows:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.productivecows")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EMERALD_MILK_BUCKET.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(IRON_MILK_BUCKET.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(BLAZEROD_MILK_BUCKET.get());
                output.accept(COPPER_MILK_BUCKET.get());
                output.accept(DIAMOND_MILK_BUCKET.get());
                output.accept(EMERALD_MILK_BUCKET.get());
                output.accept(GOLD_MILK_BUCKET.get());
                output.accept(NETHERITESCRAP_MILK_BUCKET.get());
                output.accept(NETHERSTAR_MILK_BUCKET.get());
                output.accept(NETHERWART_MILK_BUCKET.get());
                output.accept(OBSIDIAN_MILK_BUCKET.get());
                output.accept(QUARTZ_MILK_BUCKET.get());
                output.accept(REDSTONE_MILK_BUCKET.get());
                output.accept(SLIME_MILK_BUCKET.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ProductiveCows(IEventBus modEventBus, ModContainer modContainer) {
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ProductiveCows) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
