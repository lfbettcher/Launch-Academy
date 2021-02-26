import express from "express"

const categoriesRouter = new express.Router()

const catalog = {
  "space-gear": {
    rocket: {
      title: "Launch Rocket",
      description: "The fastest rocket in the universe"
    },
    spacesuit: {
      title: "Launch Retro Spacesuit",
      description: "Chic, comfortable, and air tight."
    }
  },
  "coding-schwag": {
    "launch-sticker": {
      title: "Launch Sticker for Your Laptop",
      description: "Instant nerd cred for your gear."
    },
    "launch-t-shirt": {
      title: "Launch T-Shirt",
      description: "Nerd Fashion. The Launch Shield is so in season."
    }
  }
}

categoriesRouter.get("/:categoryName/products/:productName", (req, res) => {
  const categoryName = req.params.categoryName
  const productName = req.params.productName
  const category = catalog[categoryName] || {}
  const product = category[productName]

  if (product) {
    res
      .status(200)
      .send(`<h1>${product.title}</h1><p>${product.description}</p>`)
  } else {
    res.status(404).send("Product not found.")
  }
})

export default categoriesRouter
