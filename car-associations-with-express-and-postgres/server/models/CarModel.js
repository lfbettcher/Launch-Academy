import pg from "pg";
import _ from "lodash";

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/car_associations_development",
});

class CarModel {
  constructor({ id, name, carMakeId, car_make_id }) {
    this.id = id;
    this.name = name;
    this.carMakeId = carMakeId || car_make_id;
  }

  async carMake() {
    const carMakeFile = await import("./CarMake.js")
    const CarMake = carMakeFile.default

    try {
      const client = await pool.connect()
      const queryString = "SELECT * FROM car_makes WHERE id = $1"
      const result = await client.query(queryString, [this.carMakeId])

      const relatedCarMakeData = result.rows[0]
      const relatedCarMake = new CarMake(relatedCarMakeData)

      client.release()

      return relatedCarMake
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }

  static async findAll() {
    try {
      const client = await pool.connect();
      const result = await client.query("SELECT * FROM car_models;");

      // get the results
      const carModelData = result.rows;
      const carModels = carModelData.map((carModel) => new this(carModel));

      // release the connection back to the pool
      client.release();

      return carModels;
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect();
      const result = await client.query("SELECT * FROM car_models WHERE ID = $1", [id]);

      // get the results
      const carModelData = result.rows[0];
      const carModel = new this(carModelData);

      // release the connection back to the pool
      client.release();

      return carModel;
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }
}

export default CarModel;
