package com.launchacademy.javaspringvalidations.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ideas")
@Getter
@Setter
public class Idea {
  @Id
  @SequenceGenerator(name="idea_generator",
      sequenceName="ideas_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE,
      generator="idea_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @NotBlank
  @Column(name="name", nullable=false)
  private String name;

  @Column(name="description")
  private String description;
}
