package com.mjc.school.service;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.mapper.AuthorModelMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorModelMapper mapper;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;


//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//        AuthorModelMapper mapper = AuthorModelMapper.INSTANCE;
//        authorService = new AuthorService(authorRepository, mapper);
//    }

    @DisplayName("JUnit test for create method")
    @Test
    void shouldCreateAuthorModelSuccessfully() throws NotFoundException {

        LocalDateTime now = LocalDateTime.now();

        AuthorDtoRequest authorDto = new AuthorDtoRequest(1L, "test");

        AuthorModel authorModel = new AuthorModel("test");

        AuthorModel savedAuthorModel = new AuthorModel(1L, "test");
        savedAuthorModel.setCreateDate(now);
        savedAuthorModel.setLastUpdatedDate(now);

        when(mapper.dtoToModel(authorDto)).thenReturn(authorModel);

        when(authorRepository.create(authorModel)).thenReturn(savedAuthorModel);

        when(mapper.modelToDto(savedAuthorModel)).thenReturn(new AuthorDtoResponse(1L,"test", now, now));

        AuthorDtoResponse result = authorService.create(authorDto);

        assertEquals(new AuthorDtoResponse(1L, "test", now, now), result);

        verify(mapper, times(1)).dtoToModel(authorDto);
        verify(authorRepository, times(1)).create(authorModel);
        verify(mapper, times(1)).modelToDto(savedAuthorModel);

    }
    @DisplayName("JUnit test for update method")
    @Test
    void shouldUpdateNewsModelSuccessFully() throws NotFoundException {

        Long id = 20L;
        String name = "Edgar Vardanyan";

        AuthorDtoRequest authorUpdateData = new AuthorDtoRequest(id, name);

        assertNotNull(authorService.update(authorUpdateData));
        authorService.update(authorUpdateData);
        Optional<AuthorModel> updatedAuthorModel = authorRepository.readById(id);

        assertEquals(id, updatedAuthorModel.get().getId());
        assertEquals(name, updatedAuthorModel.get().getName());

    }

    @DisplayName("JUnit test for findAll method")
    @Test
    void findAll() throws NotFoundException {

        List<AuthorDtoResponse> authorDtoResponseList = authorService.readAll();

        assertEquals(authorDtoResponseList.size(), authorRepository.readAll().size());
    }

    @DisplayName("JUnit test for findById method")
    @Test
    void findById() throws NotFoundException {
        Long expectedId = 1L;

        AuthorDtoResponse expected = authorService.readById(1L);

        Long testResult = authorService.readById(1L).id();

        assertEquals(expectedId, testResult);

        assertNotNull(expected);

    }

    @DisplayName("JUnit test for deleteById method")
    @Test
    void shouldBeDelete() throws NotFoundException {
        Long newsId = 1L;

        assertTrue(authorService.deleteById(newsId));
        assertEquals(Optional.empty(), authorRepository.readById(newsId));

    }

    @AfterEach
    void cleanData() {
        authorRepository = null;
        authorService = null;
    }

}
