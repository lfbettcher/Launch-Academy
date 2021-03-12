import React, { useState } from "react"
import Form from "./Form"

const CustomGreeting = (props) => {
  const [greetings, setGreetings] = useState([])

  const saveGreeting = () => {
    setGreetings([...greetings, { name: name, type: type }])
    setName("")
  }

  const [name, setName] = useState("")
  const handleName = event => {
    setName(event.target.value)
  }

  const [type, setType] = useState("greeting")
  const handleType = event => {
    setType(event.target.value)
  }

  const message = (type) => type === "greeting" ? "Hello my friend " : "Goodbye my friend "

  return (
    <div>
      <h1>Custom Greeting Generator</h1>
      <Form handleType={handleType} handleName={handleName} name={name} />
      <p>
        {message(type)}
        {name}
      </p>
      <button onClick={saveGreeting}>Save</button>
      <hr />
      {greetings.map(greeting => {
        return (
          <p key={greeting.name}>
            {message(greeting.type)}
            {greeting.name}  
          </p>
        )
      })}
    </div>
  )
}

export default CustomGreeting
