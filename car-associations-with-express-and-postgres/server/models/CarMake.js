import pg from "pg";
import _ from "lodash";

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/car_associations_development",
});

class CarMake {
  constructor({ id, name }) {
    this.id = id;
    this.name = name;
  }

  async carModels() {
    const carModelFile = await import("./CarModel.js")
    const CarModel = carModelFile.default

    try {
      const client = await pool.connect()
      const queryString = "SELECT * FROM car_models WHERE car_make_id = $1"
      const result = await client.query(queryString, [this.id])

      const relatedCarModelsData = result.rows
      const relatedCarModels = relatedCarModelsData.map((carModel => new CarModel(carModel)))

      client.release()

      return relatedCarModels
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)?
    }
  }
  
  static async findAll() {
    try {
      const client = await pool.connect();
      const result = await client.query("SELECT * FROM car_makes;");

      // get the results
      const carMakeData = result.rows;
      const carMakes = carMakeData.map((carMake) => new this(carMake));

      // release the connection back to the pool
      client.release();

      return carMakes;
    } catch (err) {
      console.log(err);
      throw err;
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect();
      const query = `SELECT * FROM car_makes WHERE ID = ${id};`;
      const result = await client.query(query);

      // get the results
      const carMakeData = result.rows[0];
      const carMake = new this(carMakeData);

      // release the connection back to the pool
      client.release();

      return carMake;
    } catch (err) {
      console.log(err);
      throw err;
    }
  }
}

export default CarMake;
