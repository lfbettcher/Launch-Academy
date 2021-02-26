import fs from "fs"

const productsPath = "products.json"

const getProducts = () => JSON.parse(fs.readFileSync(productsPath)).products

class Product {
  constructor({ name, description, price, featured }) {
    this.name = name
    this.description = description
    this.price = price
    this.featured = featured
  }

  static findAll() {
    return getProducts().map((product) => new Product(product))
  }

  static findFeatured() {
    return getProducts()
      .filter((product) => product.featured)
      .map((product) => new Product(product))
  }

  static find(productName) {
    return this.findAll().find((product) => product.name === productName)
  }

  static remove(productName) {
    const productsArray = getProducts()
    const productsCurrent = productsArray.filter(
      (product) => product.name !== productName
    )
    const productsJson = JSON.stringify({ products: productsCurrent })
    fs.writeFileSync(productsPath, productsJson)
    return true
  }

  save() {
    if (this.isValid()) {
      const productsArray = this.constructor.findAll()
      productsArray.push(this)
      const productsJson = JSON.stringify({ products: productsArray })
      fs.writeFileSync(productsPath, productsJson)
      return true
    }
  }

  isValid() {
    return !(
      !this ||
      !this.name ||
      !this.price ||
      this.name.trim() === "" ||
      this.price.toString().trim() === "" ||
      Number.isNaN(parseFloat(this.price))
    )
  }
}

export default Product
