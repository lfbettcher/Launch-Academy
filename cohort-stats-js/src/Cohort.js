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
    const studentsSubmitted = systemCheck.submissions.map((submission) => submission.student);
    return this.students.every((student) => studentsSubmitted.includes(student));
    // return this.students.length === systemCheck.submissions.length;
    // return this.students.every((student) => systemCheck.didStudentCompleteSystemCheck(student));
    // return !this.students.some((student) => !systemCheck.didStudentCompleteSystemCheck(student));
  }
}

export default Cohort;
