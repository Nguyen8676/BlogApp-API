package com.example.springboot.blogrestapi.service.impl;

import com.example.springboot.blogrestapi.entity.Post;
import com.example.springboot.blogrestapi.exception.ResourceNotFoundException;
import com.example.springboot.blogrestapi.payload.PostDto;
import com.example.springboot.blogrestapi.payload.PostResponse;
import com.example.springboot.blogrestapi.repository.PostRepository;
import com.example.springboot.blogrestapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {
        //create pageable instance
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable =  PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts=postRepository.findAll(pageable);
        List<Post> listOfPosts=posts.getContent();
        //List<Post> posts=postRepository.findAll();

        List<PostDto> content = listOfPosts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());


        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatepost=postRepository.save(post);

        return mapToDTO(updatepost);
    }

    @Override
    public void deletePost(long id) {
        // find post by id then delete the post
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
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
