import Student from "../Student.js";
import SystemCheck from "../SystemCheck.js";
import SystemCheckSubmission from "../SystemCheckSubmission.js";
import Cohort from "../Cohort.js";

describe("cohort class", () => {
  let firstDay;
  let lastDay;
  let cohort;
  let cohort2;
  let studentOne;
  let studentTwo;
  let studentThree;
  let studentFour;
  let studentFive;
  let studentSix;

  beforeEach(() => {
    firstDay = new Date("2021-04-01");
    lastDay = new Date("2021-08-02");
    cohort = new Cohort("Cohort 30", firstDay, lastDay);

    studentOne = new Student("Sam Seaborn", "sam.seaborn@gmail.com");
    studentTwo = new Student("Josh Lyman", "josh.lyman@gmail.com");
    studentThree = new Student("Toby Ziegler", "toby.ziegler@gmail.com");

    cohort.enroll(studentOne);
    cohort.enroll(studentTwo);
    cohort.enroll(studentThree);

    // cohort 2
    cohort2 = new Cohort("Cohort 2", firstDay, lastDay);

    studentFour = new Student("studentFour", "studentFour@gmail.com");
    studentFive = new Student("studentFive", "studentFive@gmail.com");
    studentSix = new Student("studentSix", "studentSix@gmail.com");

    cohort2.enroll(studentFour);
    cohort2.enroll(studentFive);
    cohort2.enroll(studentSix);
  });

  describe("#systemCheckCompleted", () => {
    it.only("returns 'false' since the students in this cohort did not submit", () => {
      const newDueDate = new Date("2021-01-13");
      const systemCheck = new SystemCheck("Rock Paper Scissors JS", newDueDate);
      cohort.assign(systemCheck);
      cohort2.assign(systemCheck);

      const cohort2Students = [studentFour, studentFive, studentSix];
      cohort2Students.forEach((student) => {
        const submission = new SystemCheckSubmission("awesome solution", student);
        systemCheck.addSubmission(submission);
      });

      expect(cohort.systemCheckCompleted(systemCheck)).toBe(false);
    });

    it("returns 'false' if just one student in the cohort has not submitted their system check submission", () => {
      const newDueDate = new Date("2021-01-13");
      const systemCheck = new SystemCheck("Rock Paper Scissors JS", newDueDate);
      cohort.assign(systemCheck);

      const submission = new SystemCheckSubmission("awesome solution", studentOne);
      systemCheck.addSubmission(submission);

      expect(cohort.systemCheckCompleted(systemCheck)).toBe(false);
    });
  });
});
