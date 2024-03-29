package com.mca.infrastructure.adapters.out.persistence;

import com.mca.application.ports.out.SagaRelatedPersistencePort;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaRelatedDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SagaRelatedPersistenceAdapter implements SagaRelatedPersistencePort {
    private static final Logger log = LoggerFactory.getLogger(SagaVideoGamePersistenceAdapter.class);
    private SagaRelatedDao sagaRelatedDao;

    @Autowired
    public SagaRelatedPersistenceAdapter(SagaRelatedDao sagaRelatedDao){
        this.sagaRelatedDao = sagaRelatedDao;
    }

    @Override
    public List<String> getIdsSagasRelatedBySagaId(int sagaId) {
        List<String> listIdsSagasObtained = new ArrayList<>();
        try{
            List<Object[]> listObjectsSagas = sagaRelatedDao.getListIdsSagasRelatedBySagaId(sagaId);
            Collections.sort(listObjectsSagas, (a, b) -> ((Integer) b[1]).compareTo((Integer) a[1]));
            listIdsSagasObtained = listObjectsSagas.stream().map(sagaObject -> sagaObject[0].toString()).collect(Collectors.toList());
        }catch (Exception e){
            log.error("",e);
            log.error(String.format("An error occurred while trying to retrieve the ids of sagas related with the id: %d", sagaId));
        }
        return listIdsSagasObtained;
    }
}
