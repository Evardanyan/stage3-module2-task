//package com.mjc.school.repository.model.data;
//
//import com.mjc.school.repository.model.impl.AuthorModel;
//import com.mjc.school.repository.utils.Utils;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Component
//public class AuthorData {
//
//    private List<AuthorModel> authorList;
//
//    @PostConstruct
//    public void init() {
//        this.authorList = new ArrayList<>();
//        for (long i = 1L; i <= 20L; ++i) {
//            final LocalDateTime date = Utils.getRandomDate();
//            this.authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath("authors"), date, date));
//        }
//    }
//
//    public List<AuthorModel> getAuthorList() {
//        return this.authorList;
//    }
//
//}
