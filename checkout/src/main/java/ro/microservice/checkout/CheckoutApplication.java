package ro.microservice.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ro.microservice.checkout.config.KafkaChannels;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(KafkaChannels.class)
@IntegrationComponentScan(basePackages = "ro.microservice.checkout")
public class CheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutApplication.class, args);
	}
}
