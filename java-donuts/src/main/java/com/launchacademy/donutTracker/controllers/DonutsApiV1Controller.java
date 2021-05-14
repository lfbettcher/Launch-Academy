package com.launchacademy.donutTracker.controllers;

import com.launchacademy.donutTracker.models.Donut;
import com.launchacademy.donutTracker.repositories.DonutRepository;
import com.launchacademy.donutTracker.services.DonutService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/donuts")
public class DonutsApiV1Controller {

  private final DonutRepository donutRepository;
  private final DonutService donutService;

  @Autowired
  public DonutsApiV1Controller(DonutRepository donutRepository, DonutService donutService) {
    this.donutRepository = donutRepository;
    this.donutService = donutService;
  }

  @GetMapping
  public Page<Donut> getDonuts(Pageable pageable) {
    return donutRepository.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Donut getDonut(@PathVariable Integer id) {
    return donutRepository.findById(id).orElseThrow(() -> new DonutNotFoundException());
  }

  @PostMapping()
  public Donut createDonut(@RequestBody Donut donut) {
    return donutRepository.save(donut);
  }
  // curl -X POST localhost:8080/api/v1/donuts -H 'Content-type:application/json' -d
  // '{"type": "donut thru curl", "description": "desc curl", "vegan": "true"}'

  @NoArgsConstructor
  private class DonutNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class DonutNotFoundAdvice {

    @ExceptionHandler(DonutNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String donutNotFoundHandler(DonutNotFoundException e) {
      return e.getMessage();
    }
  }

}
