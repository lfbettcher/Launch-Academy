import React, { useState, useEffect } from 'react'
import { hot } from "react-hot-loader/root"

import "../assets/scss/main.scss"

import DirectionsList from "./DirectionsList"
import SuppliesList from "./SuppliesList"
import FetchButton from "./FetchButton"

const App = (props) => {
  // const supplies = props.data.supplies
  // const directions = props.data.directions
  const [activity, setActivity] = useState("")
  const [supplies, setSupplies] = useState([])
  const [directions, setDirections] = useState([])

  const fetchFavoriteThings = async () => {
    try {
      const response = await fetch("http://localhost:3000/api/v1/favorite-things")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw error
      }
      const responseBody = await response.json()
      return responseBody
    } catch (err) {
      console.error(err)
    }
  }
  
  const getFavoriteThings = async () => {
    const favoriteThings = await fetchFavoriteThings()
    setActivity(favoriteThings.activity)
    setSupplies(favoriteThings.supplies)
    setDirections(favoriteThings.directions)
  }

  return (
    <>
      <h1>How To {activity}</h1>

      <SuppliesList
        supplies={supplies}
      />

      <DirectionsList
        directions={directions}
      />

      <FetchButton onClick={getFavoriteThings} />
    </>
  )
}

export default hot(App)
