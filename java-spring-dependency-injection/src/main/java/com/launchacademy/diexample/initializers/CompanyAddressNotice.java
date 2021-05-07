package com.launchacademy.diexample.initializers;

import com.launchacademy.diexample.models.AddressFormatter;
import com.launchacademy.diexample.models.BillingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CompanyAddressNotice implements CommandLineRunner {

  // third option is to autowire the field, which is not ideal
  // it heavily couples your code to Spring's implementation of
  // dependency injection, whereas the original two do not infer
  // implementation details.
  // Autowiring via private field should be avoided where possible.
  // @Autowired
  private AddressFormatter formatter;

  /*
  @Autowired
  public CompanyAddressNotice(SingleLineAddressFormatter formatter) {
    this.formatter = formatter;
  }*/

  // alternative way is to autowire a setter instead of a constructor
  // This is the preferred method of autowiring if we are only dealing
  // with one or a few components that we wish to autowire.
  @Autowired
  public void setFormatter(AddressFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void run(String... args) throws Exception {
    BillingAddress companyAddress = new BillingAddress();
    companyAddress.setAddress1("77 Summer Street");
    companyAddress.setAddress2("7th Floor");
    companyAddress.setCity("Boston");
    companyAddress.setState("MA");
    companyAddress.setPostalCode("02110");

    System.out.println("The Company Address is: \n" + formatter.formatAddress(companyAddress));
  }
}