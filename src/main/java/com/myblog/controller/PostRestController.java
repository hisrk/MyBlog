package com.myblog.controller;


import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/post")
public class PostRestController {
    // bean created with the help of constructor

    private PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {


        PostDto dtoResponse = postService.createPost(postDto);


        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);


    }


    @GetMapping
    public ResponseEntity<PostDto> getPostByid(@RequestParam long id) {

        PostDto dto = postService.getPostById(id);


        return new ResponseEntity<>(dto, HttpStatus.OK);


    }


}
