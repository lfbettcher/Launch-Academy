import sayPopPop from "./sayPopPop.js";
import sayMyNameIsAlex from "./sayMyNameIsAlex.js";
import createNewStudent from "./createNewStudent.js";

const annie = createNewStudent("Annie", "Edison", "Spanish 101");
const jeff = createNewStudent("Jeff", "Winger", "Intro to Pottery");

annie.greet();
jeff.greet();
console.log(annie.book);
const catchphrase = "This will not interfere with the output";

sayPopPop();
sayPopPop();

sayMyNameIsAlex();
sayMyNameIsAlex();
