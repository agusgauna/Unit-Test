package ar.com.ada.sb.unittest.model.repository;

import ar.com.ada.sb.unittest.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository <Product, Long>{
    Optional<Product> findByName(String name);
}
