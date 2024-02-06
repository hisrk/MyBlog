package com.myblog.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String name;
    private String username;//THIS IS UNIQUE BECAUSE CONSTRAINED IS PROVIDED IN
    private String email;//THIS IS UNIQUE BECAUSE CONSTRAINED IS PROVIDED IN
    private String password;
}
