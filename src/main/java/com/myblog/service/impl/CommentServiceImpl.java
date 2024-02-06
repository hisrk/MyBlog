package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    PostRepository postRepository;
    CommentRepository commentRepository;
    ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=new ModelMapper();
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not fount by id:" + postId));
//<--------------------WITHOUT METHOD---------------------------------->
        Comment comment= new Comment();

       comment.setEmail(commentDto.getEmail());
       comment.setText(commentDto.getText());
       comment.setPost(post);
//<---------------------------------------------------------------------->


      //  Comment comment = mapToCommentEntity(commentDto);

        Comment savedComment = commentRepository.save(comment);
//<------------------------WITH METHOD---------------------------------->
//        CommentDto commentDto1= new CommentDto();
//
//        commentDto1.setEmail(savedComment.getEmail());
//        commentDto1.setText(savedComment.getText());
//        commentDto1.setId(savedComment.getId());

        //<---------------------------------------------------------------------->

        CommentDto commentDto1 = this.mapToCommentDto(savedComment);


        return commentDto1;
    }

    @Override
    public void deleteComment(long id) {

            commentRepository.deleteById(id);

    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {


        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found by this id"+postId));
                          Comment comment=  commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found"+id));



                                    Comment c=mapToCommentEntity(commentDto);

                                   c.setId(comment.getId());
                                   c.setPost(post);

                                  Comment savedComment= commentRepository.save(c);

                                  CommentDto  commentDto1= mapToCommentDto(savedComment);





     //------------------------------BEFORE MODEL MAPPER--------------------------------->>>>>>>>>>>>>

//                                      comment.setText(commentDto.getText());
//
//
//                                      Comment comment1=commentRepository.save(comment);
//
//                                       CommentDto commentDto1= new CommentDto();
//
//                                       commentDto1.setText(comment1.getText());
//
//                                         commentDto1.setEmail(comment.getEmail());

//--------------------------------------------------------------------------------------->>>>>>>>>>>>>>>>>>>.



        return commentDto1;
    }

    //map commnetdto to Entity
    public Comment mapToCommentEntity(CommentDto commentDto) {

           Comment comment=modelMapper.map(commentDto,Comment.class);



//        Comment comment = new Comment();
//
//
//        comment.setEmail(commentDto.getEmail());
//        comment.setText(commentDto.getText());
//        comment.setPost(post);
//
//
                return comment;

    }

    //return map to CommnetDto

    public CommentDto mapToCommentDto(Comment savedComment) {

          CommentDto commentDto= modelMapper.map(savedComment,CommentDto.class);

//        CommentDto commentDto = new CommentDto();
//
//        commentDto.setEmail(savedComment.getEmail());
//        commentDto.setText(savedComment.getText());
//        commentDto.setId(savedComment.getId());
//        return commentDto;

        return commentDto;
    }


}
