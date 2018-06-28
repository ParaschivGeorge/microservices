package ro.microservice.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.stereotype.Component;
import ro.microservice.accounting.config.KafkaChannels;
import ro.microservice.accounting.entities.Account;
import ro.microservice.accounting.repositories.AccountRepository;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(KafkaChannels.class)
@IntegrationComponentScan(basePackages = "ro.microservice.accounting")
public class AccountingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingApplication.class, args);
	}
}

@Component
class DummyData implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws Exception {
        accountRepository.save(
                Account.builder()
                        .name("Vasile Emil")
                        .cardId(1L)
                        .credit(new BigDecimal(1000))
                        .build()
        );
    }
}
