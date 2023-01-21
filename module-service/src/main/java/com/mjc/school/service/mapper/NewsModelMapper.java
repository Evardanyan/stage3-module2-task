package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface NewsModelMapper {
    public List<NewsDtoResponse> modelListToDtoList(List<NewsModel> var1);

    public NewsDtoResponse modelToDto(NewsModel var1);

    @Mappings(value={@Mapping(target="createDate", ignore=true), @Mapping(target="lastUpdatedDate", ignore=true)})
    public NewsModel dtoToModel(NewsDtoRequest var1);

}
