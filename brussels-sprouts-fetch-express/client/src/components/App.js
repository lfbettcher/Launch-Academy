import React, { useState } from 'react'
import { hot } from "react-hot-loader/root"
import LongestRecipe from './LongestRecipe';

import RandomSprout from './RandomSprout'
import SproutsIndex from './SproutsIndex'

const App = () => {
  const [recipe, setRecipe] = useState("")
  const [recipes, setRecipes] = useState([])
  const [longestRecipe, setLongestRecipe] = useState("")

  const clearAll = () => {
    setRecipe("")
    setRecipes([])
    setLongestRecipe("")
  }

  const getLongestRecipe = async () => {
    try {
      const response = await fetch("/api/v1/recipes/longest")
      if (!response.ok) {
        throw new Error(`${response.status} (${response.statusText})`)
      }
      const body = await response.json()
      setLongestRecipe(body.recipe)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  const getRandomRecipe = async () => {
    try {
      const response = await fetch("/api/v1/recipes/random")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
      const body = await response.json()
      setRecipe(body.recipe)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  const getAllRecipes = async () => {
  try {
    const response = await fetch("/api/v1/recipes")
    if (!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      throw new Error(errorMessage)
    }
    const body = await response.json()
    setRecipes(body.recipes)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  const handleRandomClick = () => {
    clearAll()
    getRandomRecipe()
  }

  const handleIndexClick = () => {
    clearAll()
    getAllRecipes()
  }

  const handleLongestClick = () => {
    clearAll()
    getLongestRecipe()
  }

  return(
    <div className="container">
      <h1>Sprout Fetcher</h1>
      <RandomSprout
        recipe={recipe}
      />
      <SproutsIndex
        recipes={recipes}
      />
      <LongestRecipe
        recipe={longestRecipe}
      />
      <div className="buttons">
        <button onClick={handleRandomClick} className="btn">Get Random Recipe</button>
        <button onClick={handleIndexClick} className="btn">See All Recipes</button>
        <button onClick={handleLongestClick} className="btn">See Longest Recipe</button>
      </div>
    </div>
  )
}

export default hot(App)
