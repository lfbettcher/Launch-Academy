import React from 'react';

const Form = (props) => {
  return(
    <form>
      <input type="text" onChange={props.handleName} value={props.name} />
      <select onChange={props.handleType}>
        <option value="greeting">Greeting</option>
        <option value="goodbye">Goodbye</option>
      </select>
    </form>
  )
}

export default Form;
