package com.launchacademy.diexample.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SingleLineAddressFormatter implements AddressFormatter {

  @Override
  public String formatAddress(BillingAddress address) {
    String[] strings = {
        address.getAddress1(),
        address.getAddress2(),
        address.getCity(),
        address.getState(),
        address.getPostalCode()
    };
    return String.join(",", strings);
  }
}