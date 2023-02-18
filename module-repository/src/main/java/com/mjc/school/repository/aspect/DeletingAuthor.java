//package com.mjc.school.repository.aspect;
//
//import com.mjc.school.repository.BaseRepository;
//import com.mjc.school.repository.model.impl.NewsModel;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Aspect
//@Component
//public class DeletingAuthor {
//
//    private final BaseRepository<NewsModel, Long> repository;
//
//    public DeletingAuthor(BaseRepository<NewsModel, Long> repository) {
//        this.repository = repository;
//    }
//
//    @AfterReturning("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(id)")
//    public void deleteNewsEntitiesWithId(Long id) {
//        List<NewsModel> newsToDelete = repository.readAll()
//                .stream()
//                .filter(newsModel -> newsModel.getAuthorId().equals(id))
//                .toList();
//        newsToDelete.forEach(newsModel -> repository.deleteById(newsModel.getId()));
//    }
//}
