package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.StockEntity;
import com.mca.infrastructure.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StockEntityMapper {
    StockEntityMapper INSTANCE = Mappers.getMapper(StockEntityMapper.class);

    List<Stock> stockEntityListToStockList(List<StockEntity> stockEntityList);
    List<StockEntity> stockListToStockEntityList(List<Stock> stockList);
    Stock stockEntityToStock(StockEntity stockEntity);
    StockEntity stockToStockEntity(Stock stock);
}
