import express from "express"

const productsRouter = new express.Router()

const products = {
  Rocket: {
    title: "Launch Rocket",
    description: "The fastest rocket in the universe"
  },
  Spacesuit: {
    title: "Launch Retro Spacesuit",
    description: "Chic, comfortable, and air tight."
  }
}

productsRouter.get("/:productName", (req, res) => {
  const productName = req.params.productName
  const productData = products[productName]
  if (productData) {
    res
      .status(200)
      .send(`<h1>${productData.title}</h1><p>${productData.description}</p>`)
  } else {
    res.status(404).send("Product not found.")
  }
})

export default productsRouter
