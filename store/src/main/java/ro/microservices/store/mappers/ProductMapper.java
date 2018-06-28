package ro.microservices.store.mappers;

import ro.microservices.store.entities.Product;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.models.ProductModel;

import java.math.BigDecimal;

public class ProductMapper {

    public static ProductModel toModel(final Product product) {
        return ProductMapper.toModel(product, InventoryModel.builder().price(new BigDecimal(0)).build());
    }

    public static ProductModel toModel(final Product product, final InventoryModel inventoryModel) {
        return ProductModel.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .price(inventoryModel.getPrice())
                .isPublished(product.getIsPublished())
                .build();
    }

}
