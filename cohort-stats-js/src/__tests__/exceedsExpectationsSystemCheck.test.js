import Student from '../Student.js'
import SystemCheck from '../SystemCheck.js'
import SystemCheckSubmission from '../SystemCheckSubmission.js'
import Cohort from '../Cohort.js'
import Grade from '../Grade.js'

describe.skip('system check class for exceeds', () => {
  let charlie
  let josiah
  let zoey
  let newDueDate
  let systemCheck

  beforeEach(() => {
    charlie = new Student('Charlie Young', 'charlie.young@gmail.com')
    josiah = new Student('Josiah Bartlett', 'josiah.bartlett@gmail.com')
    zoey = new Student('Zoey Bartlett', 'zoey.bartlett@gmail.com')

    newDueDate = new Date('2021-01-13')
    systemCheck = new SystemCheck('Rock Paper Scissors JS', newDueDate)
  })

  it('has an initialized name', () => {
    expect(systemCheck.name).toBe('Rock Paper Scissors JS')
  })

  describe('#averageGrade', () => {
    it('returns the average score for all submissions', () => {
      let submissionOne = new SystemCheckSubmission(
        'cool submission on functions',
        zoey
      )
      systemCheck.addSubmission(submissionOne)
      submissionOne.assignGrade(Grade.meetsExpectations)

      let submissionTwo = new SystemCheckSubmission(
        'even better submission on functions',
        charlie
      )
      systemCheck.addSubmission(submissionTwo)
      submissionTwo.assignGrade(Grade.exceedsExpectations)

      let submissionThree = new SystemCheckSubmission(
        'submission with lots of errors',
        josiah
      )
      systemCheck.addSubmission(submissionThree)
      submissionThree.assignGrade(Grade.doesNotMeetExpectations)

      let sum = Grade.meetsExpectations
      sum += Grade.exceedsExpectations
      sum += Grade.doesNotMeetExpectations
      let average = sum / 3.0

      expect(systemCheck.averageGrade()).toBe(average)
    })
  })

  describe('#didStudentCompleteSystemCheck', () => {
    it("returns 'true' if a submission exists for that student", () => {
      let submissionOne = new SystemCheckSubmission(
        'cool submission on functions',
        zoey
      )
      systemCheck.addSubmission(submissionOne)

      let submissionTwo = new SystemCheckSubmission(
        'even better submission on functions',
        charlie
      )
      systemCheck.addSubmission(submissionTwo)

      expect(systemCheck.didStudentCompleteSystemCheck(charlie)).toBe(true)
    })

    it("returns 'false' if submission does not exist for that student", () => {
      let randomDate = new Date('2074-02-11')
      let randomSystemCheck = new SystemCheck('Python OOP', randomDate)

      expect(randomSystemCheck.didStudentCompleteSystemCheck()).toBe(false)
    })
  })
})
