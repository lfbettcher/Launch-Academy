const editButton = document.getElementById("edits");
const newSloganText = "Craft beer street art hoodie waistcoat tacos mustache chartreuse.";
const newSloganCredit = "~ Normcore keytar gastropub pork belly";
const colors = ["#00b8a9", "#f6416c", "#ffde7d", "#3d84a8", "#ff9a00", "#6639a6"];

editButton.addEventListener("click", () => {
  makeEdits();
});

let makeEdits = () => {
  document.body.style.backgroundColor = "#E5F3F4";
  document.querySelector("#slogan-text").textContent = newSloganText;
  document.querySelector("#slogan-credit").textContent = newSloganCredit;
  const valuesList = document.querySelector("#values-list");
  valuesList.removeChild(valuesList.firstElementChild);
};

const companyLogo = document.querySelector("#company-logo");
companyLogo.addEventListener("mouseover", () => {
  alert(`${newSloganText}\n${newSloganCredit}`);
});

const coreValueItems = document.querySelectorAll("#values-list li");
coreValueItems.forEach((item) => {
  item.addEventListener("mouseover", () => {
    changeColor(item);
  });
});

const changeColor = (colorThis) => {
  colorThis.style.color = colors[Math.floor(Math.random() * colors.length)];
};

const singBtn = document.querySelector("#sing-btn");
singBtn.addEventListener("click", () => {
  singSong();
});

const singSong = () => {
  alert("Brunch 8-bit kickstarter, hella flannel portland scenester franzen vice.");
};
