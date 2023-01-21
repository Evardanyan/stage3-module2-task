package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors()
                .stream()
                .filter(authorModel -> id.equals(authorModel.getId()))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> authorModels = dataSource.getAuthors();
        if (!authorModels.isEmpty()) {
            entity.setId(authorModels.get(authorModels.size() - 1).getId() + 1L);
        } else {
            entity.setId(1L);
        }
        authorModels.add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = readById(entity.getId()).get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(LocalDateTime.now());
        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        return readById(id).map(authorModel -> dataSource.getAuthors().remove(authorModel))
                .orElse(false);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors()
                .stream()
                .anyMatch(authorModel -> id.equals(authorModel.getId()));
    }
}
