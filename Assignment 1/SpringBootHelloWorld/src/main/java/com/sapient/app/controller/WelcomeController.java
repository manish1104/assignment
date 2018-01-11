package com.sapient.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

  @RequestMapping("/greet")
  public String greet(@RequestParam(value = "name") String name) {
    return "Hello " + name;
  }

}
