package ro.microservices.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservices.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
