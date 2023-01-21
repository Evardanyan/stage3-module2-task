package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.mapper.AuthorModelMapper;
import com.mjc.school.service.validator.Validator;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private BaseRepository<AuthorModel, Long> baseRepository;
    private  Validator authorValidator;
    private AuthorModelMapper mapper;

    public AuthorService(BaseRepository<AuthorModel, Long> baseRepository, Validator authorValidator) {
        this.baseRepository = baseRepository;
        this.mapper = (AuthorModelMapper) Mappers.getMapper((Class) AuthorModelMapper.class);
        this.authorValidator = authorValidator;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return (List<AuthorDtoResponse>)this.mapper.modelListToDtoList(this.baseRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long authorId) {
        this.authorValidator.validateAuthorId(authorId);
        if (this.baseRepository.existById(authorId)) {
            AuthorModel authorModel = this.baseRepository.readById(authorId).get();
            return this.mapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), authorId));
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
        this.authorValidator.validateAuthorDto(dtoRequest);
        AuthorModel model = this.mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdateDate(date);
        AuthorModel authorModel = this.baseRepository.create(model);
        return this.mapper.modelToDto(authorModel);
    }


    @Override
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
        this.authorValidator.validateAuthorId(dtoRequest.id());
        this.authorValidator.validateAuthorDto(dtoRequest);
        if (this.baseRepository.existById(dtoRequest.id())) {
            AuthorModel model = this.mapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setLastUpdateDate(date);
            AuthorModel authorModel = this.baseRepository.update(model);
            return this.mapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), dtoRequest.id()));
    }

    @Override
    public boolean deleteById(Long authord) {
        this.authorValidator.validateAuthorId(authord);
        if (this.baseRepository.existById(authord)) {
            return this.baseRepository.deleteById(authord);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), authord));
    }

}