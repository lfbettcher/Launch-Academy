import fs from "fs"

const articlesPath = "articles.json"

const articlesJson = () => {
  return JSON.parse(fs.readFileSync(articlesPath))
}

class Article {
  constructor({ title, url, description }) {
    this.title = title
    this.url = url
    this.description = description || "none given"
  }

  static findAll() {
    const articlesData = articlesJson().articles

    let articles = []
    articlesData.forEach((article) => {
      const newArticle = new Article(article)
      articles.push(newArticle)
    })

    return articles
  }

  save() {
    const articles = this.constructor.findAll()
    articles.push(this)
    const data = { articles: articles }
    fs.writeFileSync(articlesPath, JSON.stringify(data))
    return true
  }
}

export default Article
