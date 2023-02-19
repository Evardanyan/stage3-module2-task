package com.mjc.school.service;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.mapper.AuthorModelMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private AuthorModel authorModel;
    private AuthorDtoResponse authorDtoResponse;
    @BeforeEach
    public void setup() {
        LocalDateTime now = LocalDateTime.now();
        authorModel = new AuthorModel();
        authorModel.setId(1L);
        authorModel.setName("Test Author");
        authorDtoResponse = new AuthorDtoResponse(1L, "Test Author", now, now);

    }




    @DisplayName("JUnit test for create method")
    @Test
    void shouldCreateAuthorModelSuccessfully() throws NotFoundException {

        LocalDateTime now = LocalDateTime.now();

        AuthorDtoRequest authorDto = new AuthorDtoRequest(1L, "Author Name");

        AuthorModel authorModel = new AuthorModel("Author Name");

        AuthorModel savedAuthorModel = new AuthorModel(1L, "Author Name");
        savedAuthorModel.setCreateDate(now);
        savedAuthorModel.setLastUpdatedDate(now);

        when(mapper.dtoToModel(authorDto)).thenReturn(authorModel);

//        when(authorRepository.create(authorModel)).thenReturn(savedAuthorModel);
        when(authorRepository.create(any(AuthorModel.class))).thenReturn(savedAuthorModel);

        when(mapper.modelToDto(savedAuthorModel)).thenReturn(new AuthorDtoResponse(1L,"Author Name", now, now));

        AuthorDtoResponse result = authorService.create(authorDto);

        assertEquals(new AuthorDtoResponse(1L, "Author Name", now, now), result);

        verify(mapper, times(1)).dtoToModel(authorDto);
        verify(authorRepository, times(1)).create(authorModel);
        verify(mapper, times(1)).modelToDto(savedAuthorModel);

    }



    @DisplayName("JUnit test for update method")
    @Test
    public void shouldUpdateAuthorModelSuccessFully() throws NotFoundException {
        LocalDateTime now = LocalDateTime.now();
        Long id = 1L;

        // Create a mock AuthorModel
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(id);
        authorModel.setName("Author Name");
        authorModel.setCreateDate(now);
        authorModel.setLastUpdatedDate(now);

        AuthorModel updatedAuthorModel = new AuthorModel();
        updatedAuthorModel.setId(id);
        updatedAuthorModel.setName("Author Name");
        updatedAuthorModel.setCreateDate(now);
        updatedAuthorModel.setLastUpdatedDate(now);

        // Create a mock AuthorDtoRequest
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(id, "Author Name");

        // Create a mock AuthorDtoResponse
        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(id, "Author Name", now, now);

        // Set up the mock interactions
        when(authorRepository.existById(id)).thenReturn(true);
        when(mapper.dtoToModel(authorDtoRequest)).thenReturn(authorModel);
        when(authorRepository.update(any(AuthorModel.class))).thenReturn(authorModel);
        when(mapper.modelToDto(any(AuthorModel.class))).thenReturn(authorDtoResponse);

        // Call the method being tested
        AuthorDtoResponse result = authorService.update(authorDtoRequest);

        // Verify the result
        assertEquals(authorDtoResponse, result);
    }

//    @DisplayName("JUnit test for update method")
//    @Test
//    public void updateAuthorSuccess() throws NotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//        // Create a sample AuthorDtoRequest object
//        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(1L, "Author Name update");
//
//        // Create a sample AuthorModel object
//        AuthorModel authorModel = new AuthorModel(1L, "Author Name");
//
//        // Create a sample AuthorDtoResponse object
//        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(1L, "Author Name update", now, now);
//
//        // Define behavior of the authorRepository mock
//        when(authorRepository.existById(1L)).thenReturn(true);
//        when(mapper.dtoToModel(authorDtoRequest)).thenReturn(authorModel);
//        when(authorRepository.update(authorModel)).thenReturn(authorModel);
//        when(mapper.modelToDto(authorModel)).thenReturn(authorDtoResponse);
//
//        // Call the update method of the authorService with the sample AuthorDtoRequest object
//        AuthorDtoResponse response = authorService.update(authorDtoRequest);
//
//
//        // Assert that the response object is not null
//        assertNotNull(response);
//
//        // Assert that the response object has the correct ID and name
//        assertEquals(response.id(), 1L);
//        assertEquals(response.name(), "Author Name update");
//    }


