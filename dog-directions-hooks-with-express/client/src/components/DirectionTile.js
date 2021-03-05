import React from "react"

const DirectionTile = props => {
  const { step, setSelectedDirection, className } = props
  return <li onClick={setSelectedDirection} className={className}>{step}</li>
}

export default DirectionTile
