package ro.microservices.store.services;

import org.springframework.stereotype.Service;
import ro.microservices.store.clients.InventoryClient;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.InventoryModel;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductService(ProductRepository productRepository, InventoryClient inventoryClient) {
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;
    }

    public Collection<ProductModel> getByCategory(final long categId) {
        return productRepository.findByCategoryId(categId).stream()
                .map(p -> {
                    final InventoryModel inventory = inventoryClient.getProductInventory(p.getCode()).getBody();
                    return ProductMapper.toModel(p, inventory);
                })
                .collect(Collectors.toList());
    }

    public Collection<ProductModel> getByCode(String code) {
        return productRepository.findByCode(code).stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }
}
