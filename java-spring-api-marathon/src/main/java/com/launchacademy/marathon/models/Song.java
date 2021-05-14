package com.launchacademy.marathon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Getter
@Setter
public class Song {

  @Id
  @SequenceGenerator(name = "song_generator", sequenceName = "songs_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Length(min = 1, max = 25)
  @Column(name = "title", nullable = false)
  private String title;

  @NotBlank
  @Length(min = 1, max = 15)
  @Pattern(regexp = "\\D+", message = "Cannot contain numbers")
  @Column(name = "genre")
  private String genre;

  @NotNull
  @Range(min = 1900, max = 2021)
  @Column(name = "release_year", nullable = false)
  private Integer releaseYear;

  @NotNull
  @Column(name = "explicit_content", nullable = false)
  private Boolean explicitContent;
}
