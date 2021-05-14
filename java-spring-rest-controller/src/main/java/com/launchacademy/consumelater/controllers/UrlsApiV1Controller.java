package com.launchacademy.consumelater.controllers;

import com.launchacademy.consumelater.models.Url;
import com.launchacademy.consumelater.repositories.UrlRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlsApiV1Controller {
  private UrlRepository urlRepo;

  @Autowired
  public UrlsApiV1Controller(UrlRepository urlRepo) {
    this.urlRepo = urlRepo;
  }

  @GetMapping
  public Page<Url> getList(Pageable pageable) {
    return urlRepo.findAll(pageable);
  }

  @NoArgsConstructor
  private class UrlNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class UrlNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(UrlNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/{id}")
  public Url getOne(@PathVariable Integer id) {
    return urlRepo.findById(id).orElseThrow(() -> new UrlNotFoundException());
  }

  // endpoint allows us to create URL records
  @PostMapping
  public Url create(@RequestBody Url url) {
    return urlRepo.save(url);
  }
}
