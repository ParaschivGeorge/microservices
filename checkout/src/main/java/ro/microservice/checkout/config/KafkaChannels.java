package ro.microservice.checkout.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {

    @Input
    MessageChannel stockChannel();

    @Input
    MessageChannel creditChannel();

    @Output
    MessageChannel orderChannel();

}