//    @DisplayName("JUnit test for findAll method")
//    @Test
//    void findAll() throws NotFoundException {
//
//        List<AuthorDtoResponse> authorDtoResponseList = authorService.readAll();
//
//        assertEquals(authorDtoResponseList.size(), authorRepository.readAll().size());
//    }

    @DisplayName("JUnit test for readAll method")
    @Test
    public void testReadAll() {
        LocalDateTime now = LocalDateTime.now();
        // Create mock data to be returned by the mocked BaseRepository
        List<AuthorModel> authorModels = new ArrayList<>();
        authorModels.add(new AuthorModel(1L, "John Doe"));
        authorModels.add(new AuthorModel(2L, "Jane Smith"));

        // Create mock data to be returned by the mocked AuthorModelMapper
        List<AuthorDtoResponse> authorDtoResponses = new ArrayList<>();
        authorDtoResponses.add(new AuthorDtoResponse(1L, "John Doe", now, now));
        authorDtoResponses.add(new AuthorDtoResponse(2L, "Jane Smith", now, now));

        // Configure the mock objects to return the mock data
        when(authorRepository.readAll()).thenReturn(authorModels);
        when(mapper.modelListToDtoList(authorModels)).thenReturn(authorDtoResponses);

        // Call the method being tested
        List<AuthorDtoResponse> result = authorService.readAll();

        // Assert that the result matches the expected output
        assertEquals(authorDtoResponses, result);
    }

//    @DisplayName("JUnit test for findById method")
//    @Test
//    void findById() throws NotFoundException {
//        Long expectedId = 1L;
//
//        AuthorDtoResponse expected = authorService.readById(1L);
//
//        Long testResult = authorService.readById(1L).id();
//
//        assertEquals(expectedId, testResult);
//
//        assertNotNull(expected);
//
//    }

    @DisplayName("JUnit test for readById method")
    @Test
    public void readByIdTest() {
        LocalDateTime now = LocalDateTime.now();
        Long id = 1L;
        // create a mock AuthorModel and AuthorDtoResponse
//        AuthorModel authorModel = mock(AuthorModel.class);
//        AuthorDtoResponse authorDtoResponse = mock(AuthorDtoResponse.class);

        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(id);
        authorModel.setName("Author Name");
        authorModel.setCreateDate(now);
        authorModel.setLastUpdatedDate(now);

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(id, "Author Name", now, now);

        // set up the behavior of the mock objects when methods are called
        when(authorRepository.existById(anyLong())).thenReturn(true);
        when(authorRepository.readById(anyLong())).thenReturn(Optional.of(authorModel));
        when(mapper.modelToDto(authorModel)).thenReturn(authorDtoResponse);

        // call the method being tested
        AuthorDtoResponse result = authorService.readById(1L);

        // verify that the behavior was as expected
        assertEquals(authorDtoResponse, result);
    }

//    @Test(expected = NotFoundException.class)
//    void testReadByIdNotFound() {
//        // Setup mock behavior
//        when(authorRepository.existById(2L)).thenReturn(false);
//
//        // Call the service method with non-existent ID
//        authorService.readById(2L);
//    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long id = 1L;
        when(authorRepository.existById(id)).thenReturn(true);
        when(authorRepository.deleteById(id)).thenReturn(true);

        // Act
        boolean deleted = authorService.deleteById(id);

        // Assert
        assertTrue(deleted);
        verify(authorRepository, times(1)).existById(id);
        verify(authorRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdNonExisting() {
        // Arrange
        Long id = 1L;
        when(authorRepository.existById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> authorService.deleteById(id));
        verify(authorRepository, times(1)).existById(id);
        verify(authorRepository, never()).deleteById(id);
    }

//    @DisplayName("JUnit test for deleteById method")
//    @Test
//    void shouldBeDelete() throws NotFoundException {
//        Long newsId = 1L;
//
//        assertTrue(authorService.deleteById(newsId));
//        assertEquals(Optional.empty(), authorRepository.readById(newsId));
//
//    }

    @AfterEach
    void cleanData() {
        authorRepository = null;
        authorService = null;
    }

}
