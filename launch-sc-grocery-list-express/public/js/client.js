const fetchRandomGrocery = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/groceries/random")
    if (!response.ok) {
      const errorMessage = `${response.status} (${response.statusText})`
      const error = new Error(errorMessage)
      throw error
    }
    const responseBody = await response.json()
    console.log("responseBody:", responseBody)
    return responseBody
  } catch (err) {
    console.error("Error in fetch!")
    console.error(err)
  }
}

const getRandomGrocery = async () => {
  const randomGrocery = await fetchRandomGrocery()
  const groceriesList = document.querySelector("#groceriesList")
  groceriesList.innerHTML += `<li><a href="groceries/${randomGrocery.name}">${randomGrocery.name}</a></li>`
}

document.querySelector("#randomBtn").addEventListener("click", getRandomGrocery)
