package ro.microservice.accounting.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ro.microservice.accounting.config.KafkaGateway;
import ro.microservice.accounting.entities.Account;
import ro.microservice.accounting.models.OrderModel;
import ro.microservice.accounting.models.OrderResponseModel;
import ro.microservice.accounting.repositories.AccountRepository;

import java.util.Optional;

@Service
public class PaymentReceiver {

    private final AccountRepository accountRepository;
    private final KafkaGateway kafkaGateway;

    public PaymentReceiver(AccountRepository accountRepository, KafkaGateway kafkaGateway) {
        this.accountRepository = accountRepository;
        this.kafkaGateway = kafkaGateway;
    }

    @StreamListener("orderChannel")
    public void onReceiver(final Message<OrderModel> message) {

        OrderModel payment = message.getPayload();

        Optional<Account> account = accountRepository.findByCardId(payment.getCardId()).stream().findFirst();
        if (account.isPresent()) {
            if (account.get().getCredit().compareTo(payment.getValue()) > 0) {
                account.get().setCredit(account.get().getCredit().subtract(payment.getValue()));
                accountRepository.save(account.get());
                kafkaGateway.write(OrderResponseModel.builder()
                        .orderNo(payment.getOrderNo())
                        .accepted(true)
                        .build());
            }
            else
                kafkaGateway.write(OrderResponseModel.builder()
                        .orderNo(payment.getOrderNo())
                        .accepted(false)
                        .build());
        } else
            kafkaGateway.write(OrderResponseModel.builder()
                    .orderNo(payment.getOrderNo())
                    .accepted(false)
                    .build());
    }
}
