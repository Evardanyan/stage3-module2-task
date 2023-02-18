//package com.mjc.school.service;
//
//
//import com.mjc.school.repository.impl.AuthorRepository;
//import com.mjc.school.repository.model.data.AuthorData;
//import com.mjc.school.repository.model.data.NewsData;
//import com.mjc.school.repository.model.impl.AuthorModel;
//import com.mjc.school.repository.utils.DataSource;
//import com.mjc.school.service.dto.AuthorDtoRequest;
//import com.mjc.school.service.dto.AuthorDtoResponse;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.impl.AuthorService;
//import com.mjc.school.service.mapper.AuthorModelMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class AuthorServiceTest {
//
//    private AuthorRepository authorRepository;
//
//    private AuthorService authorService;
//
//    @BeforeEach
//    void init() throws NotFoundException {
//
//        AuthorModelMapper mapper = Mappers.getMapper(AuthorModelMapper.class);
//
//        AuthorData authorData = new AuthorData();
//
//        authorData.init();
//
//        NewsData newsData = new NewsData(authorData);
//
//        newsData.init();
//
//        DataSource dataSource = new DataSource(authorData, newsData);
//
//        dataSource.init();
//
//        authorRepository = new AuthorRepository(dataSource);
//
//        authorService = new AuthorService(authorRepository, mapper);
//    }
//
//    @DisplayName("JUnit test for create method")
//    @Test
//    void shouldCreateNewsModelSuccessFully() throws NotFoundException {
//
//        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(21L, "Edgar Vardanyan");
//
//        authorService.create(authorDtoRequest);
//
//        Long testResult = authorService.readById(21L).id();
//
//        assertEquals(21L, testResult);
//
//    }
//
//    @DisplayName("JUnit test for update method")
//    @Test
//    void shouldUpdateNewsModelSuccessFully() throws NotFoundException {
//
//        Long id = 20L;
//        String name = "Edgar Vardanyan";
//
//        AuthorDtoRequest authorUpdateData = new AuthorDtoRequest(id, name);
//
//        assertNotNull(authorService.update(authorUpdateData));
//        authorService.update(authorUpdateData);
//        Optional<AuthorModel> updatedAuthorModel = authorRepository.readById(id);
//
//        assertEquals(id, updatedAuthorModel.get().getId());
//        assertEquals(name, updatedAuthorModel.get().getName());
//
//    }
//
//    @DisplayName("JUnit test for findAll method")
//    @Test
//    void findAll() throws NotFoundException {
//
//        List<AuthorDtoResponse> authorDtoResponseList = authorService.readAll();
//
//        assertEquals(authorDtoResponseList.size(), authorRepository.readAll().size());
//    }
//
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
//
//    @DisplayName("JUnit test for deleteById method")
//    @Test
//    void shouldBeDelete() throws NotFoundException {
//        Long newsId = 1L;
//
//        assertTrue(authorService.deleteById(newsId));
//        assertEquals(Optional.empty(), authorRepository.readById(newsId));
//
//    }
//
//    @AfterEach
//    void cleanData() {
//        authorRepository = null;
//        authorService = null;
//    }
//
//}
