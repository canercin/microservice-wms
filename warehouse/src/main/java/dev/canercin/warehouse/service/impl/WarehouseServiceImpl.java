package dev.canercin.warehouse.service.impl;

import dev.canercin.warehouse.entity.WarehouseEntity;
import dev.canercin.warehouse.repository.WarehouseRepository;
import dev.canercin.warehouse.service.WarehouseService;
import dev.canercin.warehouse.service.dto.WarehouseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseData createWarehouse(WarehouseData warehouseData) {
        WarehouseEntity entity = toEntity(warehouseData);
        WarehouseEntity saved = warehouseRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public WarehouseData getWarehouseById(UUID id) {
        return warehouseRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<WarehouseData> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseData updateWarehouse(UUID id, WarehouseData warehouseData) {
        Optional<WarehouseEntity> existing = warehouseRepository.findById(id);
        if (existing.isPresent()) {
            WarehouseEntity entity = existing.get();
            entity.setCode(warehouseData.getCode());
            entity.setName(warehouseData.getName());
            entity.setDescription(warehouseData.getDescription());
            WarehouseEntity updated = warehouseRepository.save(entity);
            return toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteWarehouse(UUID id) {
        warehouseRepository.deleteById(id);
    }

    private WarehouseData toDto(WarehouseEntity entity) {
        if (entity == null) return null;
        WarehouseData dto = new WarehouseData();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private WarehouseEntity toEntity(WarehouseData dto) {
        if (dto == null) return null;
        WarehouseEntity entity = new WarehouseEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}

