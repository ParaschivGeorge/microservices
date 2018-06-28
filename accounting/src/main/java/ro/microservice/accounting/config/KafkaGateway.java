package ro.microservice.accounting.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ro.microservice.accounting.models.OrderResponseModel;

@MessagingGateway
public interface KafkaGateway {
    @Gateway(requestChannel = "creditChannel")
    void write(final OrderResponseModel accepted);
}
