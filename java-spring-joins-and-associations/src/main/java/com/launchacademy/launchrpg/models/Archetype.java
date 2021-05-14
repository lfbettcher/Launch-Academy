package com.launchacademy.launchrpg.models;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "archetypes")
public class Archetype {

  @Id
  @SequenceGenerator(name = "archetype_generator", sequenceName = "archetypes_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archetype_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  private String type;
  private Integer hitDice;
  private String primary_stat;

  @OneToMany(mappedBy = "archetype")
  private List<PlayerCharacter> playerCharacters = new ArrayList<PlayerCharacter>();

}