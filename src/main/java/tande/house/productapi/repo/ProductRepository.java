package tande.house.productapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tande.house.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
