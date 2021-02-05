class Video {
  constructor(name, body, url) {
    this.name = name;
    this.body = body;
    this.url = url;
  }

  submittable() {
    return false;
  }
}

export default Video;
