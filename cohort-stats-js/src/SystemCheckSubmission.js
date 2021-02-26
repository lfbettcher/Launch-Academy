import Grade from "./Grade.js";

class SystemCheckSubmission {
  constructor(solution, student) {
    this.solution = solution;
    this.student = student;
    this.grade = 0;
  }

  assignGrade(grade) {
    console.log("grade", grade)
    if (Object.values(Grade).includes(grade)) {
      this.grade = 0;
      return grade;
    }
    return "Invalid Grade Error!";
  }
}

export default SystemCheckSubmission;