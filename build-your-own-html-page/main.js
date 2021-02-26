// 1
Array.from(document.querySelectorAll("#grocery-list li")).find(
  (listItem) => listItem.textContent === "Milk"
).textContent = "Oat Milk";

// 2
const groceryList = document.querySelector("#grocery-list");
const fruits = ["Fruit1", "Fruit2", "Fruit3"];
fruits.forEach((fruit) => {
  createAndAppend("li", fruit, groceryList);
});

// 3
const pantryDiv = document.createElement("div");
pantryDiv.id = "pantry-section";
document.body.insertBefore(
  pantryDiv,
  document.getElementById("groceries-section").nextElementSibling
);
createAndAppend("h1", "Our Pantry", pantryDiv);

// 4
const pantryList = createAndAppend("ul", "", pantryDiv);
pantryList.id = "pantry-list";
pantryList.innerHTML = "<li>Thing1</li><li>Thing2</li><li>Thing3</li>";

// 5
groceryList.appendChild(pantryList.lastElementChild);

// 6
const notesDiv = document.createElement("div");
notesDiv.id = "notes-section";
document.body.insertBefore(notesDiv, pantryDiv.nextElementSibling);
createAndAppend("h1", "Notes", notesDiv);

// 7
createAndAppend("p", "paragraph with some notes on what you want to make for the week", notesDiv);
const link = createAndAppend("a", "Recipe Name", notesDiv);
link.href = "https://www.google.com";

// Creates and appends element
function createAndAppend(tag, text, parent) {
  const el = document.createElement(tag);
  el.textContent = text;
  parent.appendChild(el);
  return el;
}
