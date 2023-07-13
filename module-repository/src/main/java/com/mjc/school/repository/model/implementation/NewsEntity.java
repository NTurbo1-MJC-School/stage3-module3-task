package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "News")
public class NewsEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime lastUpdatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthorEntity author;

    @ManyToMany
    @JoinTable(
            name = "News_Tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;

    public NewsEntity() {}

    public NewsEntity(
            Long id,
            String title,
            String content,
            LocalDateTime createDate,
            LocalDateTime lastUpdatedDate
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsEntity newsEntity)) return false;
        return Objects.equals(id, newsEntity.id) && Objects.equals(title, newsEntity.title) && Objects.equals(content, newsEntity.content) && Objects.equals(createDate, newsEntity.createDate) && Objects.equals(lastUpdatedDate, newsEntity.lastUpdatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdatedDate);
    }
}
