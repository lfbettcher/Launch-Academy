import React from "react";
import NameForm from "./NameForm";
import KarmaButton from "./KarmaButton";

const App = (props) => {
  const karmaAlert = (event) => {
    alert("You have leveled up! (Karma: 1)");
  };

  const formChanger = (event) => {
    event.preventDefault()
    console.log("Form submitted!");
  };

  return (
    <div className="main-div">
      <NameForm formChangerProp={formChanger} />
      <KarmaButton karmaAlertProp={karmaAlert} />
    </div>
  );
};

export default App;
