import React from "react";

const EmailForm = (props) => {
  const handleSubmit = (event) => {
    console.log("Form Submitted");
    console.log("email:", event.target.elements[0].value);
    event.preventDefault();
  };

  return (
    <div className="row">
      <form onSubmit={handleSubmit}>
        <div className="small-9 columns">
          <label htmlFor="email">
            <input type="email" name="email" id="email" placeholder="Your email here" />
          </label>
        </div>
        <div className="small-3 columns">
          <input type="submit" className="button" id="prize-btn" value="CLAIM YOUR PRIZE!" />
        </div>
      </form>
    </div>
  );
};

export default EmailForm;
