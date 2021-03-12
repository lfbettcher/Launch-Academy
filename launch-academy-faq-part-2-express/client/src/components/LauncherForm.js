import React, { useState } from "react"
import _ from "lodash"

import ErrorList from "./ErrorList"

const FAQForm = ({ addQuestion }) => {
  const [formRecord, setFormRecord] = useState({ question: "", answer: "" })
  const [errors, setErrors] = useState({})

  const validFormSubmission = () => {
    let submitErrors = {}
    const requiredFields = ["question", "answer"]
    requiredFields.forEach((field) => {
      if (formRecord[field].trim() === "") {
        submitErrors = { ...submitErrors, [field]: "is blank" }
      }
    })
    setErrors(submitErrors)
    return _.isEmpty(submitErrors)
  }

  const handleInputChange = (event) => {
    const { value, name } = event.currentTarget
    setFormRecord({ ...formRecord, [name]: value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    if (validFormSubmission()) {
      addQuestion(formRecord)
      clearForm(event)
    }
  }

  const clearForm = (event) => {
    event.preventDefault()
    setFormRecord({ question: "", answer: "" })
    setErrors({})
  }

  return (
    <form className="callout" onSubmit={handleSubmit}>
      <ErrorList errors={errors} />
      <label htmlFor="question">Question: </label>
      <input
        type="text"
        id="question"
        name="question"
        onChange={handleInputChange}
        value={formRecord.question}
      />
      <label htmlFor="answer">Answer: </label>
      <input id="answer" name="answer" onChange={handleInputChange} value={formRecord.answer} />
      <div className="button-group">
        <button className="button" onClick={clearForm}>
          Clear
        </button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  )
}

export default FAQForm
