package com.example.wikimediaproject.domain.repository;

import com.example.wikimediaproject.domain.Change;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends ElasticsearchRepository<Change,String> {

    Optional<Change> findById(String id);
}
