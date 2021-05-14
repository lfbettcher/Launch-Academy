package com.launchacademy.javaspringdata.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_categories")
@Getter
@Setter
public class AppCategory {

  @Id
  @SequenceGenerator(name = "app_category_generator",
      sequenceName = "app_categories_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "app_category_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotEmpty
  @Column(name = "name", nullable = false, unique = true)
  private String name;
}