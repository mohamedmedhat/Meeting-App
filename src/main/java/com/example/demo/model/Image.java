package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "images")
public class Image {
    @Id
    private String id;

    @Field(name = "filename")
    private String filename;

    @Field(name = "filetype")
    private String filetype;

    @Field(name = "filePath")
//    private byte[] data; => very bad way
    private String filePath;

    @DBRef
    private Product product;
}
