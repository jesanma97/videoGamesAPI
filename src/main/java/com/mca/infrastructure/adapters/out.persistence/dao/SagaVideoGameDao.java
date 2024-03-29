package com.mca.infrastructure.adapters.out.persistence.dao;

import com.mca.infrastructure.adapters.out.persistence.entities.SagaEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaVideoGameEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaVideoGamePKEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.VideoGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaVideoGameDao extends JpaRepository<SagaVideoGameEntity, SagaVideoGamePKEntity> {
    @Query(value = "SELECT sagaVideoGame.sagaVideoGamePK.saga " +
            "FROM SagaVideoGameEntity sagaVideoGame " +
            "WHERE sagaVideoGame.sagaVideoGamePK.videoGame.id = :gameId " +
            "ORDER BY sagaVideoGame.sagaVideoGamePK.saga.relevance DESC")
    List<SagaEntity> getListSagasByGameId(@Param("gameId") int gameId);
}
