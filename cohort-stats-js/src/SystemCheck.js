class SystemCheck {
  constructor(name, dueDate) {
    this.name = name;
    this.dueDate = dueDate;
    this.submissions = [];
  }

  addSubmission(submission) {
    this.submissions.push(submission);
  }
}

export default SystemCheck;
