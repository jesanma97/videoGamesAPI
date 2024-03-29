package com.mca.infrastructure.adapters.out.persistence.dao;

import com.mca.infrastructure.adapters.out.persistence.entities.SagaRelatedEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaRelatedPKEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaRelatedDao extends JpaRepository<SagaRelatedEntity, SagaRelatedPKEntity> {
    @Query(value = "SELECT DISTINCT CASE " +
            "WHEN s.sagaRelatedPK.sagaRelated1.id = :sagaId THEN s.sagaRelatedPK.sagaRelated2.id " +
            "ELSE s.sagaRelatedPK.sagaRelated1.id END AS saga_related_id," +
            "CASE WHEN s.sagaRelatedPK.sagaRelated1.id = :sagaId THEN s.sagaRelatedPK.sagaRelated2.relevance " +
            "ELSE s.sagaRelatedPK.sagaRelated1.relevance END AS relevance " +
            "FROM SagaRelatedEntity s " +
            "WHERE s.sagaRelatedPK.sagaRelated1.id = :sagaId OR s.sagaRelatedPK.sagaRelated2.id = :sagaId")
    List<Object[]> getListIdsSagasRelatedBySagaId(@Param("sagaId") int sagaId);
}
