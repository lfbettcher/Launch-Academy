import pg from "pg"
import fs from "fs"
import _ from "lodash"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:postgres@localhost:5432/movie-stars-pg"
})

class Movie {
  static async findAll() {
    return "This should query all the movies and return them."
  }

  static async findById(id) {
    return "This should query the movie with the provided id and return it."
  }
}

export default Movie
