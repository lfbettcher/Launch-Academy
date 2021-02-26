import express from "express"

const brusselsSproutsRouter = express.Router()

brusselsSproutsRouter.get("/", (req, res) => {
  res.render("brussels-sprouts")
})

brusselsSproutsRouter.get("/of-doom", (req, res) => {
  let recipe = {
    name: "Roasted Brussels Sprouts (of Doom)",
    ingredients: [
      "1 1/2 pounds Brussels sprouts picked on a blood moon",
      "3 tablespoons evil olive oil",
      "3/4 teaspoon of salt processed from tears",
      "1/2 teaspoon freshly ground black pepper"
    ],
    directions: [
      "Preheat oven to 4000 degrees C.",
      "Cut off the brown ends of the Brussels sprouts.",
      "Pull off any yellow outer leaves BUT leave leaves that the color of a lemon on a cold fall morning",
      "Mix them in a bowl with the evil olive oil, tear-salt and pepper.",
      "Pour them on a sheet pan and roast for 35 minutes to 400 years.",
      "They should be until crisp on the outside and tender on the inside.",
      "Shake the pan from time to time to brown the sprouts evenly.",
      "Lightly salt and consider how you can best show your appreciation for these sacred spheres of bitter greenness ",
      "Serve and enjoy!",
      "Obey. Obey. Obey"
    ]
  }

  // res.render(...)
})

export default brusselsSproutsRouter