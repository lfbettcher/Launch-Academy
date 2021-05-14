We have discussed how ORMs allow us to bridge the gap between our Java objects and our database tables using Hibernate and the JPA. Spring Data provides us more tools to interact with our database, but keeps the syntax for joining tables and associating entities mostly the same.

This article sets up our code for `java-spring-data-launch-rpg` so don't worry that it won't run as is.

## Learning Goals

- Define a one to many relationship
- Define a many to one relationship

## Getting Started

```no-highlight
createdb launch_rpg
et java-spring-joins-and-associations
cd java-spring-joins-and-associations
idea .
```

## The Key to Lasting Relationships

Recall that in our exploration of relational databases, we can have one-to-many relationships, many-to-many, and one-to-one relationships. Leveraging these relationships among tables allows us to eliminate duplication and to keep our data well-normalized.

For this article, we'll discuss associations in the context of a Roleplaying Game. Consider the following ER diagram while we build this out:

![Entity-Relationship Diagram for Classes and Characters](https://horizon-production.s3.amazonaws.com/images/article/java-spring-joins-and-associations/Untitled+Diagram.png)


## Creating our Table

We have provided the necessary migration to get started. Run `./mvnw flyway:migrate` to affect these schema changes. Once complete you will see that our `launch_rpg` database now has two tables: Archetypes and characters.

If we examine the migration we'll see one line worth calling out `Archetype_id INTEGER NOT NULL REFERENCES Archetypes(id),`; the `REFERENCES` keyword tells SQL that this is going to be a foreign key.

## Creating Our POJO's

Using our knowledge of annotating entities, we can create our `PlayerCharacter` POJO.
```java
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
@Table(name="player_characters")
public class PlayerCharacter {
  @Id
  @SequenceGenerator(name="playerCharacter_generator", sequenceName="playerCharacters_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="playerCharacter_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;
  private String name;
  private String race;
  private String background;

  @ManyToOne
  @JoinColumn(name="archetype_id", nullable = false)
  private Archetype archetype;

  public void setArchetype(Archetype archetype) {
    this.archetype = archetype;
  }
}

```

Pay attention to:

```java
@ManyToOne
@JoinColumn(name="archetype_id", nullable = false)
private Archetype archetype;

public void setArchetype(Archetype archetype) {
 this.archetype = archetype;
}
```

Here we are telling it the name of the column on the PlayerCharacter Entity (or in the `player_characters` table) which will be used to find the `Archetype` associated with it. This is covered by the `@Setter` annotation, but we wanted to call it out specifically so that you could see under the hood.

We can also similarly create our `Archetype` POJO.

```java
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
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="archetypes")
public class Archetype {
  @Id
  @SequenceGenerator(name="archetype_generator", sequenceName="archetypes_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="archetype_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  private String type;
  private Integer hitDice;
  private String primary_stat;

  @OneToMany(mappedBy = "archetype")
  private List<PlayerCharacter> playerCharacters = new ArrayList<PlayerCharacter>();

}

```

Here we should pay attention to:

```java
@OneToMany(mappedBy = "archetype")
private List<PlayerCharacter> playerCharacters = new ArrayList<PlayerCharacter>();
```

This tells up that Archetype "owns" the relationship. One Team `has many` Players, so we provide a list of those players as a property of the `Archetype` class.

## Seeding Data

Now that our schema and POJO's are in place we can seed some data. Let's create a `Seeder` class that loads some data in for us.

```java
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

```

## In Summary

JPA Joins and Associations in Hibernate work exactly the same with or without Spring. Spring allows us to leverage the power of the framework and things like Repositories without requiring us to relearn the JPA.

## Why This Matters

In an object-oriented context, the idea of foreign key relationships does not fit with the abstraction. Therefore, the JPA provides us with ways to define associations among entities. In using `ManyToOne` and `OneToMany` relationships, we can create a more intuitive and object-oriented way for us to work with related data.
