package com.launchacademy.petTracker.mappers;

import com.launchacademy.petTracker.dtos.PetDto;
import com.launchacademy.petTracker.dtos.SpeciesDto;
import com.launchacademy.petTracker.models.Pet;
import com.launchacademy.petTracker.models.Species;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {

  Pet petDtoToPet(PetDto dto);

  PetDto petToPetDto(Pet entity);

  SpeciesDto speciesToSpeciesDto(Species entity);

  Species speciesDtoToSpecies(SpeciesDto dto);
}
