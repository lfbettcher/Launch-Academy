class Pool {
  constructor(width, length) {
    this.width = width;
    this.length = length || width;
  }

  area() {
    return this.width * this.length;
  }

  volume() {
    return this.area() * 5;
  }
}

export default Pool;
