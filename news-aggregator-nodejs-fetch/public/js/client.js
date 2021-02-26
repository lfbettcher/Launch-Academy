const fetchRandomArticle = async () => {
  try {
    const response = await fetch("http://localhost:3000/api/v1/articles/random")
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

const showRandomArticle = async () => {
  const article = await fetchRandomArticle()
  document.querySelector("#article").innerHTML = `<li>Title: ${article.title
  }</li><li>Description: ${article.description}</li><li><a href=${article.url
  } target="_blank">${article.url}</a></li>`
}

document.querySelector("#randomBtn").addEventListener("click", showRandomArticle)