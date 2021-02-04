const TOTAL_SYSTEM_CHECKS = 16;
const POINTS_TO_PASS = 25;
const POSSIBLE_SCORES = [0, 1, 2, 3];

// GET USER INPUT /////////////////////
const currentPoints = 6;
const systemChecksCompleted = 2;
// ////////////////////////////////////

const needToPass = POINTS_TO_PASS - currentPoints;
const remainingSystemChecks = TOTAL_SYSTEM_CHECKS - systemChecksCompleted;

const scoreCombos = (possibleScores, pointsNeeded) => {
  const results = [];
  const combo = [];
  makeCombo(0, pointsNeeded);
  return results;

  function makeCombo(index, remainingPointsNeeded) {
    if (remainingPointsNeeded <= 0 && combo.length <= remainingSystemChecks)
      return results.push(combo.slice());
    if (
      index === possibleScores.length ||
      remainingPointsNeeded < 0 ||
      combo.length > remainingSystemChecks
    )
      return;
    combo.push(possibleScores[index]);
    makeCombo(index, remainingPointsNeeded - possibleScores[index]);
    combo.pop();
    makeCombo(index + 1, remainingPointsNeeded);
  }
};

const allScoreCombosArray = scoreCombos(POSSIBLE_SCORES, needToPass);

const makeScoresArray = (allScoreCombos) =>
  allScoreCombos.map((comboArray) =>
    comboArray.reduce((scoreCounts, score) => {
      const scoreKey = score.toString();
      if (scoreKey in scoreCounts) scoreCounts[scoreKey]++;
      else scoreCounts[scoreKey] = 1;
      return scoreCounts;
    }, {})
  );

console.log(makeScoresArray(allScoreCombosArray));
