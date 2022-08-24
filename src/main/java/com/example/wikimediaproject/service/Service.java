package com.example.wikimediaproject.service;

import com.example.wikimediaproject.domain.Change;
import com.example.wikimediaproject.domain.repository.Repository;
import com.example.wikimediaproject.service.result.DataResult;

import java.util.List;
import java.util.Objects;
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
        return new DataResult<>(true,"Successfully fetched data",optional.get());
    }

    public DataResult<List<Change>> getChangeByDateOrUser(String from, String to,String user) {
        List<Change> changes;

        boolean hasBoth = Objects.nonNull(from) && Objects.nonNull(to);

        if(user!=null){
            if(hasBoth)
                changes = repository.findByUserAndTimestampBetween(user,from,to);
            else if (from!=null)
                changes = repository.findByUserAndTimestampAfter(user,from);
            else changes = repository.findByUserAndTimestampBefore(user,to);
        } else {
            if(hasBoth)
                changes = repository.findByTimestampBetween(from,to);
            else if(Objects.nonNull(from))
                changes = repository.findByTimestampAfter(from);
            else
                changes = repository.findByTimestampBefore(to);
        }

        return new DataResult<>(true,"Successfully fetched data!",changes);
    }
    public DataResult<List<Change>> getChangeByDateOrWiki(String from, String to, String wiki) {
        List<Change> changes;
        boolean hasBoth = Objects.nonNull(from) && Objects.nonNull(to);

        if (hasBoth)
            changes = repository.findByWikiAndTimestampBetween(wiki,from,to);
        else if (from==null && to==null)
            changes = repository.findByWiki(wiki);
        else if(from==null)
            changes = repository.findByWikiAndTimestampBefore(wiki,to);
        else changes = repository.findByWikiAndTimestampAfter(wiki,from);
        return new DataResult<>(true,"Successfully fetched data!",changes);
    }
}
