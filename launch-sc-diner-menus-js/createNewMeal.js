const createNewMeal = (name, ingredients) => ({
  name,
  ingredients,
  isVegetarian() {
    return !this.ingredients.some((ingredient) => ingredient.category.toLowerCase() === "meat");
  },
  isDelicious() {
    return this.ingredients.some((ingredient) => ingredient.category.toLowerCase() === "cheese");
  },
});

export default createNewMeal;
