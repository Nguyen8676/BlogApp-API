package com.example.springboot.blogrestapi.service.impl;

import com.example.springboot.blogrestapi.entity.Post;
import com.example.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.example.springboot.blogrestapi.payload.PostDto;
import com.example.springboot.blogrestapi.repository.PostRepository;
import com.example.springboot.blogrestapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert Dto to entity then save to db
        Post post=mapToEntity(postDto);
        Post npost= postRepository.save(post);

        //convert entity to DTO to get response
        PostDto response=mapToDTO(npost);
        return response;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    // start map to DTO & opposite
    private PostDto mapToDTO(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
    //end map to DTO & opposite
}
