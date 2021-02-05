import Student from '../Student.js'
import SystemCheckSubmission from '../SystemCheckSubmission.js'
import Grade from '../Grade.js'

describe.skip('system check submission class for exceeds', () => {
  let student
  let submission

  beforeEach(() => {
    student = new Student('Josiah Bartlett', 'josiah.bartlett@gmail.com')
    submission = new SystemCheckSubmission('awesome solution', student)
  })

  describe('#assignGrade', () => {
    it('sets the grade for a submission', () => {
      let grade = Grade.meetsExpectations
      expect(submission.assignGrade(grade)).toBe(grade)
    })
    it('returns an invalid grade message if the grade is invalid', () => {
      expect(submission.assignGrade(9001)).toBe('Invalid Grade Error!')
    })
  })
})
