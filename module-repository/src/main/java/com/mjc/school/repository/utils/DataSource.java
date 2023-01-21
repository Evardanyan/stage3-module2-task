package com.mjc.school.repository.utils;


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

    private List<NewsModel> news;

    @PostConstruct
    private void init() {
       news =  newsData.getNewsList();
    }

    public List<NewsModel> getNews() {
        return this.news;
    }

}
