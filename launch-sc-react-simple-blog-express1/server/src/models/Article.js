import fs from "fs"
import _ from "lodash"

const articlesPath = "articles.json"

class Article {
  constructor({ id, title, content }) {
    this.id = id
    this.title = title
    this.content = content
  }

  static findAll() {
    const articleData = JSON.parse(fs.readFileSync(articlesPath)).articles
    let articles = []
    articleData.forEach(article => {
      const newArticle = new Article(article)
      articles.push(newArticle)
    })
    return articles
  }

  static findById(id) {
    const articleData = JSON.parse(fs.readFileSync(articlesPath)).articles
    const myArticle = articleData.find(article => article.id == id)
    return new Article(myArticle)
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["title", "content"]
    let isValid = true

    for(const requiredField of requiredFields) {
      this.errors[requiredField] = []
      if(!this[requiredField]) {
        isValid = false
        this.errors[requiredField].push("Can't be blank")
      }
    }
    return isValid
  }

  static getNextArticleId() {
    const maxArticle = _.maxBy(this.findAll(), article => article.id)
    return maxArticle.id + 1
  }

  save() {
    if(this.isValid()) {
      delete this.errors
      this.id = this.constructor.getNextArticleId()
      const articles = this.constructor.findAll()
      articles.push(this)
      const data = { articles: articles }
      fs.writeFileSync(articlesPath, JSON.stringify(data))
      return true
    } else {
      return false
    }
  }
}

export default Article