package com.mjc.school.service;


import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.TagService;
import com.mjc.school.service.mapper.TagModelMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagModelMapper mapper;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    private AuthorModel authorModel;
    private AuthorDtoResponse authorDtoResponse;


    @DisplayName("JUnit test for create method")
    @Test
    void shouldCreateAuthorModelSuccessfully() throws NotFoundException {

        TagDtoRequest tagDto = new TagDtoRequest(1L, "Tag Name");

        TagModel tagModel = new TagModel(1L, "Tag Name");

        TagModel savedTagModel = new TagModel(1L, "Tag Name");


        when(mapper.dtoToModel(tagDto)).thenReturn(tagModel);
        when(tagRepository.create(any(TagModel.class))).thenReturn(savedTagModel);
        when(mapper.modelToDto(savedTagModel)).thenReturn(new TagDtoResponse(1L, "Tag Name"));

        TagDtoResponse result = tagService.create(tagDto);

        assertEquals(new TagDtoResponse(1L, "Tag Name"), result);

        verify(mapper, times(1)).dtoToModel(tagDto);
        verify(tagRepository, times(1)).create(tagModel);
        verify(mapper, times(1)).modelToDto(savedTagModel);

    }


    @DisplayName("JUnit test for update method")
    @Test
    void shouldUpdateAuthorModelSuccessFully() throws NotFoundException {
        Long id = 1L;

        TagModel tagModel = new TagModel();
        tagModel.setId(id);
        tagModel.setName("Tag Name");


        TagDtoRequest tagDtoRequest = new TagDtoRequest(id, "Tag Name");

        TagDtoResponse tagDtoResponse = new TagDtoResponse(id, "Tag Name");

        when(tagRepository.existById(id)).thenReturn(true);
        when(mapper.dtoToModel(tagDtoRequest)).thenReturn(tagModel);
        when(tagRepository.update(any(TagModel.class))).thenReturn(tagModel);
        when(mapper.modelToDto(any(TagModel.class))).thenReturn(tagDtoResponse);

        TagDtoResponse result = tagService.update(tagDtoRequest);

        assertEquals(tagDtoResponse, result);
    }


    @DisplayName("JUnit test for readAll method")
    @Test
    void testReadAll() {
        List<TagModel> tagModels = new ArrayList<>();
        tagModels.add(new TagModel(1L, "Tag name 1"));
        tagModels.add(new TagModel(2L, "Tag name 2"));

        List<TagDtoResponse> tagDtoResponses = new ArrayList<>();
        tagDtoResponses.add(new TagDtoResponse(1L, "Tag name 1"));
        tagDtoResponses.add(new TagDtoResponse(2L, "Tag name 2"));

        when(tagRepository.readAll()).thenReturn(tagModels);
        when(mapper.modelListToDtoList(tagModels)).thenReturn(tagDtoResponses);

        List<TagDtoResponse> result = tagService.readAll();

        assertEquals(tagDtoResponses, result);
    }


    @DisplayName("JUnit test for readById method")
    @Test
    void readByIdTest() {
        Long id = 1L;

        TagModel tagModel = new TagModel();
        tagModel.setId(id);
        tagModel.setName("Tag Name");

        TagDtoResponse tagDtoResponse = new TagDtoResponse(id, "Tag Name");

        when(tagRepository.existById(anyLong())).thenReturn(true);
        when(tagRepository.readById(anyLong())).thenReturn(Optional.of(tagModel));
        when(mapper.modelToDto(tagModel)).thenReturn(tagDtoResponse);


        TagDtoResponse result = tagService.readById(1L);

        assertEquals(tagDtoResponse, result);
    }


    @DisplayName("JUnit test for deleteById method")
    @Test
    void testDeleteById() {
        Long id = 1L;
        when(tagRepository.existById(id)).thenReturn(true);
        when(tagRepository.deleteById(id)).thenReturn(true);

        boolean deleted = tagService.deleteById(id);

        assertTrue(deleted);
        verify(tagRepository, times(1)).existById(id);
        verify(tagRepository, times(1)).deleteById(id);
    }

    @DisplayName("JUnit test for deleteByIdNonExisting")
    @Test
    void testDeleteByIdNonExisting() {

        Long id = 1L;
        when(tagRepository.existById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> tagService.deleteById(id));
        verify(tagRepository, times(1)).existById(id);
        verify(tagRepository, never()).deleteById(id);
    }

}
