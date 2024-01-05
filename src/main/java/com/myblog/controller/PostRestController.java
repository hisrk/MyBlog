package com.myblog.controller;


import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/post")
public class PostRestController {

      private PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
     public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){


           PostDto dtoResponse=postService.createPost(postDto);


    return new ResponseEntity<>(dtoResponse,HttpStatus.CREATED);


}


}
