import React, { useState, useEffect } from "react"

const ArticleForm = (props) => {
  const [formRecord, setFormRecord] = useState({ title: "", content: "" })

  const handleInputChange = (event) => {
    const { name, value } = event.currentTarget
    setFormRecord({ ...formRecord, [name]: value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    prop.addNewArticle(formRecord)
  }

  return (
    <form className="new-article-form callout" onSubmit={handleSubmit}>
      <label htmlFor="title">
        Article Title:
        <input
          name="title"
          id="title"
          type="text"
          value={formRecord.title}
          onChange={handleInputChange}
        />
      </label>
      <label htmlFor="content">
        Article Content:
        <textarea
          name="content"
          id="content"
          value={formRecord.content}
          onChange={handleInputChange}
        />
      </label>

      <div className="button-group">
        <button className="button">Clear</button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  )
}

export default ArticleForm
