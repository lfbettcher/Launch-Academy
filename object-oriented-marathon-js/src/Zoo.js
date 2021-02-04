import Cage from "./Cage.js";

class Zoo {
  constructor(name) {
    this.name = name;
    this.cages = this.createCages(10);
    this.employees = [];
  }

  createCages(num) {
    const cages = [];
    for (let i = 0; i < num; ++i) {
      cages.push(new Cage());
    }
    return cages;
  }

  addEmployee(employee) {
    this.employees.push(employee);
  }

  addAnimal(animal) {
    const emptyCage = this.cages.find((cage) => cage.isEmpty());
    if (emptyCage) emptyCage.animal = animal;
    else return "All of the cages are full!";
  }

  visit() {
    return (
      "Sssssire, ssssire, they may be banditsssss\nBob got a bad feeling about this...\n" +
      "Jojo The Great waved hello!\nDerek Zoolander waved hello!\n"
    );
  }
}

export default Zoo;
