package ar.com.ada.sb.unittest.model.mapper;

import ar.com.ada.sb.unittest.model.dto.ProductDto;
import ar.com.ada.sb.unittest.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CycleDataMapper<ProductDto, Product> {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
}
