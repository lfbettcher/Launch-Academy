import React, { useEffect, useState } from "react"

import ProjectTile from "./ProjectTile"

const ProjectsIndex = props => {
  const [projects, setProjects] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch("/api/v1/projects")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setProjects(responseData.projects)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const projectTiles = projects.map(project => {
    return <ProjectTile key={project.id} project={project} />
  })
  return (
    <div>
      <h2 className="text-center">My Projects</h2>
      {projectTiles}
    </div>
  )
}

export default ProjectsIndex
