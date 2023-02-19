package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import org.mapstruct.*;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AuthorModelMapper.class})
public interface NewsModelMapper {
    List<NewsDtoResponse> modelListToDtoList(List<NewsModel> var1);

//    @Mapping(target = "authorId", source = "authorModel.id")
//    @Mapping(target = "tagId", source = "tagModel", qualifiedByName = "tagModelToTagId")
//    NewsDtoResponse modelToDto(NewsModel var1);
//
//    @Named("tagModelToTagId")
//    default Long tagModelToTagId(List<TagModel> tagModel) {
//        return tagModel.stream().findFirst().orElse(new TagModel()).getId();
//    }
//
//
//    @Mappings(value={@Mapping(target="createDate", ignore=true), @Mapping(target="lastUpdatedDate", ignore=true),
//            @Mapping(target = "authorModel.id", source = "authorId")})
//    NewsModel dtoToModel(NewsDtoRequest var1);

    @Mapping(target = "authorId", source = "authorModel.id")
    @Mapping(target = "tagId", source = "tagModel", qualifiedByName = "tagModelToTagId")
    NewsDtoResponse modelToDto(NewsModel var1);

    @Named("tagModelToTagId")
    default Long tagModelToTagId(List<TagModel> tagModel) {
        if (tagModel != null) {
            return tagModel.stream().findFirst().orElse(new TagModel()).getId();
        }
        return null;
    }

    @Mappings(value={@Mapping(target="createDate", ignore=true), @Mapping(target="lastUpdatedDate", ignore=true),
            @Mapping(target = "authorModel.id", source = "authorId")})
    @Mapping(target = "tagModel", source = "tagId", qualifiedByName = "tagIdToTagModel")
    NewsModel dtoToModel(NewsDtoRequest var1);


    @Named("tagIdToTagModel")
    default List<TagModel> tagIdToTagModel(Long tagId) {
        if (tagId == null) {
            return null;
        } else {
            return Collections.singletonList(new TagModel(tagId));
        }
    }

}
