import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/monolith_adventures_development",
})

class Adventure {
  constructor({ title, location, id = null }) {
    this.id = id
    this.title = title
    this.location = location
  }

  static async findAll() {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM adventures")

      const adventuresData = result.rows

      const adventures = adventuresData.map((adventure) => new this(adventure))

      client.release()
      return adventures
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM adventures WHERE id = $1", [id])

      const adventure = result.rows[0]

      client.release()
      return adventure
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["title", "location"]
    let isValid = true
    requiredFields.forEach((field) => {
      if (!this[field] || this[field].trim() === "") {
        isValid = false
        this.errors[field] = ["Can't be blank"]
      }
    })
    return isValid
  }

  async save() {
    try {
      if (this.isValid()) {
        delete this.errors

        const client = await pool.connect()
        const queryString = "INSERT INTO adventures (title, location) VALUES ($1, $2) RETURNING id"
        const values = [this.title, this.location]
        const result = await client.query(queryString, values)
        this.id = result.rows[0].id

        client.release()
        return true
      } else {
        return false
      }
    } catch (error) {
      console.error(error)
      pool.end()
      return false
    }
  }
}

export default Adventure
