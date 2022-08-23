package com.example.wikimediaproject.controller;

import com.example.wikimediaproject.service.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

   private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("Hello")
    public String getData() {
        return service.hello();
    }
}
