package com.myblog.controller;


import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping("/partricular")
    public ResponseEntity<PostDto> getPostByid(@RequestParam long id) {

        PostDto dto = postService.getPostById(id);


        return new ResponseEntity<>(dto, HttpStatus.OK);


    }

    @GetMapping    //http://localhost:8080/api/post?pageNo=0&pageSize=3
    //BEWFOR PAGINATION CONCEPT IT WAS EMPTY
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize",required=false,defaultValue="3") int pageSize

            //SUPPLY THESE OBJECTS TO METHOD ASSOCIATED WITH IT WHICH WILL ALSO MAKE CHANGES TO SERVICE LAYER GET ALL METHOD

    ){


              List<PostDto> dtos= postService.getAllPosts(pageNo,pageSize);

                 return dtos;

    }





}
