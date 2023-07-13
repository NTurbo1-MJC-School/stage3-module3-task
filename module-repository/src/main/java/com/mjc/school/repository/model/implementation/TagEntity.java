package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tag")
public class TagEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<NewsEntity> newsEntities;

    public TagEntity() {}

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

    public List<NewsEntity> getNewsEntities() {
        return newsEntities;
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
    }
}
