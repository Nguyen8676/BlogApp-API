package com.example.springboot.blogrestapi.controller;


import com.example.springboot.blogrestapi.payload.PostDto;
import com.example.springboot.blogrestapi.payload.PostResponse;
import com.example.springboot.blogrestapi.service.PostService;
import com.example.springboot.blogrestapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    PostService postService;

    @GetMapping("/test")
    public String TestController(){
        return "Hello PostService";

    }

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> CreatePost( @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse GetAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIREACTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> GetPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        PostDto response=postService.updatePost(postDto,id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePost(@PathVariable(name = "id") long id){
        postService.deletePost(id);
        return  new ResponseEntity<>("Deleted Post"+id, HttpStatus.OK);
    }
}
