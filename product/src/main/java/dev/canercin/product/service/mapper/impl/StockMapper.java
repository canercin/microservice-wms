package dev.canercin.product.service.mapper.impl;

import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.entity.StockEntity;
import dev.canercin.product.service.dto.ProductData;
import dev.canercin.product.service.dto.StockData;
import dev.canercin.product.service.mapper.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StockMapper implements GenericMapper<StockEntity, StockData> {
    @Override
    public StockEntity toEntity(StockData stockData) {
        StockEntity entity = new StockEntity();

        if (Objects.nonNull(stockData)) {
            if (Objects.nonNull(stockData.getId())) {
                entity.setId(stockData.getId());
            }

            if (Objects.nonNull(stockData.getProduct())) {
                entity.setProduct(this.toEntityProduct(stockData.getProduct()));
            }

            if (Objects.nonNull(stockData.getWarehouseId())) {
                entity.setWarehouseId(stockData.getWarehouseId());
            }

            if (Objects.nonNull(stockData.getQuantity())) {
                entity.setQuantity(stockData.getQuantity());
            }
        }

        return entity;
    }

    @Override
    public StockData toDto(StockEntity stockEntity) {
        StockData stockData = new StockData();

        if (Objects.nonNull(stockEntity)) {
            if (Objects.nonNull(stockEntity.getId())) {
                stockData.setId(stockEntity.getId());
            }

            if (Objects.nonNull(stockEntity.getProduct())) {
                stockData.setProduct(this.toDto(stockEntity.getProduct()));
            }

            if (Objects.nonNull(stockEntity.getWarehouseId())) {
                stockData.setWarehouseId(stockEntity.getWarehouseId());
            }

            if (Objects.nonNull(stockEntity.getQuantity())) {
                stockData.setQuantity(stockEntity.getQuantity());
            }
        }

        return stockData;
    }

    private ProductEntity toEntityProduct(ProductData productData) {
        ProductEntity productEntity = new ProductEntity();

        if (Objects.nonNull(productData)) {
            if (Objects.nonNull(productData.getId())) {
                productEntity.setId(productData.getId());
            }

            if (Objects.nonNull(productData.getCode())) {
                productEntity.setCode(productData.getCode());
            }

            if (Objects.nonNull(productData.getName())) {
                productEntity.setName(productData.getName());
            }

            if (Objects.nonNull(productData.getDescription())) {
                productEntity.setDescription(productData.getDescription());
            }
        }

        return productEntity;
    }

    private ProductData toDto(ProductEntity productEntity) {
        ProductData productData = new ProductData();

        if (Objects.nonNull(productEntity)) {
            if (Objects.nonNull(productEntity.getId())) {
                productData.setId(productEntity.getId());
            }

            if (Objects.nonNull(productEntity.getCode())) {
                productData.setCode(productEntity.getCode());
            }

            if (Objects.nonNull(productEntity.getName())) {
                productData.setName(productEntity.getName());
            }

            if (Objects.nonNull(productEntity.getDescription())) {
                productData.setDescription(productEntity.getDescription());
            }
        }

        return productData;
    }
}
