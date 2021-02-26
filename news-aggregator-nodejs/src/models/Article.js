// you may want to use this for the optional challenge
import fs from "fs";

// path to your articles.json file
const articlesPath = "articles.json";

// read from the articles.json file
const articlesJson = () => JSON.parse(fs.readFileSync(articlesPath));

// add to the articles.json file.
const updateArticlesJsonData = (newArticleObject) => {
  const { articles } = articlesJson();
  articles.push(newArticleObject);

  const data = { articles };
  fs.writeFileSync(articlesPath, JSON.stringify(data));
  return true;
};

const isBlank = (text) => !text || text.toString().trim() === "";

// Assignment spec states "A URL is valid if it begins with http:// or https://"
const isValidUrl = (url) => /^(http[s]?:\/\/)/.test(url);

const isUrlSubmitted = (url) => {
  return articlesJson().articles.some(article => article.url === url)
}

class Article {
  constructor({ title, url, description }) {
    this.title = title;
    this.url = url;
    this.description = description || null;
    this.errors = {};
  }

  save() {
    if (this.isValid()) return updateArticlesJsonData(this);
    return false;
  }

  isValid() {
    for (const prop in this) {
      if (isBlank(this[prop])) this.addError(prop, "Can't be blank");
    }
    if (!isValidUrl(this.url)) this.addError("url", "Invalid URL");
    if (isUrlSubmitted(this.url)) this.addError("url", "URL has already been submitted.");
    if (!this.description || this.description.length < 20)
      this.addError("description", "Must have at least 20 characters");
    return Object.keys(this.errors).length === 0;
  }

  addError(prop, msg) {
    this.errors[prop] ? this.errors[prop].push(msg) : this.errors[prop] = [msg];
  }

  static findAll() {
    return articlesJson().articles.map((article) => new Article(article));
  }
}

export default Article;
