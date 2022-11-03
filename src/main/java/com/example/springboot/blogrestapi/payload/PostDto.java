package com.example.springboot.blogrestapi.payload;

import com.example.springboot.blogrestapi.entity.Comment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {

    private long id;

    // title should not be null or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min=2, message = "Post title should have at least 2 characters")
    private String title;

    // title should not be null or empty
    // title should have at least 10 characters
    @NotEmpty
    @Size(min=10, message = "Post description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
