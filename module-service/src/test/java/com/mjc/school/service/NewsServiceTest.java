package com.mjc.school.service;


import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.data.AuthorData;
import com.mjc.school.repository.model.data.NewsData;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.mapper.NewsModelMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class NewsServiceTest {

    private NewsRepository newsRepository;

    private NewsService newsService;

    @BeforeEach
    public void init() throws NotFoundException {

        NewsModelMapper mapper = Mappers.getMapper(NewsModelMapper.class);

        AuthorData authorData = new AuthorData();

        authorData.init();

        NewsData newsData = new NewsData(authorData);

        newsData.init();

        DataSource dataSource = new DataSource(authorData, newsData);

        dataSource.init();

        newsRepository = new NewsRepository(dataSource);

        newsService = new NewsService(newsRepository, mapper);
    }

    @DisplayName("JUnit test for create method")
    @Test
    public void shouldCreateNewsModelSuccessFully() throws NotFoundException {

        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(null, "Testing NewsService", "JUnit test", 20L);

        newsService.create(newsDtoRequest);

        Long testResult = newsService.readById(21L).id();

        assertEquals(21L, testResult);

    }

    @DisplayName("JUnit test for update method")
    @Test
    public void shouldUpdateNewsModelSuccessFully() throws NotFoundException {

        Long id = 20L;
        Long authorId = 19L;
        String title = "Testing Update Method";
        String content = "This is new content";

        NewsDtoRequest newsUpdateData = new NewsDtoRequest(id, title, content, authorId);

        assertNotNull(newsService.update(newsUpdateData));
        newsService.update(newsUpdateData);
        Optional<NewsModel> updatedNewsModel = newsRepository.readById(id);

        assertEquals(title, updatedNewsModel.get().getTitle());
        assertEquals(content, updatedNewsModel.get().getContent());
        assertEquals(authorId, updatedNewsModel.get().getAuthorId());

    }

    @DisplayName("JUnit test for findAll method")
    @Test
    public void findAll() throws NotFoundException {

        List<NewsDtoResponse> newsDtoResponseList = newsService.readAll();

        assertEquals(newsDtoResponseList.size(), newsRepository.readAll().size());
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findById() throws NotFoundException {
        Long expectedId = 1L;

        NewsDtoResponse expected = newsService.readById(1L);

        Long testResult = newsService.readById(1L).id();

        assertEquals(expectedId, testResult);

        assertNotNull(expected);

    }

    @DisplayName("JUnit test for deleteById method")
    @Test
    public void shouldBeDelete() throws NotFoundException {
        Long newsId = 1L;

        assertTrue(newsService.deleteById(newsId));
        assertEquals(Optional.empty(), newsRepository.readById(newsId));

    }

    @AfterEach
    public void cleanData() {
        newsRepository = null;
        newsService = null;
    }

}
