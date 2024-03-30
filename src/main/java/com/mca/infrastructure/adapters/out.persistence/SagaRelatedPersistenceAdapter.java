package com.mca.infrastructure.adapters.out.persistence;

import com.mca.application.ports.out.SagaRelatedPersistencePort;
import com.mca.infrastructure.adapters.out.persistence.dao.SagaRelatedDao;
import com.mca.infrastructure.model.ErrorMca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getIdsSagasRelatedBySagaId(int sagaId) {
        List<String> listIdsSagasObtained;
        try{
            List<Object[]> listObjectsSagas = sagaRelatedDao.getListIdsSagasRelatedBySagaId(sagaId);
            if(listObjectsSagas.size() == 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMca(HttpStatus.NOT_FOUND.value(), "No saga found"));
            }
            Collections.sort(listObjectsSagas, (a, b) -> ((Integer) b[1]).compareTo((Integer) a[1]));
            listIdsSagasObtained = listObjectsSagas.stream().map(sagaObject -> sagaObject[0].toString()).collect(Collectors.toList());
        }catch (Exception e){
            String error = String.format("An error occurred while trying to retrieve the ids of sagas related with the id: %d", sagaId);
            log.error(error, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        return ResponseEntity.ok(listIdsSagasObtained);
    }
}
