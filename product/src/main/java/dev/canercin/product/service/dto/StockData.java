package dev.canercin.product.service.dto;

import java.math.BigInteger;
import java.util.UUID;

public class StockData {
    private UUID stockId;
    private UUID productId;
    private String productName;
    private UUID warehouseId;
    private BigInteger quantity;

    public StockData() {}

    public StockData(UUID stockId, UUID productId, String productName, UUID warehouseId, BigInteger quantity) {
        this.stockId = stockId;
        this.productId = productId;
        this.productName = productName;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

