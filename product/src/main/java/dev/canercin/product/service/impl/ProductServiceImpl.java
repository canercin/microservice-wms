package dev.canercin.product.service.impl;

import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.repository.ProductRepository;
import dev.canercin.product.service.ProductService;
import dev.canercin.product.service.dto.ProductData;
import dev.canercin.product.service.mapper.impl.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MessageSource messageSource;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.messageSource = messageSource;
    }

    @Override
    public ProductData createProduct(ProductData productData) {
        logger.debug("Creating product with id {}", productData.getId());
        ProductEntity entity = productMapper.toEntity(productData);
        ProductEntity saved = productRepository.save(entity);
        return productMapper.toDto(saved);
    }

    @Override
    public ProductData getProductById(UUID id) {
        logger.debug("Retrieving product with id {}", id);
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ProductData> getAllProducts() {
        logger.debug("Retrieving all products");
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductData updateProduct(UUID id, ProductData productData) {
        logger.debug("Updating product with id {}", id);
        Optional<ProductEntity> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            ProductEntity entity = existing.get();
            entity.setCode(productData.getCode());
            entity.setName(productData.getName());
            entity.setDescription(productData.getDescription());
            ProductEntity updated = productRepository.save(entity);
            return productMapper.toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        logger.debug("Deleting product with id {}", id);
        checkProductExists(id);
        productRepository.deleteById(id);
    }

    private void checkProductExists(UUID id) {
        if (!productRepository.existsById(id)) {
            logger.error("Product with id {} does not exist", id);
            throw new RuntimeException(messageSource.getMessage("product.not-found.id", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
    }
}
