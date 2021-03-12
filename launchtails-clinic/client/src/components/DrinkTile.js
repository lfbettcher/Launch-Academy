import React from "react"
import { Link } from "react-router-dom"

const DrinkTile = ({ id, title }) => {
  return (
    <div className="drink-tile">
      <Link to={`/drinks/${id}`}>
        <p>{title}</p>
      </Link>
      <hr />
    </div>
  )
}

export default DrinkTile
