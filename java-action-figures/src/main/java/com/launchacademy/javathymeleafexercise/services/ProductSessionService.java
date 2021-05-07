package com.launchacademy.javathymeleafexercise.services;

import com.launchacademy.javathymeleafexercise.models.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class ProductSessionService implements ProductService {

  List<Product> products;

  public ProductSessionService() {
    products = new ArrayList<Product>();
    Product product = new Product();

    product.setId(0);
    product.setName("GI Joe");
    product.setDescription("America's Favorite Action Figure");
    product.setPrice(4.99);
    products.add(product);

    product = new Product();
    product.setId(1);
    product.setName("Transformer");
    product.setDescription("More Than Meets the Eye");
    product.setPrice(26.49);
    products.add(product);

    product = new Product();
    product.setId(2);
    product.setName("Dr Who");
    product.setDescription("It's a Thymey Whymey Toy");
    product.setPrice(14.49);
    products.add(product);
  }

  public List<Product> getProducts() {
    return products;
  }

  public void addToList(Product product) {
    this.products.add(product);
  }
}
