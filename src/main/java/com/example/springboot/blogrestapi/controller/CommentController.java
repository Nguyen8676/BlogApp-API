package com.example.springboot.blogrestapi.controller;

import com.example.springboot.blogrestapi.payload.CommentDto;
import com.example.springboot.blogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity< CommentDto> createComment(@PathVariable(value = "postId") long id, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(id,commentDto), HttpStatus.CREATED) ;
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId){
        CommentDto commentDto=commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId,@RequestBody CommentDto commentDto){
        CommentDto updateComment=commentService.updateComment(postId,commentId, commentDto);
        return new ResponseEntity<>(updateComment,HttpStatus.OK);
    }
}
