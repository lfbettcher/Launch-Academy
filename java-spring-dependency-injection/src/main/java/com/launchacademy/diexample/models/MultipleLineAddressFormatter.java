package com.launchacademy.diexample.models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MultipleLineAddressFormatter implements AddressFormatter {

  @Override
  public String formatAddress(BillingAddress address) {
    String[] strings = {
        address.getAddress1(),
        address.getAddress2(),
        address.getCity() + ", " + address.getState() + " " + address.getPostalCode()};
    return String.join("\n", strings);
  }
}