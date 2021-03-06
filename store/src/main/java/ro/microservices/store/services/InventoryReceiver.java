package ro.microservices.store.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ro.microservices.store.entities.Product;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class InventoryReceiver {

    private final ProductRepository productRepository;

    public InventoryReceiver(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @StreamListener("stockChannel")
    public void onReceiver(final Message<InventoryModel> message) {
        InventoryModel inventory = message.getPayload();

        Collection<Product> products = productRepository.findByCode(inventory.getCode()).stream()
        .map(p -> {
            p.setIsPublished(inventory.getStock() > 0);
            return p;
        }).collect(Collectors.toList());

        productRepository.save(products);
    }

}
