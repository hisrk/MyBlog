package com.myblog.service.impl;

import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    //Use the shortcut: Ctrl + Alt + L-for aligning the code
    //here we should have applied autowired but in this case we are using constructor injection

    //This process occurs without explicit XML configuration in a Spring Boot application, thanks to Spring's auto-configuration and component scanning features.


    private PostRepository postRepository;


    //CONSTRUCTOR
    public PostServiceImpl(PostRepository postRepository) {

        this.postRepository = postRepository;
    }


    //METHOD

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();


        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post savedPost = postRepository.save(post);
        PostDto dto = new PostDto();


        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setContent(savedPost.getContent());
        dto.setDescription(savedPost.getDescription());


        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        //INTERVIEW QUESTION-> this orElseThrow method is with java 8 feature
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with this id:" + id));


        PostDto dto = new PostDto();


        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());


        return dto;
    }
}
