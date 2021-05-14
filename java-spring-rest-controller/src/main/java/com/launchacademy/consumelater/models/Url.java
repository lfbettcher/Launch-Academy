package com.launchacademy.consumelater.models;

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
@Table(name="urls")
@Getter
@Setter
@NoArgsConstructor
public class Url {
  @Id
  @SequenceGenerator(name="url_generator",
      sequenceName="urls_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE,
      generator="url_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @Column(name="url", nullable=false)
  private String url;

  @Column(name="description")
  private String description;

  @Column(name="media_type")
  private String mediaType;
}
