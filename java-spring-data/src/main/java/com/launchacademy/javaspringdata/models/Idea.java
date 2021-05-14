package com.launchacademy.javaspringdata.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ideas")
public class Idea {

  @Id
  @SequenceGenerator(name = "idea_generator",
      sequenceName = "ideas_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "idea_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotEmpty
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}