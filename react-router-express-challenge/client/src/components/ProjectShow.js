import React, { useState, useEffect } from "react"

import ProjectShowTile from "./ProjectShowTile"

const ProjectShow = props => {
  const [project, setProject] = useState({})
  const projectId = props.match.params.id

  const fetchData = async () => {
    try {
      const response = await fetch(`/api/v1/projects/${projectId}`)
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const responseData = await response.json()
      setProject(responseData.project)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  return (
    <div>
      <ProjectShowTile key={projectId} project={project} />
    </div>
  )
}

export default ProjectShow
