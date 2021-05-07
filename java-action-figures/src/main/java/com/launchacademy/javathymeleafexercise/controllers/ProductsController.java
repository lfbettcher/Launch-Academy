package com.launchacademy.javathymeleafexercise.controllers;

import com.launchacademy.javathymeleafexercise.models.Product;
import com.launchacademy.javathymeleafexercise.services.ProductService;
import java.util.List;
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
public class ProductsController {

  @Autowired
  ProductService service;

  @GetMapping
  public String listProducts(Model model) {
    List<Product> products = service.getProducts();
    model.addAttribute("products", products);
    return "products/index";
  }

  @GetMapping("/{id}")
  public String showProducts(@PathVariable int id, Model model) {
    List<Product> products = service.getProducts();
    Product product = products.get(id);
    model.addAttribute("product", product);
    return "products/show";
  }

  @GetMapping("/new")
  public String getNewForm(@ModelAttribute Product product) {
    return "products/new";
  }

  @PostMapping
  public String createProduct(@ModelAttribute Product product) {
    product.setId(service.getProducts().size());
    service.addToList(product);
    return "redirect:/products";
  }
}
