import fs from "fs"

const recipesPath = "recipes.json"

class Recipe {
  constructor(name) {
    this.name = name
  }

  static findAll() {
    const recipeData = JSON.parse(fs.readFileSync(recipesPath)).recipes
    const recipes = recipeData.map(recipe => new Recipe(recipe))
    return recipes
  }

  static findById(id) {
    const allRecipes = this.findAll()
    const myRecipe = allRecipes.find(recipe => recipe.id == id)
    return myRecipe
  }

  isValid() {
    this.errors = {}
    const requiredFields = ["title"]
    let isValid = true

    for(const requiredField of requiredFields) {
      this.errors[requiredField] = []
      if(!this[requiredField]) {
        isValid = false
        this.errors[requiredField].push("Can't be blank")
      }
    }
    return isValid
  }

  static getNextRecipeId() {
    const maxRecipe = _.maxBy(this.findAll(), Recipe => Recipe.id)
    return maxRecipe.id + 1
  }

  save() {
    if(this.isValid()) {
      delete this.errors
      this.id = this.constructor.getNextRecipeId()
      const recipes = this.constructor.findAll()
      recipes.push(this)
      const data = { recipes: recipes }
      fs.writeFileSync(recipesPath, JSON.stringify(data))
      return true
    } else {
      return false
    }
  }
}

export default Recipe