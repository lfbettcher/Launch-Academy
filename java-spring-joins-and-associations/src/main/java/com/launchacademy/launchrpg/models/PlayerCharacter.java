package com.launchacademy.launchrpg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_characters")
public class PlayerCharacter {

  @Id
  @SequenceGenerator(name = "playerCharacter_generator", sequenceName = "playerCharacters_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerCharacter_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;
  private String name;
  private String race;
  private String background;

  @ManyToOne
  @JoinColumn(name = "archetype_id", nullable = false)
  private Archetype archetype;

  public void setArchetype(Archetype archetype) {
    this.archetype = archetype;
  }
}