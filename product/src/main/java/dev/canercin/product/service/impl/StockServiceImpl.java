package dev.canercin.product.service.impl;

import dev.canercin.product.client.WarehouseClient;
import dev.canercin.product.client.data.WarehouseData;
import dev.canercin.product.entity.StockEntity;
import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.repository.StockRepository;
import dev.canercin.product.service.StockService;
import dev.canercin.product.service.dto.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final WarehouseClient warehouseClient;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, WarehouseClient warehouseClient) {
        this.stockRepository = stockRepository;
        this.warehouseClient = warehouseClient;
    }

    private StockData toDto(StockEntity entity) {
        if (entity == null) return null;
        ProductEntity product = entity.getProduct();
        return new StockData(
            entity.getId(),
            product != null ? product.getId() : null,
            product != null ? product.getName() : null,
            entity.getWarehouseId(),
            entity.getQuantity()
        );
    }

    private StockEntity toEntity(StockData dto) {
        if (dto == null) return null;
        StockEntity entity = new StockEntity();
        entity.setId(dto.getStockId());
        ProductEntity product = new ProductEntity();
        product.setId(dto.getProductId());
        entity.setProduct(product);
        Optional<WarehouseData> warehouseDataOptional = warehouseClient.getWarehouse(dto.getWarehouseId());
        if (warehouseDataOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid warehouse ID: " + dto.getWarehouseId());
        }
        entity.setWarehouseId(dto.getWarehouseId());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    @Override
    public StockData createStock(StockData stockDto) {
        StockEntity entity = toEntity(stockDto);
        StockEntity saved = stockRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public List<StockData> getAllStocks() {
        return stockRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public StockData getStockById(UUID id) {
        return stockRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public StockData updateStock(UUID id, StockData stockDto) {
        Optional<StockEntity> existing = stockRepository.findById(id);
        if (existing.isPresent()) {
            StockEntity entity = existing.get();
            entity.setWarehouseId(stockDto.getWarehouseId());
            entity.setQuantity(stockDto.getQuantity());
            // Product güncellemesi
            ProductEntity product = entity.getProduct();
            if (product == null) product = new ProductEntity();
            product.setId(stockDto.getProductId());
            entity.setProduct(product);
            StockEntity updated = stockRepository.save(entity);
            return toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteStock(UUID id) {
        stockRepository.deleteById(id);
    }
}
