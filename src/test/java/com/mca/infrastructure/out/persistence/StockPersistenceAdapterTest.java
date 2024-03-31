package com.mca.infrastructure.out.persistence;

import com.mca.infrastructure.adapters.out.persistence.StockPersistenceAdapter;
import com.mca.infrastructure.adapters.out.persistence.dao.StockDao;
import com.mca.infrastructure.adapters.out.persistence.entities.StockEntity;
import com.mca.infrastructure.model.VideoGameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockPersistenceAdapterTest {
    @Mock
    private StockDao stockDao;
    @Mock
    private Logger logger;
    @InjectMocks
    private StockPersistenceAdapter stockPersistenceAdapter;
    VideoGameEvent videoGameEvent = new VideoGameEvent();

    @BeforeEach
    void setUp(){
        this.stockPersistenceAdapter = new StockPersistenceAdapter(stockDao);
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        videoGameEvent = new VideoGameEvent(1L, true, timestamp);
    }
    @Test
    @DisplayName("UpdateStockVideoGameKO")
    /*Este test no he conseguido que me funcione correctamente*/
    void updateStockVideoGameKO() {

        when(stockDao.findById(anyInt())).thenThrow(NullPointerException.class); // Simulando que el stock no está disponible

        assertThrows(NullPointerException.class, () -> stockPersistenceAdapter.updateStockVideoGame(videoGameEvent));

        ArgumentCaptor<Exception> argumentCaptor = ArgumentCaptor.forClass(NullPointerException.class);
        verify(logger).error(anyString(), argumentCaptor.capture());
        Exception capturedException = argumentCaptor.getValue();
        assertNotNull(capturedException);
    }

    @Test
    @DisplayName("UpdateStockVideoGameOK")
    void updateStockVideoGameOK() {
        when(stockDao.findById(Mockito.anyInt())).thenReturn(Optional.of(new StockEntity()));
        doNothing().when(this.stockDao).updateStock(Mockito.any());
        stockPersistenceAdapter.updateStockVideoGame(videoGameEvent);

    }


}
