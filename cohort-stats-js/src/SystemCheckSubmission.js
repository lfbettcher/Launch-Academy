import Grade from "./Grade.js";

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

export default SystemCheckSubmission;
