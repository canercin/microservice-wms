package dev.canercin.warehouse.service;

import dev.canercin.warehouse.service.dto.WarehouseData;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {
    WarehouseData createWarehouse(WarehouseData warehouse);
    WarehouseData getWarehouseById(UUID id);
    List<WarehouseData> getAllWarehouses();
    WarehouseData updateWarehouse(UUID id, WarehouseData warehouse);
    void deleteWarehouse(UUID id);
}

