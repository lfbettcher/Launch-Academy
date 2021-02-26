// 1. Print to the console the text of any elements with a class of oh-no
document.querySelectorAll(".oh-no").forEach((el) => console.log(el.textContent));

// 2. Print to the console the tag name of the element with an id of hashtag-inspiration
console.log(document.getElementById("hashtag-inspiration").tagName);

// 3. Print to the console the all the HTML contained within any elements with a class of tv
document.querySelectorAll(".tv").forEach((el) => console.log(el.innerHTML));

// 4. Reluctantly fix the typo "Never gonna run around and dessert you" to "Never gonna run around and desert you", where "desert" has one s
document.querySelectorAll(".intentional-misspelling-of-desert").forEach((el) => {
  el.textContent = el.textContent.replace("dessert", "desert");
});

// 5. Replace the text in the fourth child of the element with an id of purple-jaguar-eye with lyrics of your choosing
document.getElementById("purple-jaguar-eye").children[3].textContent = "fourth child";

// 6. Turn each <h2> into an <h3>
document.querySelectorAll("h2").forEach((el) => {
  el.outerHTML = el.outerHTML.replace("h2", "h3");
});

// 7. Add a class of scary-thoughts to the element with an id of woof
document.getElementById("woof").classList.add("scary-thoughts");

// 8. Add an image of a fish as the last child of the element with an id of shes-the-one-for-me
const fishPic = document.createElement("img");
fishPic.src = "https://i.insider.com/57a4db38dd089551028b465b?width=400&format=jpeg&auto=webp";
document.getElementById("shes-the-one-for-me").appendChild(fishPic);

// 9. Delete the class rickroll from the div that also contains the class limerickroll
document.querySelector("div.limerickroll").classList.remove("rickroll");

// 10. Add your own lyrics to the element with an id of lyrics by adding one additional <p>
const addP = document.createElement("p");
addP.textContent = "add my own lyrics";
document.querySelector("#lyrics").appendChild(addP);

// 11. Add a new paragraph explaining what your favorite song is as the last child of <body>
const favSong = document.createElement("p");
for (let i = 1; i <= 5; ++i) {
  favSong.appendChild(document.createTextNode(`Favorite song explanation #${i}. `));
}
document.querySelector("body").appendChild(favSong);

// 12. Add an attribute called foo, with a value of bar, to each child of the element with a class of rickroll
document.querySelectorAll(".rickroll").forEach((el) => {
  Array.from(el.children).forEach((child) => {
    child.setAttribute("foo", "bar");
  });
});
