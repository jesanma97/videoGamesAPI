package com.mca.infrastructure.adapters.out.persistence.dao;

import com.mca.infrastructure.adapters.out.persistence.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<StockEntity, Long> {
}
