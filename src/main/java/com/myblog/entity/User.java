package com.myblog.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;//THIS IS UNIQUE BECAUSE CONSTRAINED IS PROVIDED IN
    private String email;//THIS IS UNIQUE BECAUSE CONSTRAINED IS PROVIDED IN
    private String password;

    //WE CAN ALSO APPLY @COLUMN (NAME="USERNAME,UNIQUE=TRUE) on varibles only
}