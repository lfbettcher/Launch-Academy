import React, { useState, useEffect } from "react"

const LauncherShow = (props) => {
  const [launcher, setLauncher] = useState({})

  const fetchLauncher = async () => {
    let id = props.match.params.id
    try {
      const response = await fetch(`/api/v1/launchers/${id}`)
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
      const body = await response.json()
      setLauncher(body.launcher)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  useEffect(() => {
    fetchLauncher()
  }, [])

  return (
    <div className="card">
      <h1>{launcher.name}</h1>
      <p>{launcher.bio}</p>
    </div>
  )
}

export default LauncherShow
