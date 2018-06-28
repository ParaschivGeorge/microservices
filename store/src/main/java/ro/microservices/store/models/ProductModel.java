package ro.microservices.store.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.microservices.store.entities.Category;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductModel {
    private String code;
    private String name;
    private Category category;
    private BigDecimal price;
    private Boolean isPublished;
}
