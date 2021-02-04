class Employee {
  constructor(firstName, lastName, title = "Zookeeper") {
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
  }

  fullTitle() {
    return `${this.fullName()}, ${this.title}`;
  }

  greet() {
    return `${this.fullName()} waved hello!`;
  }

  fullName() {
    return `${this.firstName} ${this.lastName}`;
  }
}

export default Employee;
