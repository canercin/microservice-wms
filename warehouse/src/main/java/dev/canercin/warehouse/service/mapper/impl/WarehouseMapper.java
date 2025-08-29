package dev.canercin.warehouse.service.mapper.impl;

import dev.canercin.warehouse.entity.WarehouseEntity;
import dev.canercin.warehouse.service.dto.WarehouseData;
import dev.canercin.warehouse.service.mapper.GenericMapper;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper implements GenericMapper<WarehouseEntity, WarehouseData> {

    @Override
    public WarehouseEntity toEntity(WarehouseData source) {
        if (source == null) {
            return null;
        }

        WarehouseEntity entity = new WarehouseEntity();
        entity.setId(source.getId());
        entity.setCode(source.getCode());
        entity.setName(source.getName());
        entity.setDescription(source.getDescription());
        return entity;
    }

    @Override
    public WarehouseData toDto(WarehouseEntity source) {
        if (source == null) {
            return null;
        }

        WarehouseData dto = new WarehouseData();
        dto.setId(source.getId());
        dto.setCode(source.getCode());
        dto.setName(source.getName());
        dto.setDescription(source.getDescription());
        return dto;
    }
}
