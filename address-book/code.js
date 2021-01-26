const addressBook = {
  "Mark Zuckerberg": "markymark@example.com",
  "Grace Hopper": "foundABug@example.com",
  "Alan Turing": "alanTheMachine@example.com",
  "Sister Mary Kenneth Keller": "theOGDoctor@example.com",
  "Ada Lovelace": "kindaCalledThisWholeComputerThing@example.com",
  "Bill Gates": "noBlueScreens@example.com",
};

const lowerCaseNameAddressBook = Object.fromEntries(
  Object.entries(addressBook).map(([name, email]) => [name.toLowerCase(), email])
);

const addToAddressBook = (name) => {
  let email;
  do {
    email = prompt(`What is ${name}'s email address?`);
    if (email === null) return; // User selects Cancel
  } while (!email);
  addressBook[name] = email;
  console.log(`${name}'s email ${email} was added to the address book!`);
};

const askAdd = (name) => {
  let add;
  do {
    add = prompt(`${name} not found.\nWould you like to add ${name} to the address book? (y/n)`);
    if (add === null) return; // User selects cancel
    if (add) add = add.charAt(0).toLowerCase();
    if (add === "y") addToAddressBook(name);
  } while (!add || (add !== "y" && add !== "n"));
};

const getEmailAddresses = () => {
  while (true) {
    const nameInput = prompt('Enter a name or "exit" to quit');
    if (nameInput === null) return; // User selects Cancel
    let lowerCaseName;
    if (nameInput) lowerCaseName = nameInput.toLowerCase();
    if (lowerCaseName === "exit") return;
    if (lowerCaseNameAddressBook.hasOwnProperty(lowerCaseName)) {
      console.log(lowerCaseNameAddressBook[lowerCaseName]);
    } else if (nameInput) {
      askAdd(nameInput);
    }
  }
};

getEmailAddresses();
