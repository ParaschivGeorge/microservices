package ro.microservices.inventory.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.models.ProductModel;
import ro.microservices.inventory.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductModel> getProductByCode(@PathVariable("code") final String prodCode) {
        return productService.getByCode(prodCode)
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody final ProductModel product) {
        return ResponseEntity.ok(productService.save(product));
    }
}
