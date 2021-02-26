import fs from "fs"

const podcastsPath = "podcasts.json"

const getPodcasts = () => JSON.parse(fs.readFileSync(podcastsPath)).podcasts

class Podcast {
  constructor({ title, description, network, slug }) {
    this.title = title
    this.description = description
    this.network = network
    this.slug = slug
  }

  static findAll() {
    return getPodcasts().map((podcast) => new Podcast(podcast))
  }

  static save() {
    getPodcasts().podcasts.push(newArticleObject)

    const data = { articles }
    fs.writeFileSync(articlesPath, JSON.stringify(data))
    return true
  }
}

export default Podcast
