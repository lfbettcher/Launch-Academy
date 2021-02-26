class Student {
  constructor(name, email) {
    this.name = name;
    this.email = email;
  }
}

class SystemCheck {
  constructor(name, dueDate) {
    this.name = name;
    this.dueDate = dueDate;
    this.submissions = [];
  }

  addSubmission(submission) {
    this.submissions.push(submission);
  }

  averageGrade() {
    const sum = this.submissions.reduce((acc, submission) => acc + submission.grade, 0);
    return sum / this.submissions.length;
  }

  didStudentCompleteSystemCheck(student) {
    if (!student) return false;
    return this.submissions.some((submission) => submission.student.name === student.name);
  }

  submittable() {
    return true;
  }
}

class Cohort {
  constructor(title, startDate, endDate) {
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.students = [];
    this.systemChecks = [];
  }

  enroll(student) {
    if (!this.students.includes(student)) this.students.push(student);
  }

  assign(systemCheck) {
    this.systemChecks.push(systemCheck);
  }

  roster() {
    const studentString = this.students
      .map((student) => `${student.name} ${student.email}\n`)
      .join("");
    return `Cohort: ${this.title}\n--------------------\n${studentString}`;
  }

  systemCheckCompleted(systemCheck) {
    const studentsSubmitted = systemCheck.submissions.map((submission) => submission.student.name);
    return this.students.every((student) => studentsSubmitted.includes(student.name));
  }
}

const grade = {
  meetsExpectations: 2,
  exceedsExpectations: 3,
  doesNotMeetExpectations: 0,
  makeupPoint: 1,
};

class SystemCheckSubmission {
  constructor(solution, student) {
    this.solution = solution;
    this.student = student;
    this.grade = 0;
  }

  assignGrade(grade) {
    if (Object.values(Grade).includes(grade)) {
      this.grade = grade;
      return grade;
    }
    return "Invalid Grade Error!";
  }
}

const firstDay = new Date("2021-04-01");
const lastDay = new Date("2021-08-02");
const cohort = new Cohort("Cohort 30", firstDay, lastDay);

const studentOne = new Student("Sam Seaborn", "sam.seaborn@gmail.com");
const studentTwo = new Student("Josh Lyman", "josh.lyman@gmail.com");
const studentThree = new Student("Toby Ziegler", "toby.ziegler@gmail.com");

const newDueDate = new Date("2021-01-13");
const systemCheck = new SystemCheck("Rock Paper Scissors JS", newDueDate);

cohort.enroll(studentOne);
cohort.enroll(studentTwo);
cohort.enroll(studentThree);

cohort.assign(systemCheck);

/*
console.log("returns 'true' if every student in the cohort has submitted a SC submission");
// const newDueDate = new Date("2021-01-13");
// const systemCheck = new SystemCheck("Rock Paper Scissors JS", newDueDate);
// cohort.assign(systemCheck);

const students = [studentOne, studentTwo, studentThree];
students.forEach((student) => {
  const submission = new SystemCheckSubmission("awesome solution", student);
  systemCheck.addSubmission(submission);
});

console.log(cohort.systemCheckCompleted(systemCheck)); // true
*/

console.log(
  "returns 'false' if just one student in the cohort has not submitted their system check submission"
);
// const newDueDate = new Date("2021-01-13");
// const systemCheck = new SystemCheck("Rock Paper Scissors JS", newDueDate);
// cohort.assign(systemCheck);

const submission = new SystemCheckSubmission("awesome solution", studentOne);
systemCheck.addSubmission(submission);

console.log(cohort.systemCheckCompleted(systemCheck)); // false
