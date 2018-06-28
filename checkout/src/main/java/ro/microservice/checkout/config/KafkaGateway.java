package ro.microservice.checkout.config;

import org.springframework.integration.annotation.Gateway;
import ro.microservice.checkout.models.OrderModel;

public interface KafkaGateway {
    @Gateway(requestChannel = "orderChannel")
    void write(final OrderModel accepted);
}
