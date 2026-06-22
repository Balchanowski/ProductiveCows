package com.stanislaw.productivecows.entity.client;

import com.stanislaw.productivecows.ProductiveCows;
import com.stanislaw.productivecows.entity.custom.ProductiveCowEntity;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ProductiveCowRenderer extends MobRenderer<ProductiveCowEntity, CowModel<ProductiveCowEntity>> {

    public ProductiveCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }
    @Override
    public ResourceLocation getTextureLocation(ProductiveCowEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(ProductiveCows.MODID, "textures/entity/productive_cow/iron_cow.png");
    }

}
