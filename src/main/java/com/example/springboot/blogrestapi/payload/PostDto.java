package com.example.springboot.blogrestapi.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class PostDto {

    private long id;
    private String title;
    private String description;
    private String content;
}
