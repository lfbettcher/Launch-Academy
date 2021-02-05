import Student from '../Student.js'

describe("student class", () => {
  let student

  beforeEach(() => {
    student = new Student("Sam Seaborn", "sam.seaborn@gmail.com")
  });

  it("defines student", () => {
    expect(student).toBeInstanceOf(Student)
  })

  it("has an initialized name", () => {
    expect(student.name).toBe("Sam Seaborn")
  })

  it("has an initialized email", () => {
    expect(student.email).toBe("sam.seaborn@gmail.com")
  })
})
