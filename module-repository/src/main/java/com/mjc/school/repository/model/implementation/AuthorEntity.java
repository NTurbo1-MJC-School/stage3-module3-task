package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Author")
public class AuthorEntity implements BaseEntity<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "author_id")
  private List<NewsEntity> newsEntities = new ArrayList<>();

  public AuthorEntity() {}

  public AuthorEntity(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AuthorEntity that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
