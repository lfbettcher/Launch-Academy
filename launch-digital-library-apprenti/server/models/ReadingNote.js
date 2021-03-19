import pg from "pg";
import _ from "lodash";

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/launch_digital_library_development",
});

class ReadingNote {
  constructor({ id=null, title, body, bookId, book_id, date=null }) {
    this.id = id;
    this.title = title;
    this.body = body
    this.bookId = bookId || book_id
    this.date = date
  }

  async book () {
    const bookFile = await import("./Book.js")
    const Book = bookFile.default

    try {
      const client = await pool.connect()
      const queryString = "SELECT * FROM books WHERE id = $1"
      const result = await client.query(queryString, [this.bookId])

      const relatedBookData = result.rows[0]
      const relatedBook = new Book(relatedBookData)

      client.release()

      return relatedBook
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }

  static async findAll() {
    try {
      const client = await pool.connect();
      const result = await client.query("SELECT * FROM reading_notes;");

      // get the results
      const readingNotesData = result.rows;
      const readingNotes = readingNotesData.map((readingNote) => new this(readingNote));

      // release the connection back to the pool
      client.release();

      return readingNotes;
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }

  static async findById(id) {
    try {
      const client = await pool.connect();
      const result = await client.query("SELECT * FROM reading_notes WHERE ID = $1", [id]);

      // get the results
      const readingNoteData = result.rows[0];
      const readingNote = new this(readingNoteData);

      // release the connection back to the pool
      client.release();

      return readingNote;
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)
    }
  }

  async save() {
    try {
      const client = await pool.connect()
      const queryString = "INSERT INTO reading_notes (title, body, book_id) VALUES ($1, $2, $3) RETURNING id, date";
      const values = [this.title, this.body, this.bookId];
      const result = await client.query(queryString, values)
      console.log("result", result)
      this.id = result.rows[0].id
      this.date = result.rows[0].date
      client.release()
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }
}

export default ReadingNote;
