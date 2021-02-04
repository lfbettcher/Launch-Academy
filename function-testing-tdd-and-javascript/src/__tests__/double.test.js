import double from "../double.js"

describe("doubling a number", () => {
  it("doubles 2 resulting in 4", () => {
    expect(double(2)).toEqual(4)
  })

  it("doubles 5 resulting in 10", () => {
    expect(double(5)).toEqual(10)
  })
})
