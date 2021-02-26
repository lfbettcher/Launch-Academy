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
    return this.submissions.some((submission) => submission.student === student);
  }

  submittable() {
    return true;
  }
}

export default SystemCheck;
