import Snake from '../Snake.js'

describe('A Snake', () => {
  let snake

  beforeEach(() => {
    snake = new Snake('Sir Hisss')
  })

  it('is instantiated with a name', () => {
    expect(snake.name).toEqual('Sir Hisss')
  })

  it("makes sure you haven't hardcoded the name", () => {
    let newSnake = new Snake('Kaa')
    expect(newSnake.name).toEqual('Kaa')
  })

  describe('#speak', () => {
    it('says what snakes say', () => {
      expect(snake.speak()).toEqual(
        'Sssssire, ssssire, they may be banditsssss'
      )
    })
  })

  describe('#isColdBlooded', () => {
    it('is cold blooded', () => {
      expect(snake.isColdBlooded()).toEqual(true)
    })
  })
})
