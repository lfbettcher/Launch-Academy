import Student from '../Student.js'
import SystemCheckSubmission from '../SystemCheckSubmission.js'

describe("system check submission class", () => {
  let student
  let solution
  let submission

  beforeEach(() => {
    student = new Student("Donna Moss", "donna.moss@gmail.com")
    solution = "const fireFlyMeetsSubmission = () => { return \"Every problem is an opportunity in disguise\"}"
    submission = new SystemCheckSubmission(solution, student)
  });

  it("defines system check submission", () => {
    expect(submission).toBeInstanceOf(SystemCheckSubmission)
  })

  it("has an initialized solution", () => {
    expect(submission.solution).toBe(solution)
  })

  it("has an initialized student", () => {
    expect(submission.student).toBe(student)
  })
})
