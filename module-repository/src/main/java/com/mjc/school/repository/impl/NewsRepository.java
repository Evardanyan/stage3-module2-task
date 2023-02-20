package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class NewsRepository implements BaseRepository<NewsModel, Long> {


    @PersistenceContext
    private EntityManager entityManager;

    public Optional<NewsModel> readTagsByNewsId(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        return Optional.ofNullable(newsModel);
    }

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
        if (model.getTagId() != null) {
            List<TagModel> tagModels = entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
            List<TagModel> existingTagModels = new ArrayList<>();
            for (TagModel tagModel : tagModels) {
                TagModel existingTagModel = entityManager.find(TagModel.class, model.getTagId());
                if (existingTagModel != null) {
                    existingTagModels.add(existingTagModel);
                } else {
                    existingTagModels.add(null);
                }
            }
            model.setTagModels(existingTagModels);
        }
        entityManager.persist(model);
        return model;
    }


    @Override
    public NewsModel update(NewsModel model) {
        NewsModel newsModel = entityManager.find(NewsModel.class, model.getId());
        newsModel.setTitle(model.getTitle());
        newsModel.setContent(model.getContent());
        if (model.getTagId() != null) {
            List<TagModel> tagModels = entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
            List<TagModel> existingTagModels = new ArrayList<>();
            TagModel existingTagModel = entityManager.find(TagModel.class, model.getTagId());
            if (existingTagModel != null) {
                existingTagModels.add(existingTagModel);
            } else {
                existingTagModels.add(null);
            }
            newsModel.setTagModels(existingTagModels);
        }
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
