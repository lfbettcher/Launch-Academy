class Article {
  constructor(name, body) {
    this.name = name;
    this.body = body;
  }

  submittable() {
    return false;
  }
}

export default Article;
