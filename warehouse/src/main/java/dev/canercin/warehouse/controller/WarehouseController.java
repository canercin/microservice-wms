package dev.canercin.warehouse.controller;

import dev.canercin.warehouse.service.WarehouseService;
import dev.canercin.warehouse.service.dto.WarehouseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<WarehouseData> createWarehouse(@RequestBody WarehouseData warehouse) {
        WarehouseData created = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseData> getWarehouseById(@PathVariable UUID id) {
        WarehouseData data = warehouseService.getWarehouseById(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseData>> getAllWarehouses() {
        List<WarehouseData> list = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseData> updateWarehouse(@PathVariable UUID id, @RequestBody WarehouseData warehouse) {
        WarehouseData updated = warehouseService.updateWarehouse(id, warehouse);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable UUID id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}

