import React from "react";

const Grocery = (props) => {
  const { name } = props;
  return (
    <li>
      {name}
      <button type="button" onClick={ (event) => { alert('Button was clicked')} }>Delete</button>
    </li>
  );
};

export default Grocery;
