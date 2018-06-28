package ro.microservices.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservices.inventory.entities.Product;
import ro.microservices.inventory.models.ProductModel;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCode(final String prodCode);
}
