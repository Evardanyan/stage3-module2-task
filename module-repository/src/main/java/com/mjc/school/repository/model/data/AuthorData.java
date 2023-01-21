package com.mjc.school.repository.model.data;

import com.mjc.school.repository.utils.Utils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthorData {

    private static final String AUTHORS_FILE_NAME = "authors";
    private List<AuthorModel> authorList;

    @PostConstruct
    private void init() {
        this.authorList = new ArrayList<AuthorModel>();
        for (long i = 1L; i <= 20L; ++i) {
            this.authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath("authors")));
        }
    }

    public List<AuthorModel> getAuthorList() {
        return this.authorList;
    }

}
