const createNewMenu = (title, startTime, endTime) => ({
  title,
  startTime,
  endTime,
  meals: [],
  addMeal(newMeal) {
    this.meals.push(newMeal);
  },
  printMenu() {
    console.log(`${this.title} is from ${this.startTime} to ${this.endTime}.`);
    this.meals.forEach((meal) => {
      console.log(`Menu item: ${meal.name}`);
      let ingredientsString = "";
      meal.ingredients.forEach((ingredient) => {
        ingredientsString += ` ${ingredient.name}`;
      });
      console.log(`Ingredients:${ingredientsString}`);
    });
  },
});

export default createNewMenu;
