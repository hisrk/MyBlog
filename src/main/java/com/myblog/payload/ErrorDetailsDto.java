package com.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDto {

    private String message;

    private Date date;
    //uniform eresorce identifier
    private String uri;

}
