package dev.canercin.product.service.impl;

import dev.canercin.product.client.WarehouseClient;
import dev.canercin.product.client.data.WarehouseData;
import dev.canercin.product.entity.ProductEntity;
import dev.canercin.product.entity.StockEntity;
import dev.canercin.product.repository.StockRepository;
import dev.canercin.product.service.StockService;
import dev.canercin.product.service.dto.StockData;
import dev.canercin.product.service.mapper.impl.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final WarehouseClient warehouseClient;
    private final StockMapper stockMapper;
    private final MessageSource messageSource;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, WarehouseClient warehouseClient, StockMapper stockMapper, MessageSource messageSource) {
        this.stockRepository = stockRepository;
        this.warehouseClient = warehouseClient;
        this.stockMapper = stockMapper;
        this.messageSource = messageSource;
    }

    @Override
    public StockData createStock(StockData stockDto) {
        StockEntity entity = stockMapper.toEntity(stockDto);
        StockEntity saved = stockRepository.save(entity);
        return stockMapper.toDto(saved);
    }

    @Override
    public List<StockData> getAllStocks() {
        return stockRepository.findAll().stream().map(stockMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StockData getStockById(UUID id) {
        return stockRepository.findById(id).map(stockMapper::toDto).orElse(null);
    }

    @Override
    public StockData updateStock(UUID id, StockData stockDto) {
        Optional<StockEntity> existing = stockRepository.findById(id);
        if (existing.isPresent()) {
            StockEntity entity = existing.get();
            entity.setWarehouseId(this.getWarehouse(stockDto.getWarehouseId()).getId());
            entity.setQuantity(stockDto.getQuantity());
            // Product güncellemesi
            ProductEntity product = entity.getProduct();
            if (product == null) product = new ProductEntity();
            product.setId(stockDto.getProduct().getId());
            entity.setProduct(product);
            StockEntity updated = stockRepository.save(entity);
            return stockMapper.toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteStock(UUID id) {
        checkStockExists(id);
        stockRepository.deleteById(id);
    }

    private WarehouseData getWarehouse(UUID warehouseId) {
        Optional<WarehouseData> warehouseDataOptional = warehouseClient.getWarehouse(warehouseId);
        if (warehouseDataOptional.isPresent()) {
            return warehouseDataOptional.get();
        }

        throw new RuntimeException(messageSource.getMessage("warehouse.not-found.id", new Object[]{warehouseId}, LocaleContextHolder.getLocale()));
    }

    private void checkStockExists(UUID stockId) {
        if (!stockRepository.existsById(stockId)) {
            throw new IllegalArgumentException(messageSource.getMessage("stock.not-found.id", new Object[]{stockId}, LocaleContextHolder.getLocale()));
        }
    }
}
