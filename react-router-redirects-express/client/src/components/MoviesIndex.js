import React, { useState, useEffect } from "react"
import { Link } from "react-router-dom"
import MovieTile from "./MovieTile"

const MoviesIndex = props => {
  const [movies, setMovies] = useState([])

  const fetchData = async () => {
    try {
      const response = await fetch("/api/v1/movies")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const body = await response.json()
      setMovies(body.movies)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }
  
  useEffect(() => {
    fetchData()
  },[])

  const movieTiles = movies.map(movie => {
    return <MovieTile title={movie.title} key={movie.id} />
  })

  return (
    <div>
      <h1>My Favorite Movies</h1>
      {movieTiles}
      <Link to="/movies/new">Add a Movie</Link>
    </div>
  )
}

export default MoviesIndex
