import creatures from "../creatures.js";

class MagicalCreature {
  constructor({ name, magicalAbility, age }) {
    this.name = name;
    this.magicalAbility = magicalAbility;
    this.age = age || null;
    this.isAncient = this.isAncient();
  }

  isAncient() {
    return this.age > 200 || this.age === null;
  }

  static findAll() {
    const creatureObjects = creatures.map(
      creature =>
        new MagicalCreature({
          name: creature.name,
          magicalAbility: creature.ability,
          age: creature.age
        })
    );
    return creatureObjects;
  }

  static find(creatureName) {
    return creatures.find(creature => creature.name === creatureName);
  }
}

export default MagicalCreature;
