//package com.mjc.school.repository.utils;
//
//
//import com.mjc.school.repository.model.data.AuthorData;
//import com.mjc.school.repository.model.data.NewsData;
//import com.mjc.school.repository.model.impl.AuthorModel;
//import com.mjc.school.repository.model.impl.NewsModel;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//
//@Component
//public class DataSource {
//
//    private NewsData newsData;
//
//    private AuthorData authorData;
//
//    public DataSource(AuthorData authorData, NewsData newsData) {
//        this.authorData = authorData;
//        this.newsData = newsData;
//    }
//
//
//    private List<NewsModel> news;
//    private List<AuthorModel> authors;
//
//
//    @PostConstruct
//    public void init() {
//        news = newsData.getNewsList();
//        authors = authorData.getAuthorList();
//    }
//
//    public List<NewsModel> getNews() {
//        return this.news;
//    }
//
//    public List<AuthorModel> getAuthors() {
//        return this.authors;
//    }
//
//}
