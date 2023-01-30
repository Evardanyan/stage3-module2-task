package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateNewsDto;
import com.mjc.school.service.annotation.ValidateNewsId;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.mapper.NewsModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> baseRepository;

    private NewsModelMapper mapper;

    public NewsService(BaseRepository<NewsModel, Long> baseRepository, NewsModelMapper mapper) {
        this.baseRepository = baseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return  this.mapper.modelListToDtoList(this.baseRepository.readAll());
    }

    @Override
    @ValidateNewsId
    public NewsDtoResponse readById(Long id) {
        if (this.baseRepository.existById(id)) {
            NewsModel newsModel = this.baseRepository.readById(id).get();
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), id));
    }

    @Override
    @ValidateNewsDto
    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        if (!this.baseRepository.existById(dtoRequest.id())) {
            NewsModel model = this.mapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setCreateDate(date);
            model.setLastUpdatedDate(date);
            NewsModel newsModel = this.baseRepository.create(model);
            return this.mapper.modelToDto(newsModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_ALREADY_EXIST.getCodeMsg(), dtoRequest.id()));
    }


    @Override
    @ValidateNewsId
    @ValidateNewsDto
    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
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
    @ValidateNewsId
    @OnDelete
    public boolean deleteById(Long newsId) {
        if (this.baseRepository.existById(newsId)) {
            return this.baseRepository.deleteById(newsId);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
    }

}