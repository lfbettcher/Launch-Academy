package com.launchacademy.diexample.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingAddress {

  private String address1;
  private String address2;
  private String city;
  private String state;
  private String postalCode;
}
