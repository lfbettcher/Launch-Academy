package com.launchacademy.petTracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pets")
@NoArgsConstructor
@Getter
@Setter
public class Pet {

  @Id
  @SequenceGenerator(name = "pet_generator", sequenceName = "pets_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Length(min = 1, max = 19)
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "species_id", nullable = false)
  private Species species;

  @NotBlank
  @Length(min = 1, max = 14)
  @Pattern(regexp = "\\D+", message = "Cannot contain numbers")
  @Column(name = "breed", nullable = false)
  private String breed;

  @NotNull
  @Range(min = 1, max = 999)
  @Column(name = "age", nullable = false)
  private Integer age;

  @NotNull
  @Column(name = "neutered", nullable = false)
  private Boolean neutered;
}
