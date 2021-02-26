import fs from "fs";

const animalsPath = "animals.json";

class Animals {
  constructor({ id, name, type }) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  static findAll() {
    const stringifiedData = fs.readFileSync(animalsPath);
    const animalData = JSON.parse(stringifiedData).animals;
    const animals = animalData.map((animal) => new Animals(animal));
    return animals;
  }
}

export default Animals;
