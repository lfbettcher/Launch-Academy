import React from "react"
import { Link } from "react-router-dom"

const ArticleTile = props => {
  return (
    <div className="article-tile">
      <Link to={`/articles/${props.id}`}>
        <p>{props.title}</p>
      </Link>
      <hr />
    </div>
  )
}

export default ArticleTile
