package ro.microservices.store;

import lombok.AllArgsConstructor;
import org.apache.kafka.common.network.KafkaChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import ro.microservices.store.config.KafkaChannels;
import ro.microservices.store.entities.Product;
import ro.microservices.store.repositories.CategoryRepository;
import ro.microservices.store.repositories.ProductRepository;
import ro.microservices.store.entities.Category;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableBinding(KafkaChannels.class)
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... strings) throws Exception {
		final Category category = categoryRepository.save(
				Category.builder()
						.name("Test Category")
						.build()
		);

		productRepository.save(
				Product.builder()
						.code("prod1")
						.category(category)
						.name("test prod")
						.build()
		);
	}
}
