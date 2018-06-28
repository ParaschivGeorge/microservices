package ro.microservices.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservices.store.entities.Product;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCategoryId(final Long categId);
    Collection<Product> findByCode(final String code);
}
