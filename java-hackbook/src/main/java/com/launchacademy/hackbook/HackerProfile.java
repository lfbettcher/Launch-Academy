package com.launchacademy.hackbook;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="hacker_profiles")
public class HackerProfile {
  @Id
  @SequenceGenerator(name="profile_generator", sequenceName="hacker_profiles_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="profile_generator")
  @Column(name="id", nullable=false, unique=true)
  private long id;
  @Column(name="first_name", nullable = false)
  private String firstName;
  @Column(name="last_name", nullable = false)
  private String lastName;
  @Column(name="email_address", nullable = false)
  private String emailAddress;
  @Column(name="favorite_language", nullable = false)
  private String favoriteLanguage;
  @Column(name="status", nullable = false)
  private String status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getFavoriteLanguage() {
    return favoriteLanguage;
  }

  public void setFavoriteLanguage(String favoriteLanguage) {
    this.favoriteLanguage = favoriteLanguage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
