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
      const { name, ingredients } = meal;
      console.log(`Menu item: ${name}`);
      let ingredientsString = "";
      ingredients.forEach((ingredient) => {
        ingredientsString += ` ${ingredient.name}`;
      });
      console.log(`Ingredients:${ingredientsString}`);
    });
  },
});

export default createNewMenu;
