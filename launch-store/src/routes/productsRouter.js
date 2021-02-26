import express from "express"

import Product from "../models/Product.js"

const productsRouter = express.Router()

productsRouter.get("/", (req, res) => {
  res.render("products/index", { products: Product.findFeatured() })
})

productsRouter.get("/new", (req, res) => {
  res.render("products/new")
})

productsRouter.get("/:productName", (req, res) => {
  res.render("products/show", {
    product: Product.find(req.params.productName)
  })
})

productsRouter.post("/", (req, res) => {
  const { name, description, price, featured } = req.body
  const priceNum = parseFloat(price)
  const featuredBool = featured === "true"
  const newProduct = new Product({
    name: name,
    description: description,
    price: priceNum,
    featured: featuredBool
  })
  if (newProduct.save()) res.redirect("/products")
  else res.render("products/new", { error })
})

productsRouter.post("/delete", (req, res) => {
  const productName = req.body.deleteProduct
  Product.remove(productName)
  res.redirect("/products")
})

export default productsRouter
