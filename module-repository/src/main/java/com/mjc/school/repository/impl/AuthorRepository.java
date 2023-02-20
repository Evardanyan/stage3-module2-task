package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = entityManager.find(AuthorModel.class, entity.getId());
        authorModel.setName(entity.getName());
        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<AuthorModel> authorModel = readById(id);
        if (authorModel.isPresent()) {
            entityManager.remove(authorModel.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.createQuery("SELECT COUNT(a) FROM AuthorModel a WHERE a.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }
}
