const editButton = document.getElementById("edits");
const newSlogan = "new slogan of my choosing";
const colors = ["#120078", "#9d0191", "#fd3a69", "#556052", "#d37815"];

editButton.addEventListener("click", () => {
  makeEdits();
});

let makeEdits = () => {
  document.body.style.backgroundColor = "#EEEEEE";
  document.querySelector("#slogan-text").textContent = newSlogan;
  document.querySelector("#slogan-credit").textContent = "~ Persons Name, The Book They Wrote";
  const valuesList = document.querySelector("#values-list");
  valuesList.removeChild(valuesList.firstElementChild);
};

const companyLogo = document.querySelector("#company-logo");
companyLogo.addEventListener("mouseover", () => alert(newSlogan));

const coreValueItems = document.querySelectorAll("#values-list li");
coreValueItems.forEach((item) => {
  item.addEventListener("mouseover", () => {
    changeColor(item);
  });
});

const changeColor = (colorThis) => {
  colorThis.style.color = colors[Math.floor(Math.random() * colors.length)];
};

const singASongBtn = document.querySelector("#sing-a-song-btn");
singASongBtn.addEventListener("click", () => {
  singSong();
});

const singSong = () => {
  alert("lyrics");
};
