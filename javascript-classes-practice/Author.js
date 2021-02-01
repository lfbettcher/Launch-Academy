class Author {
  constructor(firstName, lastName, genre) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.genre = genre;
    this.anthology = [];
  }

  writeBook(book) {
    this.anthology.push(book);
  }
}

export default Author;
