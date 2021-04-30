package com.launchacademy.dogbook.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "breeds")
public class Breed {

  @Id
  @SequenceGenerator(name = "breed_generator", sequenceName = "breeds_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breed_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotEmpty
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "breed")
  private List<Dog> dogs = new ArrayList<>();

  public Breed() {
  }

  public Breed(String name) {
    this.name = name;
  }

  public List<Dog> getDogs() {
    return this.dogs;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}