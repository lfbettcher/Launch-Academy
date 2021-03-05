import React from "react";

import PerformanceList from "./PerformanceList";
import EmailForm from "./EmailForm";

const Popup = (props) => {
  const handleExit = () => {
    confirm("Are you sure you don't want to see Tay Sway?");
  };

  return (
    <div className="text-center" id="ad">
      <div className="row yellow-text">
        <div className="small-10 small-push-1 columns">
          <h3>You have won tickets to see Taylor Swift!!</h3>
          <p>Please enter your email so we can send you the tickets</p>
        </div>
        <div className="small-1 columns">
          <i className="fas fa-times" onClick={handleExit} />
        </div>
      </div>
      <div className="row">
        <div className="small-8 small-centered columns">
          <EmailForm />
        </div>
      </div>
      <div className="row">
        <div className="small-10 small-centered columns">
          <PerformanceList />
        </div>
      </div>
    </div>
  );
};

export default Popup;
