import React, { useState, useEffect } from "react"
import { Redirect } from "react-router-dom";

import _ from "lodash"

import ErrorList from "./ErrorList"

const DrinkFormContainer = (props) => {
  const [shouldRedirect, setShouldRedirect] = useState(false)
  const [newDrink, setNewDrink] = useState({title: "", content: ""})
  const [errors, setErrors] = useState({})

  const validFormSubmission = () => {
    let submitErrors = {}
    const requiredFields = ["title", "content"]
    requiredFields.forEach((field) => {
      if (newDrink[field].trim() === "") {
        submitErrors = { ...submitErrors, [field]: "is blank" }
      }
    })
    setErrors(submitErrors)
    return _.isEmpty(submitErrors)
  }

  const addNewDrink = async (formPayload) => {
    try {
      const response = await fetch("/api/v1/drinks", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json",
        }),
        body: JSON.stringify(formPayload),
      });
      if (!response.ok) {
        if (response.status === 422) {
          const body = await response.json();
          return setErrors(body.errors);
        }
        const errorMessage = `${response.status} (${response.statusText})`;
        throw new Error(errorMessage);
      }
      const body = await response.json();
      setErrors({});
      // setDrinks([...drinks, body.drinks]);
      setShouldRedirect(true);
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`);
    }
  };

  if (shouldRedirect) {
    return <Redirect to="/" />;
  }

  const handleInputChange = (event) => {
    const { name, value } = event.currentTarget
    setNewDrink({...newDrink, [name]: value})
  }

  const clearForm = (event) => {
    event.preventDefault()
    setNewDrink({ title: "", content: "" })
    setErrors({})
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    if (validFormSubmission()) {
      addNewDrink(newDrink)
      clearForm(event)
    } 
  }

  return (
    <form className="new-drink-form callout primary" onSubmit={handleSubmit}>
      <ErrorList errors={errors} />
      <label>
        Drink Title:
        <input
          name="title"
          id="title"
          type="text"
          value={newDrink.title}
          onChange={handleInputChange}
        />
      </label>
      <label>
        Description:
        <textarea
          name="content"
          id="content"
          onChange={handleInputChange}
          value={newDrink.content}
        ></textarea>
      </label>

      <div className="button-group">
        <button className="button" onClick={clearForm}>Clear</button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  )
}

export default DrinkFormContainer
