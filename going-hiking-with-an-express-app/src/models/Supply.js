import fs from "fs";

const suppliesPath = "supplies.json";

class Supply {
  constructor({ name }) {
    this.name = name;
  }

  static findAll() {
    const suppliesData = JSON.parse(fs.readFileSync(suppliesPath)).supplies;
    const supplies = suppliesData.map((supply) => new Supply(supply));
    return supplies;
  }

  save() {
    const suppliesArray = this.constructor.findAll();
    suppliesArray.push(this);
    const jsonSupplies = JSON.stringify({ supplies: suppliesArray });
    fs.writeFileSync(suppliesPath, jsonSupplies);
    return true;
  }
}

export default Supply;
