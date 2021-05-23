package com.launchacademy.petadoption.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pet_types")
@Getter
@Setter
public class PetType {

  @Id
  @SequenceGenerator(name = "pet_type_generator", sequenceName = "pet_types_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_type_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "description")
  private String description;

  @Column(name = "img_url", nullable = false)
  private String imgUrl;

  @JsonIgnore
  @OneToMany(mappedBy = "petType")
  private List<AdoptablePet> adoptablePets = new ArrayList<AdoptablePet>();

  public PetType() {
  }

  public PetType(String type, String description, String imgUrl) {
    this.type = type;
    this.description = description;
    this.imgUrl = imgUrl;
  }
}
