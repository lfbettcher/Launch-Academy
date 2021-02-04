import Person from "../Person.js";

describe("A person", () => {
  const firstName = "FirstName";
  const lastName = "LastName";
  const email = "email@email.com";
  let person;

  beforeEach(() => {
    person = new Person(firstName, lastName, email);
  });

  it("person has a first name", () => {
    expect(person.firstName).toEqual(firstName);
  });

  it("person has a last name", () => {
    expect(person.lastName).toEqual(lastName);
  });

  it("person has an email address", () => {
    expect(person.email).toEqual(email);
  });
});
