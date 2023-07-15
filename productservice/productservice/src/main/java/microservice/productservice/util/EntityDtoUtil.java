package microservice.productservice.util;

import microservice.productservice.dto.ProductDto;
import microservice.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
