package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
//@Scope("prototype")
@Table(name = "tag")
public class TagModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name = "news_tag",
//            joinColumns = @JoinColumn(name="tag_id"),
//            inverseJoinColumns = @JoinColumn(name = "news_id")
//    )
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tagModel")
    private List<NewsModel> newsModel;


    public TagModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagModel() {
    }

    public TagModel(Long tagId) {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsModel> getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(List<NewsModel> newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagModel tagModel = (TagModel) o;
        return id.equals(tagModel.id) && name.equals(tagModel.name) && newsModel.equals(tagModel.newsModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, newsModel);
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", newsModel=" + newsModel +
                '}';
    }
}
