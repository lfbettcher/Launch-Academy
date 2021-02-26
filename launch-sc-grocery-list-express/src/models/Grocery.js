import fs from "fs"

const groceriesPath = "groceries.json"
const randomGroceriesPath = "exceeds_random_items.json"

const getGroceries = () => JSON.parse(fs.readFileSync(groceriesPath)).groceries
const getRandomGroceries = () => JSON.parse(fs.readFileSync(randomGroceriesPath)).items

class Grocery {
  constructor({ name }) {
    this.name = name
  }

  static findAll() {
    return getGroceries().map((grocery) => new Grocery(grocery))
  }

  static find(groceryName) {
    return this.findAll().find((grocery) => grocery.name === groceryName)
  }

  save() {
    if (this.isValid()) {
      const groceriesArray = this.constructor.findAll()
      groceriesArray.push(this)
      const groceriesJson = JSON.stringify({ groceries: groceriesArray })
      fs.writeFileSync(groceriesPath, groceriesJson)
      return true
    }
    return false
  }

  isValid() {
    return !(!this || !this.name || this.name.trim() === "")
  }

  static random() {
    const randomGroceriesArray = getRandomGroceries().map((grocery) => new Grocery(grocery))
    const randomGrocery =
      randomGroceriesArray[Math.floor(Math.random() * randomGroceriesArray.length)]
    randomGrocery.save()
    return randomGrocery
  }
}

export default Grocery
