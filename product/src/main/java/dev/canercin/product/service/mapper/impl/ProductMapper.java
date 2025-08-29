package dev.canercin.product.service.mapper.impl;

import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.service.dto.ProductData;
import dev.canercin.product.service.mapper.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapper implements GenericMapper<ProductEntity, ProductData> {

    public ProductEntity toEntity(ProductData productData){
        ProductEntity entity = new ProductEntity();

        if (Objects.nonNull(productData)){
            if (productData.getId() != null) {
                entity.setId(productData.getId());
            }
            if (productData.getCode() != null) {
                entity.setCode(productData.getCode());
            }
            if (productData.getName() != null) {
                entity.setName(productData.getName());
            }
            if (productData.getDescription() != null) {
                entity.setDescription(productData.getDescription());
            }
        }

        return entity;
    }

    public ProductData toDto(ProductEntity productEntity){
        ProductData data = new ProductData();

        if (Objects.nonNull(productEntity)) {
            if (productEntity.getId() != null) {
                data.setId(productEntity.getId());
            }
            if (productEntity.getCode() != null) {
                data.setCode(productEntity.getCode());
            }
            if (productEntity.getName() != null) {
                data.setName(productEntity.getName());
            }
            if (productEntity.getDescription() != null) {
                data.setDescription(productEntity.getDescription());
            }
        }

        return data;
    }
}
