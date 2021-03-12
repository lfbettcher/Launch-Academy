import React, { useState } from "react";

import AddressForm from "./AddressForm";

const App = (props) => {
  const [formInputs, setFormInputs] = useState([]);

  const formSubmittedHandler = (formInput) => {
    setFormInputs([...formInputs, formInput]);
  };

  return (
    <div className="grid-container">
      <div className="medium-6 medium-offset-3 small-12">
        <AddressForm onFormSubmitted={formSubmittedHandler} />
      </div>
    </div>
  );
};

export default App;
