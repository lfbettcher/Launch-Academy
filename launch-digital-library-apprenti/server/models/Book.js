import query from "../db/query.js";

class Book {
  constructor({ title, author, pageCount, page_count, description, fiction, id = null }) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.pageCount = pageCount || page_count;
    this.description = description;
    this.fiction = fiction;
  }

  async readingNotes() {
    const readingNoteFile = await import("./ReadingNote.js")
    const ReadingNote = readingNoteFile.default

    try {
      const client = await pool.connect()
      const queryString = "SELECT * FROM reading_notes WHERE book_id = $1"
      const result = await client.query(queryString, [this.id])

      const relatedReadingNotesData = result.rows
      const relatedReadingNotes = relatedReadingNotesData.map((readingNote => new ReadingNote(readingNote)))

      client.release()

      return relatedReadingNotes
    } catch (err) {
      console.error(err)
      pool.end()
      // throw(err)?
    }
  }

  static async findAll() {
    const queryData = { queryString: "SELECT * FROM books" };
    const bookData = await query(queryData);
    return bookData.map((book) => new this(book));
  }

  static async findById(id) {
    const queryData = { queryString: "SELECT * FROM books WHERE id = $1", values: [id] };
    const bookData = await query(queryData);
    return new this(bookData[0]);
  }

  async save() {
    const insertString =
      "INSERT INTO books (title, author, page_count, description, fiction) VALUES ($1, $2, $3, $4, $5) RETURNING id";
    const insertValues = [this.title, this.author, this.pageCount, this.description, this.fiction];

    const queryData = { queryString: insertString, values: insertValues };
    const result = await query(queryData);
    this.id = result[0].id;
  }
}

export default Book;
