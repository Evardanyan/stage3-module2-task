package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorModelMapper {
    public List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> var1);

    public AuthorDtoResponse modelToDto(AuthorModel var1);

    @Mappings(value={@Mapping(target="createDate", ignore=true), @Mapping(target="lastUpdatedDate", ignore=true)})
    public AuthorModel dtoToModel(AuthorDtoRequest var1);

}
