package ro.microservices.inventory.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.microservices.inventory.config.KafkaGateway;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.mappers.ProductMapper;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final KafkaGateway kafkaGateway;

    public ProductService(ProductRepository productRepository, KafkaGateway kafkaGateway) {
        this.productRepository = productRepository;
        this.kafkaGateway = kafkaGateway;
    }

    public Optional<ProductModel> getByCode(String prodCode) {
        return productRepository.findByCode(prodCode).stream()
                .findFirst()
                .map(p -> ProductMapper.toModel(p));
    }

    public ProductModel save(final ProductModel product) {
        final Product prod = productRepository.findByCode(product.getCode()).stream()
                .findFirst()
                .map(p -> {
                    Integer initStock = p.getStock();
                    p.setStock(product.getStock());
                    p.setPrice(product.getPrice());
                    if (initStock != product.getStock()) {
                        kafkaGateway.write(product);
                    }

                    return p;
                })
                .orElseGet(() -> ProductMapper.toEntity(product));
        return ProductMapper.toModel(productRepository.save(prod));
    }
}
