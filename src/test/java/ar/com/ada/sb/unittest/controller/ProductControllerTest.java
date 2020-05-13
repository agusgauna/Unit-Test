package ar.com.ada.sb.unittest.controller;

import ar.com.ada.sb.unittest.model.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldBeCreatedANewProduct() {
        //GIVEN
        String url = "http://localhost:" + port + "/products";

        //requestBody
        ProductDto newProduct = new ProductDto()
                .setId(1L)
                .setName("P1")
                .setDescription("Description 1")
                .setPrice(new BigInteger("10"));

        //HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDto> request = new HttpEntity<>(newProduct);

        //WHEN
        ResponseEntity<ProductDto> response = testRestTemplate.exchange(
                url, HttpMethod.POST,
                request, new ParameterizedTypeReference<ProductDto>() {
                }
        );

        //THEN

        //FORMA A: expected vs actual
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getId());

        //FORMA B: by method isEqualTo
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(1);

    }
}