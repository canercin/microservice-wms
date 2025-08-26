package dev.canercin.product.service;

import dev.canercin.product.service.dto.ProductData;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductData createProduct(ProductData product);
    ProductData getProductById(UUID id);
    List<ProductData> getAllProducts();
    ProductData updateProduct(UUID id, ProductData product);
    void deleteProduct(UUID id);
}
