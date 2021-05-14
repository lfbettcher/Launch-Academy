package com.launchacademy.launchrpg.seeders;

import com.google.inject.internal.util.Lists;
import com.launchacademy.launchrpg.models.Archetype;
import com.launchacademy.launchrpg.models.PlayerCharacter;
import com.launchacademy.launchrpg.repositories.ArchetypeRepository;
import com.launchacademy.launchrpg.repositories.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoSeeder implements CommandLineRunner {

  private PlayerCharacterRepository playerCharacterRepository;
  private ArchetypeRepository archetypeRepository;

  @Autowired
  public DemoSeeder(PlayerCharacterRepository playerCharacterRepository,
      ArchetypeRepository archetypeRepository) {
    this.playerCharacterRepository = playerCharacterRepository;
    this.archetypeRepository = archetypeRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Archetype archetype = new Archetype();
    PlayerCharacter playerCharacter = new PlayerCharacter();

    if (Lists.newArrayList(archetypeRepository.findAll()).size() == 0) {
      archetype.setType("Rogue");
      archetype.setHitDice(8);
      archetype.setPrimary_stat("Dex");
      archetypeRepository.save(archetype);
    }

    if (Lists.newArrayList(playerCharacterRepository.findAll()).size() == 0) {
      playerCharacter.setName("Regis");
      playerCharacter.setArchetype(archetype);
      playerCharacter.setRace("Halfling");
      playerCharacter.setBackground("Icewind Dale's Halfling Rogue Extraordinarre");
      playerCharacterRepository.save(playerCharacter);
    }

  }
}