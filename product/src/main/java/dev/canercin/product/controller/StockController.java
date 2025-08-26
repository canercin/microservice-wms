package dev.canercin.product.controller;

import dev.canercin.product.service.StockService;
import dev.canercin.product.service.dto.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StockData> createStock(@RequestBody StockData stockDto) {
        return ResponseEntity.ok(stockService.createStock(stockDto));
    }

    @GetMapping
    public ResponseEntity<List<StockData>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockData> getStockById(@PathVariable UUID id) {
        StockData stock = stockService.getStockById(id);
        if (stock == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockData> updateStock(@PathVariable UUID id, @RequestBody StockData stockDto) {
        StockData updated = stockService.updateStock(id, stockDto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
