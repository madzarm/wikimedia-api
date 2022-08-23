package com.example.wikimediaproject.service;

import com.example.wikimediaproject.domain.Change;
import com.example.wikimediaproject.domain.repository.Repository;
import com.example.wikimediaproject.service.result.DataResult;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public DataResult<Change> getChangeById(String id){
        Optional<Change> optional = repository.findById(id);
        if(optional.isEmpty())
            return new DataResult<>(false,"Change with that id does not exist",null);
        return new DataResult<>(true,"Successfully retrieved data",optional.get());
    }

    public DataResult<List<Change>> getChangeByDate(String from) {
        List<Change> changes = repository.findByTimestampAfter(from);
        return new DataResult<>(true,"Successfully retrieved data!",changes);
    }

    public String hello(){
        return "Hello";
    }
}
