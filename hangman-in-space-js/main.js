const printPuzzle = (word, guessed = []) => {
  let output = "";
  word.split("").forEach((letter) => {
    output += guessed.includes(letter) ? `${letter} ` : "_ ";
  });
  return output;
};

console.log(printPuzzle("triangle", ["t", "s", "g"]));
