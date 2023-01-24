package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateAuthorDto;
import com.mjc.school.service.annotation.ValidateAuthorId;
import com.mjc.school.service.annotation.ValidateNewsId;
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
//    private AuthorRepository baseRepository;
    private final BaseRepository<AuthorModel, Long> baseRepository;
    private  Validator authorValidator;

    private AuthorModelMapper mapper = (AuthorModelMapper)Mappers.getMapper((Class)AuthorModelMapper.class) ;

    public AuthorService(BaseRepository<AuthorModel, Long> baseRepository, Validator authorValidator) {
        this.baseRepository = baseRepository;
        this.authorValidator = authorValidator;
//        this.mapper = (AuthorModelMapper)Mappers.getMapper((Class)AuthorModelMapper.class);
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return (List<AuthorDtoResponse>)this.mapper.modelListToDtoList(this.baseRepository.readAll());
    }

    @Override
    @ValidateAuthorId
    public AuthorDtoResponse readById(Long id) {
//        this.authorValidator.validateAuthorId(id);
//        if (this.baseRepository.existById(id)) {
//            AuthorModel authorModel = this.baseRepository.readById(id).get();
//            return this.mapper.modelToDto(authorModel);
//        }
        AuthorModel authorModel = this.baseRepository.readById(id).get();
        return this.mapper.modelToDto(authorModel);
//        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), id));
    }

    @Override
    @ValidateAuthorDto
    public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
//        this.authorValidator.validateAuthorDto(dtoRequest);
        AuthorModel model = this.mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdatedDate(date);
        AuthorModel authorModel = this.baseRepository.create(model);
        return this.mapper.modelToDto(authorModel);
    }


    @Override
    @ValidateAuthorId
    @ValidateAuthorDto
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
//        this.authorValidator.validateAuthorId(dtoRequest.id());
//        this.authorValidator.validateAuthorDto(dtoRequest);
//        if (this.baseRepository.existById(dtoRequest.id())) {
//            AuthorModel model = this.mapper.dtoToModel(dtoRequest);
//            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//            model.setLastUpdatedDate(date);
//            AuthorModel authorModel = this.baseRepository.update(model);
//            return this.mapper.modelToDto(authorModel);
//        }
//        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), dtoRequest.id()));

        AuthorModel model = this.mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setLastUpdatedDate(date);
        AuthorModel authorModel = this.baseRepository.update(model);
        return this.mapper.modelToDto(authorModel);
    }

    @Override
    @ValidateAuthorId
    public boolean deleteById(Long id) {
//        this.authorValidator.validateAuthorId(id);
//        if (this.baseRepository.existById(id)) {
//            return this.baseRepository.deleteById(id);
//        }
//        throw new NotFoundException(String.format(ServiceErrorCodeMessage.NEWS_ID_DOES_NOT_EXIST.getCodeMsg(), id));
        return this.baseRepository.deleteById(id);
    }

}