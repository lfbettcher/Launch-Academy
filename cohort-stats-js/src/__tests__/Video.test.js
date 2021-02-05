import Video from "../Video.js";

describe("Video class", () => {
  const name = "Video Name";
  const body = "Video Body";
  const url = "https://www.video.com";
  let video;

  beforeEach(() => {
    video = new Video(name, body, url);
  });

  it("defines video", () => {
    expect(video).toBeInstanceOf(Video);
  });

  it("has an initialized name", () => {
    expect(video.name).toEqual(name);
  });

  it("has an initialized body", () => {
    expect(video.body).toEqual(body);
  });

  it("has an initialized url", () => {
    expect(video.url).toEqual(url);
  });

  describe("#submittable", () => {
    it("returns false", () => {
      expect(video.submittable()).toEqual(false);
    });
  });
});
