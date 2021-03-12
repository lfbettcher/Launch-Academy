import React, { useState, useEffect } from "react"

const ArticleForm = props => {
  return (
    <form className="new-article-form callout">
      <label>
        Article Title:
        <input
          name="title"
          id="title"
          type="text"
        />
      </label>
      <label>
        Article Content:
        <textarea
          name="content"
          id="content"
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
