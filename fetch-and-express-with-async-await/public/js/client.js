console.log("Hello from client.js!")

const fetchBooks = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/books")
    console.log("response", response)
    console.log("response.status:", response.status)
    console.log("response.statusText:", response.statusText)
    console.log("response.ok:", response.ok)
  } catch(err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}
