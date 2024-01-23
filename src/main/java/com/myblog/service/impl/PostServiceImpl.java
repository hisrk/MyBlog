package com.myblog.service.impl;

import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    //Use the shortcut: Ctrl + Alt + L-for aligning the code
    //here we should have applied autowired but in this case we are using constructor injection

    //This process occurs without explicit XML configuration in a Spring Boot application,
    // thanks to Spring's auto-configuration and component scanning features.


    private PostRepository postRepository;


    //CONSTRUCTOR
    public PostServiceImpl(PostRepository postRepository) {

        this.postRepository = postRepository;
    }


    //METHOD

    @Override
    public PostDto createPost(PostDto postDto) {


        Post post = mapToEntity(postDto);
//      TO CHANGE THHIS EVERYTIME WE HAVE CREATED A SEPERATE METHOD FOR CONVERTING dTO OBJ TO ENTITY OBJ

        //    Post post=New Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());

        Post savedPost = postRepository.save(post);

     //AGAIN TO CONBER ENTITY OBJ TO DTO FOR JSON OBJ RESPONSE


        // PostDto dto = new PostDto();
//        dto.setId(savedPost.getId());
//        dto.setTitle(savedPost.getTitle());
//        dto.setContent(savedPost.getContent());
//        dto.setDescription(savedPost.getDescription());

        PostDto dto = mapToDto(savedPost);
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

    @Override  //THESE PAGENO AND PAGE SIZE AFTER PAGINATION BEFORE IT WAS EMPTY
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //------------------AFTER PAGINATION CONCEPT-------------------------->

       Sort sort= (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
                  // <-------- IF CONDITION STARTS--------------------->---RETURN-----------------><---ELSE-------------------->



        //Pageable pageable =PageRequest.of(pageNo,pageSize, Sort.by(sortBy)); BEFORE TERNARY OPERATOR

        Pageable pageable =PageRequest.of(pageNo,pageSize, sort); //AFTER TERNARY OPERATOR

                   Page<Post> pagePost=postRepository.findAll(pageable);


                      List<Post> posts=pagePost.getContent();
        //------------------------------------------------------------------------->


        //-----------------BEFORE PAGINATION CONCEPT-------------------------------->


                     //this will always reeturn entity object

       // List<Post> posts = postRepository.findAll();


                    //------------------------------------------------------------>

        //NOW TO GET REPSPONE IN THE FORM OF DTO OBJECT

        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        return dtos;
    }
//mapping to get the java object
    public Post mapToEntity(PostDto postDto) {

        Post post = new Post();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        return post;

    }

    //mappiing to get the Dto object
    public PostDto mapToDto(Post post) {

        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());


        return dto;


    }


}
