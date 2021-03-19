import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/movie-stars-pg",
})

class Movie {
  static async findAll() {
    try {
      const client = await pool.connect()
      const result = await pool.query("SELECT * FROM movies")
      client.release()
      return result.rows
    } catch (err) {
      console.error(`Error: ${err.message}`)
      pool.end()
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect()
      const response = await pool.query("SELECT * FROM movies WHERE id = $1", [id])
      client.release()
      return response.rows[0]
    } catch (err) {
      console.error(`Error: ${err.message}`)
      pool.end()
    }
  }
}

export default Movie
