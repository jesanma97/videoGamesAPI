package com.mca.infrastructure.adapters.out.persistence;

import com.mca.application.ports.out.SagaVideoGamePersistencePort;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaVideoGameDao;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaEntity;
import com.mca.infrastructure.adapters.out.persistence.entities.SagaVideoGameEntity;
import com.mca.infrastructure.adapters.out.persistence.mappers.SagaEntityMapper;
import com.mca.infrastructure.adapters.out.persistence.mappers.SagaVideoGameEntityMapper;
import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.model.SagaVideoGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SagaVideoGamePersistenceAdapter implements SagaVideoGamePersistencePort {
    private static final Logger log = LoggerFactory.getLogger(SagaVideoGamePersistenceAdapter.class);
    private SagaVideoGameDao sagaVideoGameDao;

    @Autowired
    public SagaVideoGamePersistenceAdapter(SagaVideoGameDao sagaVideoGameDao){
        this.sagaVideoGameDao = sagaVideoGameDao;
    }

    @Override
    public List<Saga> getListSagasByGameId(int gameId) {
        List<Saga> sagaList = new ArrayList<>();
        try{
            List<SagaEntity> sagaEntityList = sagaVideoGameDao.getListSagasByGameId(gameId);
            sagaList = SagaEntityMapper.INSTANCE.sagaEntityListToSagaList(sagaEntityList);
        }catch (Exception e){
            log.error(String.format("An error occurred while trying to retrieve the sagas from the game ID: %d", gameId));
        }
        return sagaList;
    }
}
