package com.mjc.school.repository.utils;


import com.mjc.school.repository.model.data.AuthorData;
import com.mjc.school.repository.model.data.AuthorModel;
import com.mjc.school.repository.model.data.NewsData;
import com.mjc.school.repository.model.data.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class DataSource {

    @Autowired
    private NewsData newsData;

    @Autowired
    private AuthorData authorData;

    private List<NewsModel> news;
    private List<AuthorModel> authors;


    @PostConstruct
    private void init() {
       news =  newsData.getNewsList();
       authors = authorData.getAuthorList();
    }

    public List<NewsModel> getNews() {
        return this.news;
    }

    public List<AuthorModel> getAuthors() {
        return this.authors;
    }

}
