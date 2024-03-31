package com.mca.infrastructure.adapters.out.persistence.dao;

import com.mca.infrastructure.adapters.out.persistence.entities.StockEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<StockEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE STOCK " +
            "SET AVAILABILITY = :#{#stock.availability}," +
            "LAST_UPDATED = :#{#stock.lastUpdated} " +
            "WHERE ID = :#{#stock.id}", nativeQuery = true)
    void updateStock(@Param("stock") StockEntity stockEntity);
}
