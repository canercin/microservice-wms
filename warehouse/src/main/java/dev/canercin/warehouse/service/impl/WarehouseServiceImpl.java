package dev.canercin.warehouse.service.impl;

import dev.canercin.warehouse.entity.WarehouseEntity;
import dev.canercin.warehouse.repository.WarehouseRepository;
import dev.canercin.warehouse.service.WarehouseService;
import dev.canercin.warehouse.service.dto.WarehouseData;
import dev.canercin.warehouse.service.mapper.impl.WarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;
    private final MessageSource messageSource;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper, MessageSource messageSource) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
        this.messageSource = messageSource;
    }

    @Override
    public WarehouseData createWarehouse(WarehouseData warehouseData) {
        logger.debug("Warehouse create request: {}", warehouseData);
        WarehouseEntity entity = warehouseMapper.toEntity(warehouseData);
        WarehouseEntity saved = warehouseRepository.save(entity);
        return warehouseMapper.toDto(saved);
    }

    @Override
    public WarehouseData getWarehouseById(UUID id) {
        logger.debug("Warehouse get request: {}", id);
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<WarehouseData> getAllWarehouses() {
        logger.debug("Warehouse get request of list of warehouses");
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseData updateWarehouse(UUID id, WarehouseData warehouseData) {
        logger.debug("Warehouse update request: {}", warehouseData);
        Optional<WarehouseEntity> existing = warehouseRepository.findById(id);
        if (existing.isPresent()) {
            WarehouseEntity entity = existing.get();
            entity.setCode(warehouseData.getCode());
            entity.setName(warehouseData.getName());
            entity.setDescription(warehouseData.getDescription());
            WarehouseEntity updated = warehouseRepository.save(entity);
            return warehouseMapper.toDto(updated);
        }
        return null;
    }

    @Override
    public void deleteWarehouse(UUID id) {
        logger.debug("Warehouse delete request: {}", id);
        checkWarehouseExistence(id);
        warehouseRepository.deleteById(id);
    }

    private void checkWarehouseExistence(UUID id) {
        if (!warehouseRepository.existsById(id)) {
            logger.error("Warehouse does not exist: {}", id);
            throw new IllegalArgumentException(messageSource.getMessage("warehouse.not-found.id", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
    }
}

