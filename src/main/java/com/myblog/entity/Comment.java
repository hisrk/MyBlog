package com.myblog.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

private String text;


private String email;

//many to one where many is comments and one is post
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;



}
