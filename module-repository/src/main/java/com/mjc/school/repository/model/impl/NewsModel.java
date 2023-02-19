package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity
//@Scope("prototype")
@Table(name = "News")
public class NewsModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "news_tag",
            joinColumns = @JoinColumn(name="news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private List<TagModel> tagModel;

    public NewsModel() {
    }

    public NewsModel(final Long id, final String title, final String content, final AuthorModel authorModel) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorModel = authorModel;
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<TagModel> getTagModel() {
        return tagModel;
    }

    public void setTagModel(List<TagModel> tagModel) {
        this.tagModel = tagModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return id.equals(newsModel.id) && title.equals(newsModel.title) && content.equals(newsModel.content) && createDate.equals(newsModel.createDate) && lastUpdatedDate.equals(newsModel.lastUpdatedDate) && authorModel.equals(newsModel.authorModel) && tagModel.equals(newsModel.tagModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdatedDate, authorModel, tagModel);
    }


    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", authorModel=" + authorModel +
                ", tagModel=" + tagModel +
                '}';
    }
}
