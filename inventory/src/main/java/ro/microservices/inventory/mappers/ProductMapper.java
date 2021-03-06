package ro.microservices.inventory.mappers;

import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.models.ProductModel;

public class ProductMapper {

    public static ProductModel toModel(final Product product) {
        return ProductModel.builder()
                .code(product.getCode())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public static Product toEntity(ProductModel product) {
        return Product.builder()
                .code(product.getCode())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
