package com.example.springboot.blogrestapi.service;

import com.example.springboot.blogrestapi.payload.PostDto;
import com.example.springboot.blogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePost(long id);
}
