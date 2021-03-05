import React from "react";

const PillButton = () => {
  const alertPills = (event) => {
    event.preventDefault();
    alert(
      "Take one dose red pill, two doses of the purple pills. Take one half of your vitamin D supplement (don't take the whole thing!)"
    );
  };

  return (
    <div className="centered">
      <button className="pill-button" type="button" onClick={alertPills}>
        Stop Being a Bitch
      </button>
    </div>
  );
};

export default PillButton;
