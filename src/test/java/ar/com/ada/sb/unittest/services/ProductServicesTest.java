package ar.com.ada.sb.unittest.services;

import ar.com.ada.sb.unittest.model.dto.ProductDto;
import ar.com.ada.sb.unittest.model.entity.Product;
import ar.com.ada.sb.unittest.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.sb.unittest.model.mapper.ProductMapper;
import ar.com.ada.sb.unittest.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServicesTest {

    private final ProductMapper productMapper = ProductMapper.MAPPER;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CycleAvoidingMappingContext context;

    @InjectMocks
    private ProductServices productServices;

    @Test
    public void whenFindThenReturnProductList() {
        //GIVEN
        Product dto = new Product()
                .setId(1L)
                .setName("P1")
                .setDescription("Description 1")
                .setPrice(new BigInteger("1000"));
        Product dto2 = new Product()
                .setId(2L)
                .setName("P2")
                .setDescription("Description 2")
                .setPrice(new BigInteger("2000"));

        List<Product> expectedProductList = Arrays.asList(dto, dto2);
        doReturn(expectedProductList).when(productRepository).findAll();
        //when(productRepository.findAll()).thenReturn(productListMock or expectedProductList)

        //WHEN
        List<ProductDto> productDtos = productServices.findAll();

        //THEN
        assertThat(productDtos.size()).isEqualTo(2);
        assertThat(productDtos.get(0).getName()).isEqualTo("P1");
        assertThat(productDtos.get(0).getDescription()).isEqualTo("Description 1");

    }

    @Test
    public void whenSaveReturnProductDtoSaved() {
            //GIVEN
            Product product = new Product()
                    .setId(1L)
                    .setName("P1")
                    .setDescription("Description 1")
                    .setPrice(new BigInteger("1000"));

            when(productRepository.save(any(Product.class))).thenReturn(product);
            ProductDto dto = new ProductDto();

            //WHEN
            ProductDto dtoSaved = productServices.save(dto);

            //THEN
            assertThat(dtoSaved.getId()).isEqualTo(1);

    }

    //whenSaveReturnProductDtoSaved, dto sin id y cuando me retorne que me retorne con id asignado
    //assertThat(dtoSaved.getId()).isEqualTo(); definir id en el mock
}