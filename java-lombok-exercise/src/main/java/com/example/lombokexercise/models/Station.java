package com.example.lombokexercise.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Station {

  @ToString.Exclude
  private String name;
  private String location;
}
