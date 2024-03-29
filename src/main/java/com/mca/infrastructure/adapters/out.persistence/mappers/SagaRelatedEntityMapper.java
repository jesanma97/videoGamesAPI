package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.SagaRelatedEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaRelatedPKEntity;
import com.mca.infrastructure.model.SagaRelated;
import com.mca.infrastructure.model.SagaRelatedPK;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SagaRelatedEntityMapper {
    SagaRelatedEntityMapper INSTANCE = Mappers.getMapper(SagaRelatedEntityMapper.class);
    List<SagaRelated> sagaRelatedEntityListToSagaRelatedList(List<SagaRelatedEntity> sagaRelatedEntityList);
    List<SagaRelatedEntity> sagaRelatedListToSagaRelatedEntityList(List<SagaRelated> sagaRelatedEntityList);
    SagaRelated sagaRelatedEntityToSagaRelated(SagaRelatedEntity sagaRelatedEntity);
    SagaRelatedEntity sagaRelatedToSagaRelatedEntity(SagaRelated sagaRelated);
    SagaRelatedPKEntity sagaRelatedPKToSagaRelatedPKEntity(SagaRelatedPK sagaRelatedPK);
    SagaRelatedPK sagaRelatedPKEntityToSagaRelatedPK(SagaRelatedPKEntity sagaRelatedPK);
}
