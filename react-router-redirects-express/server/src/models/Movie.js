import fs from "fs"
import _ from "lodash"

const moviesPath = "movies.json"

class Movie {
  constructor({ id, title }) {
    this.id = id
    this.title = title
  }

  static findAll() {
    const movieData = JSON.parse(fs.readFileSync(moviesPath)).movies
    const movies = movieData.map(movie => new Movie(movie))
    return movies
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["title"]
    let isValid = true

    for(const requiredField of requiredFields) {
      this.errors[requiredField] = []
      if(!this[requiredField]) {
        isValid = false
        this.errors[requiredField].push("Can't be blank")
      }
    }
    return isValid
  }

  static getNextMovieId() {
    const maxMovie = _.maxBy(this.findAll(), Movie => Movie.id)
    return maxMovie.id + 1
  }

  save() {
    if(this.isValid()) {
      delete this.errors
      this.id = this.constructor.getNextMovieId()
      const movies = this.constructor.findAll()
      movies.push(this)
      const data = { movies: movies }
      fs.writeFileSync(moviesPath, JSON.stringify(data))
      return true
    } else {
      return false
    }
  }
}

export default Movie