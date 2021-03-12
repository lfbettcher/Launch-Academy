import fs from "fs"
import _ from "lodash"

const drinksPath = "drinks.json"

class Drink {
  constructor({ id, title, content }) {
    this.id = id
    this.title = title
    this.content = content
  }

  static findAll() {
    const drinkData = JSON.parse(fs.readFileSync(drinksPath)).drinks
    const drinks = drinkData.map((drink) => new Drink(drink))
    return drinks
  }

  static findById(id) {
    const allDrinks = this.findAll()
    const myDrink = allDrinks.find((drink) => drink.id == id)
    return myDrink
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["title", "content"]
    let isValid = true

    for (const requiredField of requiredFields) {
      this.errors[requiredField] = []
      if (!this[requiredField]) {
        isValid = false
        this.errors[requiredField].push("Can't be blank")
      }
    }
    return isValid
  }

  static getNextDrinkId() {
    const maxDrink = _.maxBy(this.findAll(), (Drink) => Drink.id)
    return maxDrink.id + 1
  }

  save() {
    if (this.isValid()) {
      delete this.errors
      this.id = this.constructor.getNextDrinkId()
      const drinks = this.constructor.findAll()
      drinks.push(this)
      const data = { drinks: drinks }
      fs.writeFileSync(drinksPath, JSON.stringify(data))
      return true
    } else {
      return false
    }
  }
}

export default Drink
