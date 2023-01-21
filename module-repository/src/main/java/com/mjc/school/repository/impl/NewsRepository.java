package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    public NewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public List<NewsModel> readAll() {
        return this.dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long newsId) {
//        return Optional.of(this.dataSource.getNews().stream().filter(news -> newsId.equals(news.getId())).findFirst().get());
        return this.dataSource.getNews().stream().filter(news -> newsId.equals(news.getId())).findFirst();
    }

    @Override
    public NewsModel create(NewsModel model) {
        List<NewsModel> newsModel = this.dataSource.getNews();
        newsModel.sort(Comparator.comparing((NewsModel::getId)));
        if (!newsModel.isEmpty()) {
            model.setId(newsModel.get(newsModel.size() - 1).getId() + 1L);
        }
        else {
            model.setId(1L);
        }
        newsModel.add(model);
        return model;
    }

    @Override
    public NewsModel update(NewsModel model) {
        Optional<NewsModel> newsModel = this.readById(model.getId());
        newsModel.get().setTitle(model.getTitle());
        newsModel.get().setContent(model.getContent());
        newsModel.get().setLastUpdatedDate(model.getLastUpdatedDate());
        newsModel.get().setAuthorId(model.getAuthorId());
        return newsModel.get();
    }

    @Override
    public boolean deleteById(Long newsId) {
        final List<NewsModel> deleteList = new ArrayList<>();
        deleteList.add(this.readById(newsId).get());
        return this.dataSource.getNews().removeAll(deleteList);
    }

    @Override
    public boolean  existById(Long newsId) {
        return this.dataSource.getNews().stream().anyMatch(news -> newsId.equals(news.getId()));
    }

}
