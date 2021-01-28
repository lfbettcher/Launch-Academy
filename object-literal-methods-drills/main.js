const guybrushThreepwood = {
  firstName: "Guybrush",
  lastName: "Threepwood",
  title: "flooring inspector",
  greet() {
    console.log(`I'm ${this.firstName} ${this.lastName}, ${this.title}.`);
  },
  lungCapacityMinutes: 10,
  breathHeldMinutes: 0,
  holdBreath() {
    if (++this.breathHeldMinutes >= this.lungCapacityMinutes) {
      this.gameOver = true;
      console.log("Sorry, game over.");
    }
  },
  gameOver: false,
  inventory: [],
  addToInventory(string) {
    if (!this.inventory.includes(string)) this.inventory.push(string);
  },
  haveGrog: false,
  checkGrogStatus() {
    if (this.inventory.includes("red dye #2" && "battery acid")) {
      console.log("Makin' some grog!");
      this.haveGrog = true;
    } else {
      console.log("Where has all the grog gone?");
    }
  },
};

guybrushThreepwood.greet();
guybrushThreepwood.title = "mighty pirate";
guybrushThreepwood.greet();

guybrushThreepwood.holdBreath();

console.log(Object.keys(guybrushThreepwood));
console.log(Object.values(guybrushThreepwood));

guybrushThreepwood.addToInventory("red dye #2");
guybrushThreepwood.checkGrogStatus();
guybrushThreepwood.addToInventory("battery acid");
guybrushThreepwood.checkGrogStatus();
