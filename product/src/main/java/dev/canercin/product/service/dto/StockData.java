package dev.canercin.product.service.dto;

import java.math.BigInteger;
import java.util.UUID;

public class StockData {
    private UUID id;
    private ProductData product;
    private UUID warehouseId;
    private BigInteger quantity;

    public StockData() {}

    public StockData(UUID id, ProductData product, UUID warehouseId, BigInteger quantity) {
        this.id = id;
        this.product = product;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}

