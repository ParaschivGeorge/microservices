package ro.microservice.checkout.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.microservice.checkout.entities.Product;

import java.math.BigDecimal;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long cardId;
    private BigDecimal value;
    private Long orderNo;
    private Collection<Product> products;
}
