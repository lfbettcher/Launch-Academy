import React, { useState } from "react"
import _ from "lodash"

import ErrorList from "./ErrorList"

const ArticleForm = (props) => {
  const [newArticle, setNewArticle] = useState({ title: "", content: "" })
  const [errors, setErrors] = useState({})

  const validFormSubmission = () => {
    let submitErrors = {}
    const requiredFields = ["title", "content"]
    requiredFields.forEach((field) => {
      if (newArticle[field].trim() === "") {
        submitErrors = { ...submitErrors, [field]: "is blank" }
      }
    })
    setErrors(submitErrors)
    return _.isEmpty(submitErrors)
  }

  const handleChange = (event) => {
    const { name, value } = event.currentTarget
    setNewArticle({ ...newArticle, [name]: value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    if (validFormSubmission()) {
      props.addNewArticle(newArticle)
      clearForm(event)
    }
  }

  const clearForm = (event) => {
    event.preventDefault()
    setNewArticle({ title: "", content: "" })
    setErrors({})
  }

  return (
    <form className="new-article-form callout" onSubmit={handleSubmit}>
      <ErrorList errors={errors} />
      <label>
        Article Title:
        <input
          name="title"
          id="title"
          type="text"
          value={newArticle.title}
          onChange={handleChange}
        />
      </label>
      <label>
        Article Content:
        <textarea name="content" id="content" value={newArticle.content} onChange={handleChange} />
      </label>

      <div className="button-group">
        <button className="button" onClick={clearForm}>
          Clear
        </button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  )
}

export default ArticleForm
