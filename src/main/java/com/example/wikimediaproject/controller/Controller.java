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
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String wiki
    ) {
        boolean hasUserSearch = Objects.nonNull(user);
        boolean hasIdSearch = Objects.nonNull(id);
        boolean hasDateSearch = Objects.nonNull(to) || Objects.nonNull(from);
        boolean hasWikiSearch = Objects.nonNull(wiki);
        boolean hasAnySearch = hasDateSearch || hasIdSearch || hasUserSearch || hasWikiSearch;

         if((hasDateSearch ?(hasIdSearch || (hasUserSearch && hasWikiSearch)) : (hasUserSearch ? (hasIdSearch || hasWikiSearch) : (hasIdSearch && hasWikiSearch))) || !hasAnySearch)
             return new DataResult<>(false, "Search can be done by date (from,to), by user and by id. It is not possible to " +
                     "combine params except date (from or/and to) and user.",null).intoResponseEntity();
         else if(hasIdSearch)
             return service.getChangeById(id).intoResponseEntity();
         else if(hasWikiSearch)
             return service.getChangeByDateOrWiki(from,to,wiki).intoResponseEntity();
        return service.getChangeByDateOrUser(from,to,user).intoResponseEntity();
    }
}
