package com.mjc.school.service.utils;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ModelMapper {

  List<NewsDtoResponse> newsEntityListToDtoList(List<NewsEntity> newsEntityList);
  List<AuthorDtoResponse> authorEntityListToDtoList(List<AuthorEntity> authorEntityList);
  List<TagDtoResponse> tagEntityListToDtoList(List<TagEntity> tagEntityList);

  NewsDtoResponse newsEntityToDto(NewsEntity newsEntity);
  AuthorDtoResponse authorEntityToDto(AuthorEntity authorEntity);
  TagDtoResponse tagEntityToDto(TagEntity tagEntity);

  @Mappings({
    @Mapping(target = "createDate", ignore = true),
    @Mapping(target = "lastUpdatedDate", ignore = true),
    @Mapping(target = "author", ignore = true),
    @Mapping(target = "tags", ignore = true)
  })
  NewsEntity dtoToAuthorEntity(NewsDtoRequest newsDtoRequest);
  AuthorEntity dtoToAuthorEntity(AuthorDtoRequest authorDtoRequest);
  TagEntity dtoToTagEntity(TagDtoRequest tagDtoRequest);
}
