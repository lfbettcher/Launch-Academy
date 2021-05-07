package com.example.lombokexercise.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Train NOT FOUND")
public class TrainNotFoundException extends RuntimeException { }
