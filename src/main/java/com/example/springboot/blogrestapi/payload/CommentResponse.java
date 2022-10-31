package com.example.springboot.blogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private long id;
    private String title;
    private String description;
    private String content;
    private String postId;
}
