package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.utils.Utils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class NewsData {

    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private List<NewsModel> newsList;

    private AuthorData authorData;

    public NewsData(AuthorData authorData) {
        this.authorData = authorData;
    }

    @PostConstruct
    private void init() {
        List<AuthorModel> authorModelList = authorData.getAuthorList();
        this.newsList = new ArrayList<NewsModel>();
        final Random random = new Random();
        for (long i = 1L; i <= 20L; ++i) {
            final LocalDateTime date = Utils.getRandomDate();
            this.newsList.add(new NewsModel(i, Utils.getRandomContentByFilePath("news"), Utils.getRandomContentByFilePath("content"), date, date, authorModelList.get(random.nextInt(authorModelList.size())).getId()));
        }
    }

    public List<NewsModel> getNewsList() {
        return this.newsList;
    }

}
