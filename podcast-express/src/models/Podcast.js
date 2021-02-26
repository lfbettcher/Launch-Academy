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
  }
}

export default Podcast
