import pg from "pg";

const pool = new pg.Pool({
  connectionString:
    "postgres://postgres:password@localhost:5432/launch_digital_library_development",
});

class Seeder {
  static async seed() {
    try {
      const insertString =
        "INSERT INTO books (title, author, page_count, description, fiction) VALUES ($1, $2, $3, $4, $5)";
      await pool.query(insertString, ["Title 1", "Author 1", 111, "Description 1", true]);
      await pool.query(insertString, ["Title 2", "Author 2", 222, "Description 2", true]);
      await pool.query("INSERT INTO books (title, author, page_count) VALUES ($1, $2, $3)", ["Title 3", "Author 3", 333]);

      const result = await pool.query("SELECT * FROM books;");
      console.log(result.rows);
      pool.end();
    } catch (error) {
      pool.end();
    }
  }
}

export default Seeder;
