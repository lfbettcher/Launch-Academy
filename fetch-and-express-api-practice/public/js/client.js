const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    if(!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      const error = new Error(errorMessage)
      throw(error)
    }
    const responseBody = await response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}

const addBooksToPage = async () => {
  const books = await fetchBooks()
  // your code here
}

addBooksToPage()