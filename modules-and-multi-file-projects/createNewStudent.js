import createNewBook from "./createNewBook.js";

const createNewStudent = (firstName, lastName, favoriteClass) => {
  const student = {
    firstName,
    lastName,
    favoriteClass,
    book: createNewBook("my book title", "my book author"),
    greet() {
      console.log(`${this.firstName} ${this.lastName} loves ${this.favoriteClass}`);
    },
  };

  return student;
};

export default createNewStudent;
