import _ from "lodash"
import React, { useState } from "react"
import { Redirect } from "react-router-dom"

import Error from "./Error"

const SurrenderForm = props => {
  const [newSurrender, setNewSurrender] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    petType: "",
    petImageUrl: "",
    vaccinationStatus: "",
    applicationStatus: "pending"
  })
  const [errors, setErrors] = useState([])
  const [redirect, setRedirect] = useState(false)

  const addNewSurrender = async () => {
    try {
      const response = await fetch("/api/v1/surrender", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json"
        }),
        body: JSON.stringify(newSurrender)
      })
      if (!response.ok) {
        if (response.status === 422) {
          const data = await response.json()
          return setErrors(data.errors)
        } else {
          const errorMessage = `${response.status} (${response.statusText})`
          const error = new Error(errorMessage)
          throw error
        }
      } else {
        const data = await response.json()
        if (data) {
          setRedirect(true)
        }
      }
    } catch (error) {
      console.error(`Error in fetch: ${error}`)
    }
  }

  const handleInput = event => {
    const { name, value } = event.currentTarget;
    setNewSurrender({ ...newSurrender, [name]: value })
  }
  const validateInput = () => {
    let submissionErrors = {}
    const requiredFields = [
      "name",
      "phoneNumber",
      "email",
      "petName",
      "petAge",
      "petType",
      "petImageUrl",
      "vaccinationStatus"
    ]
    requiredFields.forEach(field => {
      if (!newSurrender[field] || newSurrender[field].trim() === "") {
        submissionErrors = { ...submissionErrors, [field]: `is required` }
      }
    })
    setErrors(submissionErrors)
    return _.isEmpty(submissionErrors)
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (validateInput()) {
      addNewSurrender()
    }
  }

  if (redirect) {
    return <Redirect to="/adoptions" />
  }

  return (
    <div>
      <h2>Surrender Your Pet:</h2>
      <Error errors={errors} />
      <form onSubmit={handleSubmit} className="callout">
        <div className="grid-container grid-margin-x">
          <div className="grid-x grid-padding-x">

            <div className="small-4 cell">
              <label htmlFor="name">
                Your Name:
                <input
                  id="name"
                  type="text"
                  name="name"
                  onChange={handleInput}
                  value={newSurrender.name}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="phoneNumber">
                Phone:
                <input
                  id="phoneNumber"
                  type="text"
                  name="phoneNumber"
                  onChange={handleInput}
                  value={newSurrender.phoneNumber}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="email">
                Email:
                <input
                  id="email"
                  type="email"
                  name="email"
                  onChange={handleInput}
                  value={newSurrender.email}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="petName">
                Pets Name:
                <input
                  id="petName"
                  type="text"
                  name="petName"
                  onChange={handleInput}
                  value={newSurrender.petName}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="petImageUrl">
                Picture of your pet:
                <input
                  id="petImageUrl"
                  type="url"
                  name="petImageUrl"
                  onChange={handleInput}
                  value={newSurrender.petImageUrl}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="petAge">
                Pets Age (in months):
                <input
                  id="petAge"
                  type="number"
                  name="petAge"
                  onChange={handleInput}
                  value={newSurrender.petAge}
                />
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="petType">
                Pet Type:
                <select
                  id="petType"
                  name="petType"
                  value={newSurrender.petType}
                  onChange={handleInput}
                >
                  <option value="">Please Select</option>
                  <option value="cat">Cat</option>
                  <option value="dog">Dog</option>
                  <option value="bear">Bear</option>
                </select>
              </label>
            </div>

            <div className="small-4 cell">
              <label htmlFor="vaccinationStatus">
                Vaccination Status
                <div>
                  <input
                    type="radio"
                    id="vaccinationStatusTrue"
                    name="vaccinationStatus"
                    onChange={handleInput}
                    value="true"
                  />
                  <label htmlFor="vaccinationStatusTrue">Yes</label>

                  <input
                    type="radio"
                    id="vaccinationStatusFalse"
                    name="vaccinationStatus"
                    onChange={handleInput}
                    value="false"
                  />
                  <label htmlFor="vaccinationStatusFalse">No</label>
                </div>
              </label>
            </div>

            <div className="small-4 cell">
              <input className="button" type="submit" value="Submit" />
            </div>
          </div>
        </div>
      </form>
    </div>
  )
}
export default SurrenderForm
