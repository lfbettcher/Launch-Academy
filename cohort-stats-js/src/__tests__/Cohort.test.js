import Cohort from '../Cohort.js'
import Student from '../Student.js'
import SystemCheck from '../SystemCheck.js'

describe('cohort class', () => {
  let firstDay
  let lastDay
  let cohort

  beforeEach(() => {
    firstDay = new Date('2021-04-01')
    lastDay = new Date('2021-08-02')
    cohort = new Cohort('Cohort 30', firstDay, lastDay)
  })

  it('defines cohort', () => {
    expect(cohort).toBeInstanceOf(Cohort)
  })

  it('has an initialized title', () => {
    expect(cohort.title).toBe('Cohort 30')
  })

  it('has an initialized startDate', () => {
    expect(cohort.startDate).toBe(firstDay)
  })

  it('has an initialized endDate', () => {
    expect(cohort.endDate).toBe(lastDay)
  })

  it("has an initialized empty 'students' array", () => {
    expect(cohort.students).toBeInstanceOf(Array)
    expect(cohort.students).toHaveLength(0)
  })

  it("has an initialized empty 'systemChecks' array", () => {
    expect(cohort.systemChecks).toBeInstanceOf(Array)
    expect(cohort.systemChecks).toHaveLength(0)
  })

  describe('#enroll', () => {
    let student = new Student('Sam Seaborn', 'sam.seaborn@gmail.com')

    it('adds a student to the students array', () => {
      cohort.enroll(student)
      expect(cohort.students).toContain(student)
    })

    it('should not add the student if they are already in the cohort', () => {
      cohort.enroll(student)
      cohort.enroll(student)
      expect(cohort.students).toHaveLength(1)
    })
  })

  describe('#assign', () => {
    it('adds a system check to the system checks array', () => {
      let newDueDate = new Date('2021-01-13')
      let systemCheck = new SystemCheck('Rock Paper Scissors JS', newDueDate)

      cohort.assign(systemCheck)
      expect(cohort.systemChecks).toContain(systemCheck)
    })
  })

  describe('#roster', () => {
    beforeEach(() => {
      let studentOne = new Student('Mandy Hampton', 'mandy.hampton@gmail.com')
      let studentTwo = new Student('Josh Lyman', 'josh.lyman@gmail.com')
      let studentThird = new Student('Leo McGarry', 'leo.mcgarry@gmail.com')

      cohort.enroll(studentOne)
      cohort.enroll(studentTwo)
      cohort.enroll(studentThird)
    })

    // EXAMPLE_OUTPUT of roster method
    //
    // Cohort: Cohort 30
    // --------------------
    // Mandy Hampton mandy.hampton@gmail.com
    // Josh Lyman josh.lyman@gmail.com
    // Leo McGarry leo.mcgarry@gmail.com
    //
    // EXAMPLE_OUTPUT

    it("returns a string containing the cohort's title", () => {
      expect(cohort.roster()).toContain('Cohort 30')
    })

    it('returns a string containing each students name and email seperated by a new line', () => {
      expect(cohort.roster()).toContain(
        'Mandy Hampton mandy.hampton@gmail.com\n'
      )
      expect(cohort.roster()).toContain('Josh Lyman josh.lyman@gmail.com\n')
      expect(cohort.roster()).toContain('Leo McGarry leo.mcgarry@gmail.com\n')
    })
  })
})
