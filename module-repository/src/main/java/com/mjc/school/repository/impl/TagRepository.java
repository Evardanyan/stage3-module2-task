package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        TagModel tagModel = entityManager.find(TagModel.class, entity.getId());
        tagModel.setName(entity.getName());
        return tagModel;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<TagModel> tagModel = readById(id);
        if (tagModel.isPresent()) {
            entityManager.remove(tagModel.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.createQuery("SELECT COUNT(t) FROM TagModel t WHERE t.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }
}
