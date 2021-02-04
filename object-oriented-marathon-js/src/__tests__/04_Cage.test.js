import Cage from '../Cage.js'
import Snake from '../Snake.js'

describe('A Cage', () => {
  let cage

  beforeEach(() => {
    cage = new Cage()
  })

  it('should be created with no animal inside', () => {
    expect(cage.animal).toEqual(null)
  })

  describe('#isEmpty', () => {
    it('returns true if the cage is empty', () => {
      expect(cage.isEmpty()).toEqual(true)
    })

    it('returns false if the cage is filled with an animal', () => {
      let snake = new Snake('Sir Hiss')
      cage.animal = snake

      expect(cage.isEmpty()).toEqual(false)
    })
  })
})
