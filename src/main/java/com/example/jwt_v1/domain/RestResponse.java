package com.example.jwt_v1.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse <T>{
    private int statusCode;
    private String error;
    //message có thể là string hoặc araylist
    private Object message;
    // data chưa biết hình thù ntn nên để T rớ đọ
    private T data;
}
