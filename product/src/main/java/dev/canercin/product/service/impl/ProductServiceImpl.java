package dev.canercin.product.service.impl;

import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.repository.ProductRepository;
import dev.canercin.product.service.ProductService;
import dev.canercin.product.service.dto.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductData createProduct(ProductData productData) {
        ProductEntity entity = toEntity(productData);
        ProductEntity saved = productRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public ProductData getProductById(UUID id) {
        return productRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<ProductData> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductData updateProduct(UUID id, ProductData productData) {
        Optional<ProductEntity> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            ProductEntity entity = existing.get();
            entity.setCode(productData.getCode());
            entity.setName(productData.getName());
            entity.setDescription(productData.getDescription());
            ProductEntity updated = productRepository.save(entity);
            return toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    private ProductData toDto(ProductEntity entity) {
        if (entity == null) return null;
        ProductData dto = new ProductData();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private ProductEntity toEntity(ProductData dto) {
        if (dto == null) return null;
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
