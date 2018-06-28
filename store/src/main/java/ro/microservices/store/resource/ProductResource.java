package ro.microservices.store.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservices.store.entities.Product;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.services.ProductService;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = Objects.requireNonNull(productService, "productService should not be null");
    }

    @GetMapping("/list/{category}")
    public Collection<ProductModel> getCategoryProducts(@PathVariable("category") final Long categId) {
        return productService.getByCategory(categId);
    }

    @GetMapping("/{code}")
    public Collection<ProductModel> getByCode(@PathVariable("code") final String code) {
        return productService.getByCode(code);
    }
}
