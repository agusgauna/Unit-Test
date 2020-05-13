package ar.com.ada.sb.unittest.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private BigInteger price;

    private Date createAt;

    private Date updateAt;

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDto setPrice(BigInteger price) {
        this.price = price;
        return this;
    }
}
