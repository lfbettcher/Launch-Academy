// Random quote
const QUOTES = {
  "Random Quote abc": "Character 1",
  "Random Quote def": "Character 1",
  "Random Quote ghi": "Character 2",
  "Random Quote jkl": "Character 2",
  "Random Quote mno": "Character 3",
  "Random Quote xyz": "Character 3",
};

const setQuote = (quote, character) => {
  document.querySelector("#quote").textContent = quote;
  document.querySelector("#quote-character").textContent = character;
};

const randomQuote = () => {
  const keys = Object.keys(QUOTES);
  const rand = Math.floor(Math.random() * keys.length);
  return [`"${keys[rand]}"`, `${QUOTES[keys[rand]]}`];
};

document.querySelector("#quote-btn").addEventListener("click", () => {
  const quote = randomQuote();
  setQuote(quote[0], quote[1]);
});

const sections = document.querySelectorAll(".section");
sections.forEach((section) => {
  const newButton = document.createElement("button");
  newButton.classList.add("button", "small", "show-hide");
  const showText = `Show ${section.firstElementChild.textContent}`;
  const hideText = `Hide ${section.firstElementChild.textContent}`;
  newButton.textContent = hideText;
  newButton.addEventListener("click", () => {
    section.classList.toggle("hidden");
    newButton.textContent.includes("Hide")
      ? (newButton.textContent = showText)
      : (newButton.textContent = hideText);
  });
  const newDiv = document.createElement("div");
  newDiv.append(newButton);
  section.before(newDiv);
});

const removeAllFav = (parent, notRemove) => {
  [...parent.children].forEach((el) => {
    if (el !== notRemove) {
      el.querySelector("a").textContent = " Select Favorite";
      el.classList.remove("fav-border");
    }
  });
};

const selectFaves = document.querySelectorAll(".select-favorite");
selectFaves.forEach((fav) => {
  const parent = fav.parentElement;
  const favCharacter = parent.firstChild;
  fav.addEventListener("click", () => {
    removeAllFav(parent.parentElement, fav.parentElement);
    parent.classList.toggle("fav-border");
    fav.textContent === " Select Favorite"
      ? (fav.textContent = " Unselect Favorite")
      : (fav.textContent = " Select Favorite");
    let quoteArr;
    do {
      quoteArr = randomQuote();
    } while (quoteArr[1] !== favCharacter.textContent);
    setQuote(quoteArr[0], quoteArr[1]);
  });
});
