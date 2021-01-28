// Step 7 - Building your spaceship's rockets!
const createNewRocket = () => ({
  fuel: 0,
  addFuel(amount) {
    this.fuel += amount;
  },
  // Step 8 - Define a rocket's fire function!
  fire() {
    if (this.fuel > 0) {
      this.fuel--;
      console.log(`The engines have fired. Current fuel: ${this.fuel}.`);
      return true;
    }
    console.log("The engines failed to fire.");
    return false;
  },
});

export default createNewRocket;
