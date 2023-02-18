package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class NewsRepository implements BaseRepository<NewsModel, Long> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        return Optional.ofNullable(newsModel);
    }

    @Override
    public NewsModel create(NewsModel model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public NewsModel update(NewsModel model) {
        NewsModel newsModel = entityManager.find(NewsModel.class, model.getId());
        newsModel.setTitle(model.getTitle());
        newsModel.setContent(model.getContent());
        return entityManager.merge(newsModel);
    }

    @Override
    public boolean deleteById(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        if (newsModel != null) {
            entityManager.remove(newsModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long newsId) {
        return entityManager.createQuery("SELECT COUNT(n) FROM NewsModel n WHERE n.id = :id", Long.class)
                .setParameter("id", newsId)
                .getSingleResult() > 0;
    }

}
