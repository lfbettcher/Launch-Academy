import Zoo from '../Zoo.js'
import Employee from '../Employee.js'
import Fox from '../Fox.js'
import Snake from '../Snake.js'
import Gorilla from '../Gorilla.js'
import Cage from '../Cage.js'

describe('A Zoo', () => {
  let zoo

  beforeEach(() => {
    zoo = new Zoo('The First Zoo in Outer Space')
  })

  it('is instantiated with a name', () => {
    expect(zoo.name).toEqual('The First Zoo in Outer Space')
  })

  it('should be instantiated with 10 cages by default, and assign them to the cages property', () => {
    expect(zoo.cages).toHaveLength(10)
    expect(zoo.cages[0]).toBeInstanceOf(Cage)
  })

  describe('#addEmployee', () => {
    it('should be able to have employees', () => {
      let newEmployee = new Employee('Roger', 'Rabbit')
      zoo.addEmployee(newEmployee)

      expect(zoo.employees[0].firstName).toEqual('Roger')
    })
  })

  describe('#addAnimal', () => {
    it('should add an animal to the first open cage', () => {
      let newAnimal = new Fox('Kit')
      zoo.addAnimal(newAnimal)

      expect(zoo.cages[0].animal.name).toEqual('Kit')
    })

    it('should not add an animal to all of the open cages', () => {
      let newAnimal = new Fox('Kit')
      zoo.addAnimal(newAnimal)

      expect(zoo.cages[1].isEmpty()).toEqual(true)
    })

    it('should return a message that all of the cages are full if cages are filled', () => {
      let newAnimal = new Fox('Kit')
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)
      zoo.addAnimal(newAnimal)

      expect(zoo.addAnimal(newAnimal)).toEqual('All of the cages are full!')
    })
  })

  describe('#visit', () => {
    it('should allow someone to visit and be greeted by each employee and animal', () => {
      let animalOne = new Snake('Sir Hiss')
      let animalTwo = new Gorilla('Bob')
      let employeeOne = new Employee('Jojo', 'The Great')
      let employeeTwo = new Employee('Derek', 'Zoolander')

      zoo.addAnimal(animalOne)
      zoo.addAnimal(animalTwo)
      zoo.addEmployee(employeeOne)
      zoo.addEmployee(employeeTwo)

      expect(zoo.visit()).toEqual(
        'Sssssire, ssssire, they may be banditsssss\nBob got a bad feeling about this...\nJojo The Great waved hello!\nDerek Zoolander waved hello!\n'
      )
    })
  })
})
