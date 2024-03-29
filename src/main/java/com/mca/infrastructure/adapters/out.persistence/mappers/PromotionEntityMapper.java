package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.PromotionEntity;
import com.mca.infrastructure.model.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PromotionEntityMapper {
    PromotionEntityMapper INSTANCE = Mappers.getMapper(PromotionEntityMapper.class);

    List<Promotion> promotionEntityListToPromotionList(List<PromotionEntity> promotionEntityList);
    List<PromotionEntity> promotionListToPromotionEntityList(List<Promotion> promotionList);
    Promotion promotionEntityToPromotion(PromotionEntity promotionEntity);
    PromotionEntity promotionToPromotionEntity(Promotion promotion);
}
