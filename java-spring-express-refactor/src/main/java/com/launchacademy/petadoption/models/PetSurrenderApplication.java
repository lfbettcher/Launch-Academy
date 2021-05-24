package com.launchacademy.petadoption.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "pet_surrender_applications")
@Getter
@Setter
@NoArgsConstructor
public class PetSurrenderApplication {

  @Id
  @SequenceGenerator(name = "pet_surrender_application_generator", sequenceName = "pet_surrender_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_surrender_application_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @NotBlank
  @Email
  @Column(name = "email", nullable = false)
  private String email;

  @NotBlank
  @Column(name = "pet_name", nullable = false)
  private String petName;

  @Column(name = "pet_age")
  private Integer petAge;

  @NotBlank
  @URL
  @Column(name = "pet_image_url", nullable = false)
  private String petImageUrl;

  @Column(name = "vaccination_status")
  private Boolean vaccinationStatus;

  @NotBlank
  @Column(name = "application_status", nullable = false)
  private String applicationStatus;

  @ManyToOne
  @JoinColumn(name = "pet_type_id")
  @JsonIgnoreProperties("petSurrenderApplications")
  private PetType petType;
}