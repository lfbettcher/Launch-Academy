import React from "react"
import { Link } from "react-router-dom"
import _ from "lodash"

const TypeTile = props => {
  const { type, description, imgUrl } = props.petType
  return (
    <div className="cell">
      <div className="card">
        <h1><Link to={`/pets/${type}`}>{_.capitalize(type)}s</Link></h1>
        <Link to={`/pets/${type}`}>
          <img className="images thumbnail" src={imgUrl} />
        </Link>
        <div className="card-section">
          {description}
        </div>
        <br />
      </div>
    </div>
  )
}

export default TypeTile
