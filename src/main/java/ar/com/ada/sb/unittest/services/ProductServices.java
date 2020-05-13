package ar.com.ada.sb.unittest.services;

import ar.com.ada.sb.unittest.model.dto.ProductDto;
import ar.com.ada.sb.unittest.model.entity.Product;
import ar.com.ada.sb.unittest.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.sb.unittest.model.mapper.ProductMapper;
import ar.com.ada.sb.unittest.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServices")
public class ProductServices implements Services<ProductDto> {
    //Repository
    @Autowired @Qualifier("productRepository")
    private ProductRepository productRepository;

    //Mappers
    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ProductMapper productMapper = ProductMapper.MAPPER;

    @Override
    public List<ProductDto> findAll() {
        List<Product> all = productRepository.findAll();
        List<ProductDto> productDtoList = productMapper.toDto(all, context);

        return productDtoList;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        Product product = productMapper.toEntity(dto,context);

        //aqui se aplica la entrega fake en base al mock definido
        Product productToSave = productRepository.save(product);

        ProductDto dtoSaved = productMapper.toDto(productToSave, context);

        return dtoSaved;
    }

    @Override
    public void delete(Long id) {

    }
}
