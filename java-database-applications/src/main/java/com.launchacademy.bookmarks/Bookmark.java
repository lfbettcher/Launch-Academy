package com.launchacademy.bookmarks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

  @Id
  @SequenceGenerator(name = "bookmark_generator", sequenceName = "bookmarks_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotEmpty
  @Column(name = "title", nullable = false)
  private String title;

  @NotEmpty
  @URL
  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "description")
  private String description;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}