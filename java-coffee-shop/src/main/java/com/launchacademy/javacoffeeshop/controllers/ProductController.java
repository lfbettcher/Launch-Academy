package com.launchacademy.javacoffeeshop.controllers;

import com.launchacademy.javacoffeeshop.models.Product;
import com.launchacademy.javacoffeeshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String listProducts(Model model) {
    model.addAttribute("products", productService.findAll());
    return "products/index";
  }

  @GetMapping("/show/{productId}")
  public String showProduct(@PathVariable Integer productId, Model model) {
    model.addAttribute("product", productService.get(productId));
    return "products/show";
  }

  @GetMapping("/new")
  public String productForm(@ModelAttribute Product product) {
    return "products/new";
  }

  @PostMapping
  public String createProduct(@ModelAttribute Product product) {
    product.setId(productService.findAll().size() + 1);
    productService.addToList(product);
    return "redirect:/products/show/" + product.getId();
  }
}