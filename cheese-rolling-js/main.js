// generate a random integer between 10 and 60
const generateRandomRolltime = () => Math.floor(Math.random() * (60 - 10 + 1) + 10);

const rawDataArray = [
  {
    cheeseName: "Red Leicester",
    cheeseDeliciousness: 9,
    contestantName: "John C",
    contestantHunger: 8,
  },
  {
    cheeseName: "Tilsit",
    cheeseDeliciousness: 3,
    contestantName: "Michael P",
    contestantHunger: 10,
  },
  {
    cheeseName: "Caerphilly",
    cheeseDeliciousness: 5,
    contestantName: "Eric I",
    contestantHunger: 2,
  },
  {
    cheeseName: "Bel Paese",
    cheeseDeliciousness: 4,
    contestantName: "Graham C",
    contestantHunger: 4,
  },
  {
    cheeseName: "Red Windsor",
    cheeseDeliciousness: 9,
    contestantName: "Terry G",
    contestantHunger: 6,
  },
  {
    cheeseName: "Stilton",
    cheeseDeliciousness: 7,
    contestantName: "Terry J",
    contestantHunger: 1,
  },
  {
    cheeseName: "Gruyere",
    cheeseDeliciousness: 2,
    contestantName: "Carol C",
    contestantHunger: 6,
  },
];

// Create array of contestant objects by mapping over rawDataArray
const registerContestants = (dataArray) =>
  dataArray.map((data) => ({
    name: data.contestantName,
    hunger: data.contestantHunger,
    cheese: { name: data.cheeseName, deliciousness: data.cheeseDeliciousness },
    disqualified: false,
    results: [],
  }));

const contestants = registerContestants(rawDataArray);

// Calculates if a contestant has eaten their cheese and should be disqualified
const checkForCheeseEating = (contestant) => {
  if ((contestant.hunger + contestant.cheese.deliciousness) / 2 >= 7) {
    contestant.disqualified = true;
  }
};

contestants.forEach((contestant) => checkForCheeseEating(contestant));

// Simulate a round of rolling the cheese up the hill
const rollDatCheese = (contestant) => {
  if (!contestant.disqualified) contestant.results.push(generateRandomRolltime());
};

// Roll for each contestant 3 times
contestants.forEach((contestant) => {
  for (let i = 0; i < 3; i++) rollDatCheese(contestant);
});
console.log(contestants); // check results

// Report contestant's name, type of cheese, whether they were disqualified,
// and each of their 3 cheese rolling results.
const reportIndividualResults = (contestant) => {
  const disq = contestant.disqualified;
  console.log(
    `Contestant ${contestant.name} entered this competition with a lovely ${
      contestant.cheese.name
    } cheese. By ${disq ? "eating the cheese" : "meeting the criteria"}, they were ${
      disq ? "disqualified and not " : ""
    }able to compete${disq ? "" : ` with resulting scores of ${contestant.results.join(", ")}`}.`
  );
};

contestants.forEach((contestant) => reportIndividualResults(contestant));
