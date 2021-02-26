const text = document.getElementById("changing-color-text");

/*
text.addEventListener("click", (event) => {
  // this line lists the available colors in an array named colors
  const colors = ["Aqua", "Blue", "Orange", "Green", "Red", "LightSteelBlue"];
  // this line uses the random method to select an index from the colors list and set it to the variable index
  const index = Math.floor(Math.random() * colors.length);
  // this line sets the variable color to whichever index was randomly set on the line above this one
  const color = colors[index];
  // this line says the color style for the targeted event in this function is now set to the color defined on the line above this one (event.target.style is comprised of keywords in JavaScript)
  event.target.style.color = color;
});
*/

const changeFontColor = (event) => {
  const colors = ["Aqua", "Blue", "Orange", "Green", "Red", "LightSteelBlue"];
  const index = Math.floor(Math.random() * colors.length);
  const color = colors[index];

  event.target.style.color = color;
  // Note that some older versions of IE e.g. 6-8 may not support event.target, so we may decide to use event.srcElement.style.color instead.
};

text.addEventListener("click", changeFontColor);

window.onload = () => {
  console.log("window loaded.");
};
