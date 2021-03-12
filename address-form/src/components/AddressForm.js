import React, { useState } from "react";

const AddressForm = (props) => {
  const [formRecord, setFormRecord] = useState({
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    state: "",
    zipCode: "",
    phoneNumber: "",
    email: "",
  });

  const handleInputChange = (event) => {
    setFormRecord({
      ...formRecord,
      [event.currentTarget.id]: event.currentTarget.value,
    });
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();
    props.onFormSubmitted(formRecord);
    console.log(formRecord);
  };

  return (
    <form className="callout secondary" id="shipping-address-form" onSubmit={onSubmitHandler}>
      <h1>Shipping Address</h1>
      <div>
        <label htmlFor="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" onChange={handleInputChange} />
      </div>

      <div>
        <label htmlFor="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" onChange={handleInputChange} />
      </div>

      <label htmlFor="address">Address:</label>
      <input type="text" id="address" name="address" onChange={handleInputChange} />

      <div>
        <label htmlFor="city">City:</label>
        <input type="text" id="city" name="city" onChange={handleInputChange} />
      </div>

      <div>
        <label htmlFor="state">State:</label>
        <input type="text" id="state" name="state" onChange={handleInputChange} />
      </div>

      <div>
        <label htmlFor="zipCode">Zip Code:</label>
        <input type="text" id="zipCode" name="zipCode" onChange={handleInputChange} />
      </div>

      <div>
        <label htmlFor="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" onChange={handleInputChange} />
      </div>

      <div>
        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" onChange={handleInputChange} />
      </div>

      <input type="submit" className="button" value="Submit " />
    </form>
  );
};

export default AddressForm;
