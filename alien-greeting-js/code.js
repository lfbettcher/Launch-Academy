/*
// Part 1
const greet = (name) => console.log(`Hi ${name}!`);

// Part 2
const greetings = ["Hi", "Yo", "Hey", "Howdy"];
const greet = (name) =>
  console.log(`${greetings[Math.floor(Math.random() * greetings.length)]} ${name}!`);
*/

// Part 3
const englishGreetings = ["Hi", "Yo", "Hey", "Howdy"];
const greetings = { english: "Hi", spanish: "Hola", italian: "Ciao", irken: "DOOM de doom" };
const greet = (name, language = "English") => {
  const lang = language.toLowerCase();
  let greeting = greetings[lang];
  // Want random greeting for english?
  if (lang === "english")
    greeting = englishGreetings[Math.floor(Math.random() * englishGreetings.length)];
  console.log(`${lang.charAt(0).toUpperCase() + lang.slice(1)}: "${greeting} ${name}!"`);
};
