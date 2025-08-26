package dev.canercin.product.client;

import dev.canercin.product.client.data.WarehouseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "WAREHOUSE")
public interface WarehouseClient {

    @GetMapping("/warehouses/{id}")
    Optional<WarehouseData> getWarehouse(@PathVariable UUID id);
}
