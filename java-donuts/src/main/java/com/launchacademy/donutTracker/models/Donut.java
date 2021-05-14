package com.launchacademy.donutTracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "donuts")
@NoArgsConstructor
@Getter
@Setter
public class Donut {
  @Id
  @SequenceGenerator(name="donut_generator", sequenceName = "donuts_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "donut_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @Column(name="type", nullable = false)
  private String type;

  @Column(name="description")
  private String description;

  @Column(name="vegan", nullable = false)
  private Boolean vegan;
}
