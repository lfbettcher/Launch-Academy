import React, { useState, useEffect } from "react"
import { Link } from "react-router-dom"

const ArticleShow = (props) => {
  const [article, setArticle] = useState({ title: "", content: "" })

  const fetchArticle = async () => {
    let id = props.match.params.id
    try {
      const response = await fetch(`/api/v1/articles/${id}`)
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
      const body = await response.json()
      setArticle(body.article)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  useEffect(() => {
    fetchArticle()
  }, [])

  return (
    <div className="article-show grid-container">
      <h2>{article.title}</h2>
      <p>{article.content}</p>
      <Link to="/">Back to Articles List</Link>
    </div>
  )
}

export default ArticleShow
