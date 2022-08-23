package com.example.wikimediaproject.controller;

import com.example.wikimediaproject.domain.Change;
import com.example.wikimediaproject.service.Service;

import com.example.wikimediaproject.service.result.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Controller {

   private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/change")
    public ResponseEntity<DataResult<Change>> getChange(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        boolean hasIdSearch = Objects.nonNull(id);
        boolean hasDateSearch = Objects.nonNull(to) || Objects.nonNull(from);

        if(hasIdSearch && hasDateSearch)
            return new DataResult<>(false,"Id and Date search can not be combined!",null).intoResponseEntity();
        else if(hasIdSearch)
            return service.getChangeById(id).intoResponseEntity();

        return service.getChangeByDate(from,to).intoResponseEntity();
    }
}
