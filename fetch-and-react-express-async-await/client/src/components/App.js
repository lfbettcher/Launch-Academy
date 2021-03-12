import React, { useState, useEffect } from "react"
import { hot } from "react-hot-loader/root"

import "../assets/scss/main.scss"

import BookForm from './BookForm'
import ErrorList from "./ErrorList"

const App = (props) => {
  const [books, setBooks] = useState([])
  const [errors, setErrors] = useState({})

  const addBook = async (formPayload) => {
    try {
      const response = await fetch("/api/v1/books", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json"
        }),
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if (response.status === 422) {
          const body = await response.json()
          return setErrors(body.errors)
        } else {
          const errorMessage = `${response.status} (${response.statusText})`
          throw new Error(errorMessage)
        }
      }
      const body = await response.json()
      setErrors({})
      setBooks([...books, body.book])
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  const fetchBooks = async () => {
    try {
      const response = await fetch("/api/v1/books")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
      const body = await response.json()
      setBooks(body.books)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  useEffect(() => {
    fetchBooks()
  }, [])

  const bookListItems = books.map(book => {
    return <li key={book.id}>{book.title}</li>;
  })

  return (
    <div>
      <h1>Books</h1>
      <ul>
        {bookListItems}
      </ul>
      <hr />
      <ErrorList errors={errors} />
      <BookForm addBook={addBook} />
    </div>
  )
}

export default hot(App)
