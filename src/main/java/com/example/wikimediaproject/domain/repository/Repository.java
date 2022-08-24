package com.example.wikimediaproject.domain.repository;

import com.example.wikimediaproject.domain.Change;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends ElasticsearchRepository<Change,String> {

    Optional<Change> findById(String id);

    //timestamp must be yyyy-MM-dd OR yyyy-MM-ddTHH-mm-ssZ
    List<Change> findByTimestampAfter(String from);
    List<Change> findByTimestampBefore(String to);
    List<Change> findByTimestampBetween(String from, String to);
    List<Change> findByUserAndTimestampAfter(String user, String from);
    List<Change> findByUserAndTimestampBefore(String user, String to);
    List<Change> findByUserAndTimestampBetween(String user, String from, String to);
    List<Change> findByWikiAndTimestampBetween(String wiki, String from, String to);
    List<Change> findByWiki(String wiki);
    List<Change> findByWikiAndTimestampBefore(String wiki, String to);
    List<Change> findByWikiAndTimestampAfter(String wiki, String from);
}
