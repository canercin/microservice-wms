package dev.canercin.product.service;

import dev.canercin.product.service.dto.StockData;
import java.util.List;
import java.util.UUID;

public interface StockService {
    StockData createStock(StockData stockDto);
    List<StockData> getAllStocks();
    StockData getStockById(UUID id);
    StockData updateStock(UUID id, StockData stockDto);
    void deleteStock(UUID id);
}
