import React, { useState, useEffect } from "react"

import CerealTile from "./CerealTile"

const CerealsIndexPage = props => {
  const [cereals, setCereals] = useState([])

  const getCereals = async () => {
    try {
      const response = await fetch("/api/v1/cereals")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw error
      }
      const cereals = await response.json()
      setCereals(cereals)
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    getCereals()
  }, [])

  const cerealTiles = cereals.map(cereal => {
    return <CerealTile key={cereal.id} cereal={cereal} />
  })

  return (
    <div className="center-bg">
      <h3 className="so-great">Cereals Are Great</h3>
      <a href="/milks">So are milks!</a>
      {cerealTiles}
    </div>
  )
}

export default CerealsIndexPage
