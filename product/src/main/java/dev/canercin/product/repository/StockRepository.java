package dev.canercin.product.repository;

import dev.canercin.product.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StockRepository extends JpaRepository<StockEntity, UUID> {
}

