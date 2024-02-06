package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
public class CommentController {


    //Control shift f--> for search

    private CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


   //http://localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){

                          CommentDto commentDto1=commentService.createComment(commentDto,postId);


                            return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){


           commentService.deleteComment(id);

   return new ResponseEntity<>("comment is deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/post/{postId}")//not post_id but comment primary key
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){

        CommentDto commentDto1=commentService.updateComment(id,commentDto,postId);



           return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }
}
