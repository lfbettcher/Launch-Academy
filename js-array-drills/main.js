const teachers = ["Arrington", "Kincart", "Alberts", "Pickett"];

const rooms = [
  ["Andy", "Rodolfo", "Lynn", "Talia"],
  ["Al", "Ross", "Jorge", "Dante"],
  ["Nick", "Kim", "Jasmine", "Dorothy"],
  ["Adam", "Grayson", "Aliyah", "Alexa"],
];

// List all the teachers with an even index number (including 0)
const evenIndex = (n) => n % 2 === 0;

teachers.forEach((teacher, index) => {
  if (evenIndex(index)) console.log(teacher);
});

// List all of the teachers with the letter i in their name
const iInName = (name) => name.toLowerCase().includes("i");

teachers.forEach((teacher) => {
  if (iInName(teacher)) console.log(teacher);
});

// Return the number of teachers
const teacherCount = () => teachers.length;

console.log(teacherCount());

// Return the number of rooms of students
const roomCount = () => rooms.length;

console.log(roomCount());

// Return the number of students in all the rooms
const studentCount = () => {
  let count = 0;
  rooms.forEach((room) => {
    count += room.length;
  });
  return count;
};

console.log(studentCount());

// Return the students who have an i in their names
const whichStudents = () => {
  const studentsWithI = [];
  rooms.forEach((room) => {
    room.forEach((student) => {
      if (iInName(student)) studentsWithI.push(student);
    });
  });
  return studentsWithI;
};

console.log(`The students who have an 'i' in their name are  ${whichStudents()}.`);

// Return the teacher who has the given student in their room
const whichTeacher = (student) => {
  let teacher;
  rooms.forEach((room, index) => {
    if (room.includes(student)) teacher = teachers[index];
  });
  return teacher;
};

console.log(`The teacher who has Jorge is ${whichTeacher("Jorge")}.`);
console.log(`The teacher who has Alexa is ${whichTeacher("Alexa")}.`);
