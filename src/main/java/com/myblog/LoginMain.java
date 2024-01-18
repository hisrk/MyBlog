package com.myblog;

import java.util.List;
import java.util.stream.Collectors;

public class LoginMain {


    public static void main(String[] args) {

        List<Login> listLogins= List.of(new Login("shahrukh","srk000"),
                new Login("arko","0000"),
                new Login("shivani","shhiva"));

        System.out.println(listLogins);//now to conver this into Logind=Dto object and also with the help of Stream we can get number of object one by one

              List<LoginDto> loginDtos= listLogins.stream().map(login->mapToLoginDto(login)).collect(Collectors.toList());

        System.out.println(loginDtos);

    }

    public static LoginDto mapToLoginDto(Login login){

        LoginDto dto=new LoginDto();

        dto.setUsername(login.getUsername());
        dto.setPassword(login.getPassword());








        return dto;
    }


}
