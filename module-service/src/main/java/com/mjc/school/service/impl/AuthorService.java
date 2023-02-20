package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateAuthorDto;
import com.mjc.school.service.annotation.ValidateAuthorId;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.mapper.AuthorModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> baseRepository;

    private AuthorModelMapper mapper;

    public AuthorService(BaseRepository<AuthorModel, Long> baseRepository, AuthorModelMapper mapper) {
        this.baseRepository = baseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return this.mapper.modelListToDtoList(this.baseRepository.readAll());
    }

    @Override
    @ValidateAuthorId
    public AuthorDtoResponse readById(Long id) {
        if (this.baseRepository.existById(id)) {
            AuthorModel authorModel = this.baseRepository.readById(id).get();
            return this.mapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), id));
    }

    @Override
    @ValidateAuthorDto
    public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
        if (!this.baseRepository.existById(dtoRequest.id())) {
            AuthorModel model = this.mapper.dtoToModel(dtoRequest);
//            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            AuthorModel authorModel = this.baseRepository.create(model);
            return this.mapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_ALREADY_EXIST.getCodeMsg(), dtoRequest.id()));
    }


    @Override
    @ValidateAuthorId
    @ValidateAuthorDto
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
        if (this.baseRepository.existById(dtoRequest.id())) {
            AuthorModel model = this.mapper.dtoToModel(dtoRequest);
//            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            AuthorModel authorModel = this.baseRepository.update(model);
            return this.mapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), dtoRequest.id()));

    }

    @Override
    @ValidateAuthorId
    public boolean deleteById(Long id) {
        if (this.baseRepository.existById(id)) {
            return this.baseRepository.deleteById(id);
        }
        throw new NotFoundException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), id));
    }

    @Override
    public AuthorDtoResponse readTagsByNewsId(Long id) {
        return null;
    }
}