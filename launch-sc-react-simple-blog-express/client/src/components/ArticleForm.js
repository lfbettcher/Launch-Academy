import React, { useState } from "react"

const ArticleForm = (props) => {
  const [newArticle, setNewArticle] = useState({ title: "", content: "" })

  const handleChange = (event) => {
    const { name, value } = event.currentTarget
    setNewArticle({ ...newArticle, [name]: value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    props.addNewArticle(newArticle)
  }

  return (
    <form className="new-article-form callout" onSubmit={handleSubmit}>
      <label>
        Article Title:
        <input name="title" id="title" type="text" onChange={handleChange} />
      </label>
      <label>
        Article Content:
        <textarea name="content" id="content" onChange={handleChange} />
      </label>

      <div className="button-group">
        <button className="button">Clear</button>
        <input className="button" type="submit" value="Submit" />
      </div>
    </form>
  )
}

export default ArticleForm
