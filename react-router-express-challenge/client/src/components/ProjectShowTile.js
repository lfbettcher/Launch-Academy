import React from "react"

const ProjectShowTile = ({ project: { name, description } }) => {
  return (
    <div className="project-show">
      <h2 className="text-center">{name}</h2>
      <p className="text-center">Description: {description}</p>
    </div>
  )
}

export default ProjectShowTile
