package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.SagaVideoGameEntity;
import com.mca.infrastructure.model.SagaVideoGame;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SagaVideoGameEntityMapper {
    SagaVideoGameEntityMapper INSTANCE = Mappers.getMapper(SagaVideoGameEntityMapper.class);

    List<SagaVideoGame> sagaVideoGameEntityListToSagaVideoGameList(List<SagaVideoGameEntity> sagaVideoGameEntityList);
    List<SagaVideoGameEntity> sagaVideoListToSagaVideoGameEntityList(List<SagaVideoGame> sagaVideoGames);
    SagaVideoGame sagaVideoGameEntityToSagaVideoGame(SagaVideoGameEntity sagaVideoGameEntity);
    SagaVideoGameEntity sagaVideoGameToSagaVideoGameEntity(SagaVideoGame sagaVideoGame);
}
