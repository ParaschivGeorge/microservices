package ro.microservices.inventory;

import org.apache.kafka.common.network.KafkaChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;
import ro.microservices.inventory.config.KafkaChannels;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.repositories.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(KafkaChannels.class)
@IntegrationComponentScan(basePackages = "ro.microservices.inventory")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Value("${price.value}")
    private Integer value;

    @Override
    public void run(String... strings) throws Exception {
        productRepository.save(Product.builder()
                .code("prod1")
                .price(new BigDecimal(value))
                .build());
    }
}
