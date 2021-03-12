import React, { useState, useEffect } from "react"

import ArticleTile from "./ArticleTile"
import ArticleForm from "./ArticleForm"

const ArticlesList = props => {
  const [articles, setArticles] = useState([])

  // Fetch all articles

  const addNewArticle = async formPayload => {
    // FETCH POST LOGIC
  }

  const articleTiles = articles.map(article => {
    return (
      <ArticleTile
        key={article.id}
        id={article.id}
        title={article.title}
        content={article.content}
      />
    )
  })

  return (
    <div className="row">
      <div className="small-8 small-centered columns">
        <h1>My Blog!</h1>
        <hr />
        {articleTiles}
        <ArticleForm addNewArticle={addNewArticle} />
      </div>
    </div>
  )
}

export default ArticlesList
