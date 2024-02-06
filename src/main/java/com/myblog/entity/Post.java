package com.myblog.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {


     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

      private String title;

    private String description;

    private  String content;

    //here mapping of post to comments has one to many relationship whereas one is post and many is comments results to composition
    @OneToMany(cascade=CascadeType.ALL,mappedBy = "post")
    private List<Comment> comments;




}
