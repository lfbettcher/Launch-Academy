// Exercise 1): Find the top navbar, using a query for the HTML element type. The navbar's type is `<nav>`.
const nav = document.getElementsByTagName("nav")[0];

// Exercise 2): Find the sidebar on the left by using its id and set it equal to the variable sideBar.
const sideBar = document.getElementById("sidebar-left");

// Exercise 3): Find the 'Pages' and 'Groups' sections of the sidebar by using their class.
const pages = document.getElementsByClassName("pages")[0];
const groups = document.getElementsByClassName("groups")[0];

// Exercise 4): Set the text of the 'Favorites' `h5` to say "Least Favs".
sideBar.getElementsByClassName("favorites")[0].getElementsByTagName("h5")[0].textContent =
  "Least Favs";

// Exercise 5): Find the first of the ads in the sidebar and hide it.
const ads = document.getElementsByClassName("ads")[0];
ads.firstElementChild.style.visibility = "hidden";

// Exercise 6): Set the visibility on the ad that you hid in the previous exercise to make it visible again.
ads.firstElementChild.style.visibility = "visible";

// Exercise 7): Use `setAttribute` to change `src` attribute of one of the ads in the sidebar.
ads.firstElementChild
  .getElementsByTagName("img")[0]
  .setAttribute("src", "http://placebear.com/200/300");

// Exercise 8): Find Sam's post and change its text to something incredible.
const listOfPosts = document.getElementById("list-of-posts");
listOfPosts.getElementsByTagName("p")[4].innerText = "something incredible";

// Exercise 9): Find the first post and add the `.post-liked` class to it, and watch it turn blue.
listOfPosts.getElementsByTagName("li")[0].classList.add("post-liked");

// Exercise 10: Find the second post and add the `.post-shared` class to the `li` containing the text Share to watch it turn red.
const secondPost = listOfPosts.getElementsByTagName("li")[7];
secondPost.classList.add("post-shared");

// Exercise 11: On the second post, remove the `.post-shared` class from the `li` containing the text Share.
secondPost.classList.remove("post-shared");
