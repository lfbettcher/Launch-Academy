// Yell real loud
const yellButton = document.getElementById("yell");
yellButton.addEventListener("click", () => {
  yell();
});

let yell = () => {
  // Warm-up 1
  document.getElementById("name-header").style.textTransform = "uppercase";
};

// Make me fierce
const fierceButton = document.getElementById("fierceify");
fierceButton.addEventListener("click", () => {
  fierceify();
});

let fierceify = () => {
  // Warm-up 2
  document
    .getElementById("my-photo")
    .setAttribute(
      "src",
      "https://i.insider.com/5f0346ad3ad8614e1350cf7d?width=200&format=jpeg&auto=webp"
    );
};

// Reveal an Easter egg
const easterEggButton = document.getElementById("easter-egg");
easterEggButton.addEventListener("click", () => {
  eggify();
});

let eggify = () => {
  // Warm-up 3
  const eggImg = document.createElement("img");
  eggImg.src =
    "https://partycity6.scene7.com/is/image/PartyCity/_pdp_sq_?$_1000x1000_$&$product=PartyCity/610214";
  document.body.appendChild(eggImg);
};

// Select the first paragraph in the document
const selectMeButton = document.getElementById("select-me");
selectMeButton.addEventListener("click", () => {
  selectFirstParagraph();
});

let selectFirstParagraph = () => {
  // Stylin 2
  document.getElementsByTagName("p")[0].classList.add("selected");
};

// Deselect the first paragraph in the document
const deselectMeButton = document.getElementById("deselect-me");
deselectMeButton.addEventListener("click", () => {
  deselectFirstParagraph();
});

let deselectFirstParagraph = () => {
  // Stylin 3
  document.getElementsByTagName("p")[0].classList.remove("selected");
};

// Toggle the visibility of the image of your face
const toggleFaceButton = document.getElementById("toggle-my-face");
toggleFaceButton.addEventListener("click", () => {
  toggleMyFace();
});

let toggleMyFace = () => {
  // Now you see me... 2
  const myFaceClassList = document.getElementById("my-photo").classList;
  // myFaceClassList.toggle("hidden")
  myFaceClassList.contains("hidden")
    ? myFaceClassList.remove("hidden")
    : myFaceClassList.add("hidden");
};

// Reload page when reload button is clicked -- no need to modify anything here!
const reloadButton = document.getElementById("reload");

reloadButton.addEventListener("click", () => {
  location.reload();
});
