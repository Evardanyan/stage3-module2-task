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
import java.util.stream.Collectors;


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

//    @Override
//    public NewsModel create(NewsModel model) {
//        entityManager.persist(model);
//        return model;
//    }

    @Override
    public NewsModel create(NewsModel model) {
        List<TagModel> existingTags = new ArrayList<>();
        for (TagModel tag : model.getTagModel()) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>" + tag.getId());
            if (tag.getId() != null) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>" + tag.getId());
                TagModel existingTag = entityManager.find(TagModel.class, tag.getId());
                if (existingTag != null) {
                    existingTags.add(existingTag);
                }
            }
        }
        model.setTagModel(existingTags);
        entityManager.persist(model);
        return model;
    }

//    @Override
//    public NewsModel create(NewsModel model) {
//        // Get the existing TagModels by their IDs
//        List<TagModel> existingTagModels = entityManager.createQuery("SELECT t FROM TagModel t WHERE t.id IN :ids", TagModel.class)
//                .setParameter("ids", model.getTagModel().stream().map(TagModel::getId).collect(Collectors.toList()))
//                .getResultList();
//
//        // Replace the tagModel list in the NewsModel with the existing TagModels
//        List<TagModel> mergedTagModels = model.getTagModel().stream()
//                .map(tag -> existingTagModels.stream().filter(existingTag -> existingTag.getId().equals(tag.getId())).findFirst().orElse(tag))
//                .collect(Collectors.toList());
//        model.setTagModel(mergedTagModels);
//
//        entityManager.persist(model);
//        return model;
//    }

    @Override
    public NewsModel update(NewsModel model) {
        NewsModel newsModel = entityManager.find(NewsModel.class, model.getId());
        newsModel.setTitle(model.getTitle());
        newsModel.setContent(model.getContent());
        return entityManager.merge(newsModel);
    }
//    @Override
//    public NewsModel update(NewsModel model) {
//        NewsModel newsModel = entityManager.find(NewsModel.class, model.getId());
//        if (newsModel != null) {
//            newsModel.setTitle(model.getTitle());
//            newsModel.setContent(model.getContent());
//            newsModel.setAuthorModel(model.getAuthorModel());
//            newsModel.setTagModel(model.getTagModel());
//            return entityManager.merge(newsModel);
//        }
//        return null;
//    }

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
