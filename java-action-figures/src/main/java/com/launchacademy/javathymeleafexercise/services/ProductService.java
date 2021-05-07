package com.launchacademy.javathymeleafexercise.services;

import com.launchacademy.javathymeleafexercise.models.Product;
import java.util.List;

public interface ProductService {

  List<Product> getProducts();

  void addToList(Product product);
}
