class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  celebrateBirthday() {
    console.log("WooHoo! Happy Birthday!!");
    this.age += 1;
  }
}

export default Person;
