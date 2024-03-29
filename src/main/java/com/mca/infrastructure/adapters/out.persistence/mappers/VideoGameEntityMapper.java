package com.mca.infrastructure.adapters.out.persistence.mappers;

import com.mca.infrastructure.adapters.out.persistence.entities.VideoGameEntity;
import com.mca.infrastructure.model.VideoGame;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VideoGameEntityMapper {
    VideoGameEntityMapper INSTANCE = Mappers.getMapper(VideoGameEntityMapper.class);

    List<VideoGame> videoGameEntityListToVideoGameList(List<VideoGameEntity> videoGameEntityList);
    List<VideoGameEntity> videoGameListToVideoGameEntityList(List<VideoGame> videoGameList);
    VideoGame videoGameEntityToVideoGame(VideoGameEntity videoGameEntity);
    VideoGameEntity videoGameToVideoGameEntity(VideoGame videoGame);
}
