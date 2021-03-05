import React from "react"

const FetchButton = props => {
  const { onClick } = props
  return <button onClick={onClick}>Get Favorite Thing</button>
}

export default FetchButton
