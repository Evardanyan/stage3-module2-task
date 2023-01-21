package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;

import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.mapper.NewsModelMapper;
import com.mjc.school.service.validator.Validator;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private BaseRepository<NewsModel, Long> baseRepository;
    private final Validator newsValidator;
    private NewsModelMapper mapper;

    public NewsService(BaseRepository<NewsModel, Long> baseRepository, Validator newsValidator) {
        this.baseRepository = baseRepository;
        this.mapper = (NewsModelMapper)Mappers.getMapper((Class) NewsModelMapper.class);
        this.newsValidator = newsValidator;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return (List<NewsDtoResponse>)this.mapper.modelListToDtoList(this.baseRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long newsId) {
        this.newsValidator.validateNewsId(newsId);
        if (this.baseRepository.existById(newsId)) {
            NewsModel newsModel = this.baseRepository.readById(newsId).get();
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        this.newsValidator.validateNewsDto(dtoRequest);
        NewsModel model = this.mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdatedDate(date);
        NewsModel newsModel = this.baseRepository.create(model);
        return this.mapper.modelToDto(newsModel);
    }


    @Override
    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        this.newsValidator.validateNewsId(dtoRequest.id());
        this.newsValidator.validateNewsDto(dtoRequest);
        if (this.baseRepository.existById(dtoRequest.id())) {
            NewsModel model = this.mapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setLastUpdatedDate(date);
            NewsModel newsModel = this.baseRepository.update(model);
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), dtoRequest.id()));
    }

    @Override
    public boolean deleteById(Long newsId) {
        this.newsValidator.validateNewsId(newsId);
        if (this.baseRepository.existById(newsId)) {
            return this.baseRepository.deleteById(newsId);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
    }

}