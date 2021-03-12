import React from "react"
import { Link } from "react-router-dom"

const ProjectTile = ({ project: { id, name, description } }) => {
  return (
    <div className="row">
      <div className="project small-9 small-centered columns">
        <Link to={`/projects/${id}`}>
          <h2>{name}</h2>
        </Link>
        <h3>Description: {description} </h3>
      </div>
    </div>
  )
}

export default ProjectTile
