package com.example.wikimediaproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "logstash-kafka-2022.08.23")
public class Change {
    @Id
    private String id;

    @Field(type = FieldType.Boolean, name = "bot")
    private boolean bot;

    @Field(type = FieldType.Text, name = "type")
    private String type;

    @Field(type = FieldType.Text, name = "wiki")
    private String wiki;

   @Field(type = FieldType.Date, name = "@timestamp")
    private String timestamp;

   @Field(type = FieldType.Text, name = "title")
    private String title;

   @Field(type = FieldType.Text, name = "user")
    private String user;
}
