package com.launchacademy.dogbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "dogs")
public class Dog {

  @Id
  @SequenceGenerator(name = "dog_generator", sequenceName = "dogs_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dog_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotEmpty
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotEmpty
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @NotNull
  @URL
  @Column(name = "photo_url", nullable = false)
  private String photoUrl;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "breed_id")
  private Breed breed;

  @NotEmpty
  @Column(name = "sex", nullable = false)
  private String sex;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhotoUrl() {
    return this.photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Breed getBreed() {
    return this.breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}