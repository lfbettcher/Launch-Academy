import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/monolith_cats_development",
})

class Cat {
  constructor({ name, age, human, id = null }) {
    this.id = id
    this.name = name
    this.age = age
    this.human = human
  }

  static async findAll() {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM cats")

      const catsData = result.rows

      const cats = catsData.map((cat) => new this(cat))

      client.release()
      return cats
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM cats WHERE id = $1", [id])

      const cat = result.rows[0]

      client.release()
      return cat
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["name", "age"]
    let isValid = true

    for (const requiredField of requiredFields) {
      this.errors[requiredField] = []
      if (!this[requiredField]) {
        isValid = false
        this.errors[requiredField].push("Can't be blank")
      }
    }
    return isValid
  }

  async save() {
    try {
      if (this.isValid()) {
        delete this.errors
        const client = await pool.connect()
        const queryString = "INSERT INTO cats (name, age, human) VALUES ($1, $2, $3) RETURNING id"
        const values = [this.name, this.age, this.human]
        const result = await client.query(queryString, values)
        this.id = result.rows[0].id

        client.release()
        return true
      }
      return false
    } catch (error) {
      console.error(error)
      pool.end()
      return false
    }
  }
}

export default Cat
