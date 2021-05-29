package com.launchacademy.petTracker.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PetDto {

  private Integer petId;
  private String petName;
  private SpeciesDto species;
  private String breed;
  private Integer age;
  private Boolean neutered;
}
