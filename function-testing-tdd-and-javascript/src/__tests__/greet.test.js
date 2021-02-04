import greet from "../greet.js"

describe("greet function", () => {
  it("says Hello to Scott", () => {
    expect(greet("Scott")).toEqual("Hello, Scott")
  })
})
