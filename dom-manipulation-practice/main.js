const toDo = ["Write some JavaScript", "Submit System Check", "Take a Break"];
const toDoList = document.getElementById("todo-list");
toDo.forEach((item) => {
  toDoList.innerHTML += `<li>${item}</li>`;
});
