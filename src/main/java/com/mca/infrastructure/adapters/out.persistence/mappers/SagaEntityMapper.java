package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.SagaEntity;
import com.mca.infrastructure.model.Saga;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SagaEntityMapper {
    SagaEntityMapper INSTANCE = Mappers.getMapper(SagaEntityMapper.class);

    List<Saga> sagaEntityListToSagaList(List<SagaEntity> sagaEntityList);
    List<SagaEntity> sagaListToSagaEntityList(List<Saga> sagaList);
    Saga sagaEntityToSaga(SagaEntity sagaEntity);
    SagaEntity sagaToSagaEntity(Saga saga);
}
