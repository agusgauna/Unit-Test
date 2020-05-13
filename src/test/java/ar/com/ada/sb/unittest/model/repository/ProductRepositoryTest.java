package ar.com.ada.sb.unittest.model.repository;

import ar.com.ada.sb.unittest.model.entity.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

    @Autowired @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Test @Order(0)
    public void whenSaveThenReturnAProductWhitId(){
        //GIVEN
        Product product = new Product()
                .setName("P1")
                .setDescription("DESC 1")
                .setPrice(new BigInteger("10"));

        //WHEN
        Product saved = productRepository.save(product);

        //THEN
        assertNotNull(saved.getId());
        assertNotNull(saved.getCreateAt());
        assertNotNull(saved.getUpdateAt());
    }

    @Test @Order(1)
    public void whenFindByNameThenReturnProduct() {
        //GIVEN
        String productName = "P1";

        //WHEN
        Optional<Product> byName = productRepository.findByName(productName);
        Product product = byName.get();

        //THEN
        assertEquals(true, byName.isPresent());
        assertEquals(productName, product.getName());

    }

    @Test @Order(2)
    public void whenFindAllThenReturnProductList() {
        //GIVEN

        //WHEN
        List<Product> productList = productRepository.findAll();

        //THEN
        assertThat(productList).hasSize(1);
    }
}