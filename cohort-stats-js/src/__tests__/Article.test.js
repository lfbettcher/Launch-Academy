import Article from "../Article.js";

describe("Article class", () => {
  const name = "Article Name";
  const body = "Article Body";
  let article;

  beforeEach(() => {
    article = new Article(name, body);
  });

  it("defines article", () => {
    expect(article).toBeInstanceOf(Article);
  });

  it("has an initialized name", () => {
    expect(article.name).toEqual(name);
  });

  it("has an initialized body", () => {
    expect(article.body).toEqual(body);
  });

  describe("#submittable", () => {
    it("returns false", () => {
      expect(article.submittable()).toEqual(false);
    });
  });
});
