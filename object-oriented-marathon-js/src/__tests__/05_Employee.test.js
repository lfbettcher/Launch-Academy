import Employee from '../Employee.js'

describe('An Employee', () => {
  let employee

  beforeEach(() => {
    employee = new Employee('Derek', 'Zoolander')
  })

  it('is instantiated with a first name', () => {
    expect(employee.firstName).toEqual('Derek')
  })

  it('is instantiated with a last name', () => {
    expect(employee.lastName).toEqual('Zoolander')
  })

  it('has a default title', () => {
    expect(employee.title).toEqual('Zookeeper')
  })

  it('allows for a custom title', () => {
    let newEmployee = new Employee(
      'Zerek',
      'Doolander',
      'Educator at the School for Ants'
    )
    expect(newEmployee.title).toEqual('Educator at the School for Ants')
  })

  describe('#fullTitle', () => {
    it("returns the employee's full name and title", () => {
      expect(employee.fullTitle()).toEqual('Derek Zoolander, Zookeeper')
    })
  })
  describe('#greet', () => {
    it("returns a string with the employee's name and a greeting", () => {
      expect(employee.greet()).toEqual('Derek Zoolander waved hello!')
    })
  })
})
