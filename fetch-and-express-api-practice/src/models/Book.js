import fs from 'fs'
import _ from "lodash"

const booksPath = "books.json"

class Book {
  constructor({ id, name }) {
    this.id = id
    this.name = name
  }

  static findAll() {
    const bookData = JSON.parse(fs.readFileSync(booksPath)).books
    let books = []
    bookData.forEach(book => {
      const newBook = new Book(book)
      books.push(newBook)
    })
    return books
  }

  isValid() {
    this.errors = {
      name: []
    }
    if(!this.name) {
      this.errors.name.push("Can't be blank")
      return false
    } else {
      return true
    }
  }

  static getNextBookId() {
    const maxBook = _.maxBy(this.findAll(), book => book.id)
    return maxBook.id + 1
  }

  save() {
    if(this.isValid()) {
      delete this.errors
      this.id = this.constructor.getNextBookId()
      const books = this.constructor.findAll()
      books.push(this)
      const data = { books: books }
      fs.writeFileSync(booksPath, JSON.stringify(data))
      return true
    } else {
      return false
    }
  }
}

export default Book