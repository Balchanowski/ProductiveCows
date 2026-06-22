package com.stanislaw.productivecows.event;

import com.stanislaw.productivecows.ProductiveCows;
import com.stanislaw.productivecows.entity.ModEntities;
import com.stanislaw.productivecows.entity.custom.ProductiveCowEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ProductiveCows.MODID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PRODUCTIVE_COW.get(), ProductiveCowEntity.createAttributes().build());
    }
}
