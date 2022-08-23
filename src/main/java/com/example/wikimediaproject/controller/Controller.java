package com.example.wikimediaproject.controller;

import com.example.wikimediaproject.domain.Change;
import com.example.wikimediaproject.service.Service;

import com.example.wikimediaproject.service.result.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

   private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/change")
    public ResponseEntity<DataResult<Change>> getChangeById(@RequestParam String id) {
        return service.getChangeById(id).intoResponseEntity();
    }

    @GetMapping("Hello")
    public String getData() {
        return service.hello();
    }
}
