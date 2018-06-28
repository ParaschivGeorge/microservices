package ro.microservice.accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Collection<ProductModel> products;
}
